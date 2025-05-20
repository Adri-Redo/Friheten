/**
 * 
 */
package Integracion.Habilidad;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transactions.TransactionManager;
import Negocio.Habilidad.THabilidadPersonal;

public class DAOHabilidadPersonalImp implements DAOHabilidadPersonal {

	@Override
	public int createHabilidadPersonal(THabilidadPersonal tHabilidadPersonal) {

		int id = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return -1;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement(
					"INSERT INTO HABILIDADPERSONAL (Habilidad_id,Personal_id,activo)" + " VALUES(?,?,?)"
					);
			pstmt.setInt(1, tHabilidadPersonal.getIdHabilidad());
			pstmt.setInt(2, tHabilidadPersonal.getIdPersonal());
			pstmt.setBoolean(3, true);
			id = pstmt.executeUpdate();
			
			if (id > 0) {
				return tHabilidadPersonal.getIdPersonal();
			} else {
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return id;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int updateHabilidadPersonal(THabilidadPersonal tHabilidadPersonal) {

		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return -1;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement(
					"UPDATE HABILIDADPERSONAL SET activo = ? WHERE habilidad_id = ? AND personal_id = ?");
			pstmt.setBoolean(1, tHabilidadPersonal.getActivo());
			pstmt.setInt(2, tHabilidadPersonal.getIdHabilidad());
			pstmt.setInt(3, tHabilidadPersonal.getIdPersonal());
			int success = pstmt.executeUpdate();
			if (success > 0) {
				exito = 1;
			} else {
				exito = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}
	
	@Override
	public int deleteHabilidadPersonal(THabilidadPersonal tHabilidadPersonal) {

		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return -1;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement(
					"UPDATE HABILIDADPERSONAL SET activo = ? WHERE habilidad_id = ? AND personal_id = ?");
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, tHabilidadPersonal.getIdHabilidad());
			pstmt.setInt(3, tHabilidadPersonal.getIdPersonal());
			int success = pstmt.executeUpdate();
			if (success > 0) {
				exito = 1;
			} else {
				exito = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}

	@Override
	public THabilidadPersonal readHabilidadPersonal(int idPer, int idHab) {

		THabilidadPersonal habilidadpersonal = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return habilidadpersonal;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement(
					"SELECT * FROM HABILIDADPERSONAL WHERE personal_id = ? AND habilidad_id = ? FOR UPDATE");
			pstmt.setInt(1, idPer);
			pstmt.setInt(2, idHab);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				habilidadpersonal = new THabilidadPersonal(rs.getInt("personal_id"), rs.getInt("habilidad_id"),rs.getBoolean("activo"));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return habilidadpersonal;
	}

	@Override
	public List<THabilidadPersonal> readAll() {

		List<THabilidadPersonal> lista = new ArrayList<>();
		THabilidadPersonal transferLeido;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return lista;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM HABILIDADPERSONAL FOR UPDATE");
			rs = pstmt.executeQuery();
			while (rs.next()) {

				transferLeido = new THabilidadPersonal(rs.getInt("personal_id"), rs.getInt("habilidad_id"),rs.getBoolean("activo"));
				lista.add(transferLeido);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	@Override
	public List<THabilidadPersonal> readHabilidadVinculadaPersonal(int idHabilidad) {

		List<THabilidadPersonal> lista = new ArrayList<>();

		THabilidadPersonal transferLeido;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return lista;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM HABILIDADPERSONAL WHERE habilidad_id = ? FOR UPDATE");
			pstmt.setInt(1, idHabilidad);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				transferLeido = new THabilidadPersonal(rs.getInt("personal_id"), rs.getInt("habilidad_id"),rs.getBoolean("activo"));
				lista.add(transferLeido);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;

		/*
		 * SI DEVUELVE UNA LISTA VACIA ENTONCES LA HABILIDAD NO ESTA VINCULADA A NINGUNA
		 * PERSONA
		 */

	}

	@Override
	public List<THabilidadPersonal> readPersonalVinculadoHabilidad(int idPersonal) {

		List<THabilidadPersonal> lista = new ArrayList<>();
		THabilidadPersonal transferLeido;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return lista;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM habilidadpersonal h INNER JOIN personal p "
					+ "ON h.Personal_id=p.id WHERE h.personal_id=? FOR UPDATE"); 
			pstmt.setInt(1,idPersonal);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				transferLeido = new THabilidadPersonal(rs.getInt("personal_id"), rs.getInt("habilidad_id"),rs.getBoolean("activo"));
				lista.add(transferLeido);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

}