/**
 * 
 */
package Integracion.Habilidad;

import Negocio.Habilidad.THabilidad;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transactions.TransactionManager;


public class DAOHabilidadImp implements DAOHabilidad {

	@Override
	public int create(THabilidad habilidad) {

		int idHab = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return -1;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("INSERT INTO HABILIDAD (nombre,activo) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, habilidad.getNombre());
			pstmt.setBoolean(2, true);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				idHab = rs.getInt(1);
			}

		} catch (SQLException e) {

			return -1;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return idHab;

	}

	@Override
	public THabilidad read(int id) {

		THabilidad hab = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return hab;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM HABILIDAD WHERE id = ? FOR UPDATE",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				hab = new THabilidad(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hab;

	}

	// tanto read como readbyname se apoyan en readAll para realizar la lectura
	@Override
	public List<THabilidad> readAll() {

		List<THabilidad> listaHabilidades = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return listaHabilidades;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT* FROM HABILIDAD FOR UPDATE");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				THabilidad h1 = new THabilidad(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"));
				listaHabilidades.add(h1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaHabilidades;
	}

	@Override
	public int update(THabilidad habilidad) {

		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return exito;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("UPDATE HABILIDAD SET nombre = ?, activo = ? WHERE ID = ?");
			pstmt.setString(1, habilidad.getNombre());
			pstmt.setBoolean(2,  habilidad.getActivo());
			pstmt.setInt(3, habilidad.getId());
			int exec = pstmt.executeUpdate();

			if (exec > 0)
				exito = habilidad.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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
	public int delete(int id) {

		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) {
				return exito;
			}

			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("UPDATE HABILIDAD SET activo =? WHERE id =?");
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);
			exito = pstmt.executeUpdate();
			
			return exito;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public THabilidad readByName(String nombre) {

		THabilidad hab = null;

		List<THabilidad> listaHabilidades = new ArrayList<>();
		listaHabilidades = this.readAll();

		for (THabilidad tH : listaHabilidades) {
			if (tH.getNombre().equals(nombre)) {

				if (tH.getActivo()) {
					hab = tH;
					break;// Sal
				}

			}
		}

		return hab;
	}

}