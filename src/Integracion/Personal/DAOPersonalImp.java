package Integracion.Personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transactions.TransactionManager;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

public class DAOPersonalImp implements DAOPersonal {

	private final int ERROR = -1;

	@Override
	public int create(TPersonal personal) {

		int resultado = ERROR;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return -1;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement(
					"INSERT INTO PERSONAL (nombre,apellido,nif,id_nave,cargo,jefe,activo,nomina_base"
							+ ",responsabilidades,bonificaciones) " + "VALUES(?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, personal.getNombre());
			ps.setString(2, personal.getApellido());
			ps.setString(3, personal.getNif());
			ps.setInt(4, personal.getIdNave());
			ps.setBoolean(7, true);
			ps.setInt(8, personal.getNomina());

			if (personal.getCargo() != null) {
				ps.setString(5, personal.getCargo());
				ps.setBoolean(6, false);
				ps.setInt(9, 0);
				ps.setInt(10, personal.getBonificaciones());
			} else {
				ps.setString(5, null);
				ps.setBoolean(6, true);
				ps.setInt(9, personal.getResponsabilidades());
				ps.setInt(10, personal.getBonificaciones());
			}

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public TPersonal read(int id) {

		TPersonal personal = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return personal;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("SELECT * FROM PERSONAL WHERE id = ? AND activo = ? FOR UPDATE");
			ps.setInt(1, id);
			ps.setBoolean(2, true);

			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("cargo") != null)
					personal = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"), id,
							rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"), rs.getInt("nomina_base"),
							rs.getInt("bonificaciones"));
				else
					personal = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"), id,
							rs.getInt("id_nave"), rs.getBoolean("activo"), rs.getInt("nomina_base"),
							rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personal;
	}

	@Override
	public List<TPersonal> readAll() {

		List<TPersonal> listaPersonal = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return listaPersonal;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("SELECT * FROM PERSONAL WHERE activo = ? FOR UPDATE");
			ps.setBoolean(1, true);
			rs = ps.executeQuery();

			while (rs.next()) {
				TPersonal personal = null;
				if (rs.getBoolean("activo")) {
					if (rs.getString("cargo") != null)
						personal = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("bonificaciones"));
					else
						personal = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
					listaPersonal.add(personal);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaPersonal;
	}

	@Override
	public int update(TPersonal personal) {

		int resultado = -1;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return resultado;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("UPDATE PERSONAL SET nombre = ?, "
					+ "apellido = ?, nif = ?, id_nave = ?, cargo = ?, activo = ?" + " WHERE id = ?");
			ps.setString(1, personal.getNombre());
			ps.setString(2, personal.getApellido());
			ps.setString(3, personal.getNif());
			ps.setInt(4, personal.getIdNave());
			ps.setString(5, personal.getCargo());
			ps.setBoolean(6, personal.getActivo());
			ps.setInt(7, personal.getId());

			int exito = ps.executeUpdate();
			if (exito > 0)
				resultado = 1;

		} catch (Exception e) {
			return -1;
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public int delete(int id) {
		int resultado = -1;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return resultado;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("UPDATE PERSONAL SET activo =? WHERE id =?");
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			resultado = ps.executeUpdate();
			if (resultado > 0)
				resultado = id;
			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public TPersonal readPersonalByNif(String nif) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TPersonal pers = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return pers;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("SELECT * FROM PERSONAL WHERE nif = ? FOR UPDATE");
			ps.setString(1, nif);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("activo")) {
					if (rs.getString("cargo") != null)
						pers = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("bonificaciones"));
					else
						pers = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getBoolean("activo"), rs.getInt("nomina_base"),
								rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pers;
	}

	@Override
	public List<TPersonal> readPersonalByNave(int idNave) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<TPersonal> listaPersonal = new ArrayList<>();

		try {
			TPersonal personal = null;
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return listaPersonal;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("SELECT * FROM PERSONAL WHERE id_nave = ? FOR UPDATE");
			ps.setInt(1, idNave);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("activo")) {
					if (rs.getString("cargo") != null)
						personal = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("bonificaciones"));
					else
						personal = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getBoolean("activo"), rs.getInt("nomina_base"),
								rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
					listaPersonal.add(personal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaPersonal;
	}

	@Override
	public List<TPersonal> readPersonalByHabilidad(int idHabilidad) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<TPersonal> listaPersonal = new ArrayList<>();

		try {
			TPersonal personal = null;
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null)
				return listaPersonal;

			con = (Connection) tm.getTransaccion().getResource();
			ps = con.prepareStatement("SELECT * FROM habilidad_has_personal h INNER JOIN personal p "
					+ "ON h.Personal_id=p.id WHERE h.Habilidad_id=? FOR UPDATE");
			ps.setInt(1, idHabilidad);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("activo")) {
					if (rs.getString("cargo") != null)
						personal = new TEmpleado(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getString("cargo"), rs.getBoolean("activo"),
								rs.getInt("nomina_base"), rs.getInt("bonificaciones"));
					else
						personal = new TJefe(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellido"),
								rs.getInt("id"), rs.getInt("id_nave"), rs.getBoolean("activo"), rs.getInt("nomina_base"),
								rs.getInt("responsabilidades"), rs.getInt("bonificaciones"));
					listaPersonal.add(personal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaPersonal;
	}

}
