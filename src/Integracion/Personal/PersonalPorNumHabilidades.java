package Integracion.Personal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Queries.Query;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonalPorNumHabilidades implements Query {

	@Override
	public Object execute(Object param) {

		TransactionManager tm = null;
		Transaction t = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TPersonal> lista = new ArrayList<>();
		try {
			tm = TransactionManager.getInstance();
			t = tm.nuevaTransaccion();
			t.start();

			con = (Connection) t.getResource();
			ps = con.prepareStatement("SELECT p.id, p.nombre, COUNT(h.id) AS num_habilidades" + "		FROM personal p"
					+ "		JOIN habilidadPersonal hp ON p.id = hp.personal_id"
					+ "		JOIN habilidad h ON hp.habilidad_id = h.id" + "		GROUP BY p.id, p.nombre"
					+ "		HAVING num_habilidades = ? FOR UPDATE");
			ps.setInt(1, (Integer) param);
			rs = ps.executeQuery();

			while (rs.next()) {

				ps = con.prepareStatement("SELECT * FROM personal WHERE id = ? ");
				ps.setInt(1, rs.getInt("id"));
				ResultSet rs2 = ps.executeQuery();
				TPersonal personal;
				if (rs2.next()) {
					if (rs2.getString("cargo") != null)
						personal = new TEmpleado(rs2.getString("nif"), rs2.getString("nombre"), rs2.getString("apellido"), rs2.getInt("id"),
								rs2.getInt("id_nave"), rs2.getString("cargo"), rs2.getBoolean("activo"), rs2.getInt("nomina_base"),
								rs2.getInt("bonificaciones"));
					else
						personal = new TJefe(rs2.getString("nif"), rs2.getString("nombre"), rs2.getString("apellido"), rs2.getInt("id"),
								rs2.getInt("id_nave"), rs2.getBoolean("activo"), rs2.getInt("nomina_base"),
								rs2.getInt("responsabilidades"), rs2.getInt("bonificaciones"));
					lista.add(personal);
				}
				
			}
			ps.close();
			rs.close();
			t.commit();

			if (lista.isEmpty()) {
				
				return null;
			}

			return lista;

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
