package Integracion.Personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Queries.Query;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

public class PersonalPorRangoSueldo implements Query{

	@Override
	public Object execute(Object param) {
		TransactionManager tm = null;
		Transaction t = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TPersonal> listaPersonal = new ArrayList<>();
		Object[] rango = (Object[]) param;
		
		try {
			tm = TransactionManager.getInstance();
			t = tm.nuevaTransaccion();
			t.start();

			con = (Connection) t.getResource();
			ps = con.prepareStatement("SELECT * FROM personal FOR UPDATE");
			rs = ps.executeQuery();

			// LA QUERIE CALCULA EL SALARIO DE MANERA DIFERENTE EN FUNCION DE SI ES JEFE O EMPLEADO, LO DEVUELVE EN EL ATRIBUTO NOMINA
			// CORRESPONDIENTE
			while (rs.next()) {
				TPersonal personal = null;
				if (rs.getBoolean("activo")) {
					if (!rs.getBoolean("jefe")) {
						personal = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("bonificaciones"));
						personal.setNomina(personal.getNomina() + personal.getBonificaciones());
					}
						
					else {
						personal = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
						personal.setNomina(personal.getNomina() + (personal.getResponsabilidades()*personal.getBonificaciones()));
					}
					//SOLO LO AÑADE SI ESTA ENTRE LOS DOS PARAMETROS
					if(personal.getNomina() > (Integer)rango[0] && personal.getNomina() < (Integer)rango[1])
						listaPersonal.add(personal);
				}
				
			}
			ps.close();
			rs.close();
			t.commit();

			if (listaPersonal.isEmpty()) {
				
				return null;
			}

			return listaPersonal;

		} catch (Exception e) {
			e.printStackTrace();
			if (t != null)
				t.rollback();
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (SQLException c) {
				c.printStackTrace();
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (SQLException c) {
				c.printStackTrace();
			}

		}

		return null;
	}

}
