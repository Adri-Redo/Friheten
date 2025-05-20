/**
 * 
 */
package Integracion.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transactions.TransactionManager;
import Negocio.Compra.TCompra;

public class DAOCompraImp implements DAOCompra {

	public Integer create(TCompra compra) {
		int id = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				return -1;
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "INSERT INTO COMPRA (fecha, id_cliente, id_personal, precioTotal, activo)"
						   + " VALUES(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, compra.getFecha());
			pstmt.setInt(2, compra.getIdCliente());
			pstmt.setInt(3, compra.getIdPersonal());
			pstmt.setInt(4, compra.getPrecioTotal());
			pstmt.setBoolean(5, compra.getActivo());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			return -1;
		}
		
		finally {
			try {
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public TCompra read(Integer id) {
		TCompra compra =  null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM COMPRA WHERE id = ? AND activo = ?";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, true);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			
			if(rs.next()) {
				compra = new TCompra();
				compra.setId(id);
				compra.setFecha(rs.getString(2));
				compra.setIdCliente(rs.getInt(3));
				compra.setIdPersonal(rs.getInt(4));
				compra.setPrecioTotal(rs.getInt(5));
				compra.setActivo(rs.getBoolean(6));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return compra;
	}

	public Set<TCompra> readAll() {
		Set<TCompra> compras = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM COMPRA WHERE activo = ? FOR UPDATE";
			
			pstmt = con.prepareStatement(query);
			pstmt.setBoolean(1, true);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TCompra compra = new TCompra(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6));
				compras.add(compra);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return compras;
	}

	public Integer update(Integer id, TCompra compra) {
		Integer exito = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "UPDATE COMPRA SET fecha=?, id_cliente=?, id_personal=?, precioTotal=COALESCE(?,precioTotal) WHERE id = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, compra.getFecha());
			pstmt.setInt(2, compra.getIdCliente());
			pstmt.setInt(3, compra.getIdPersonal());
			if(compra.getPrecioTotal()==null)
				pstmt.setNull(4, 0);
			else
				pstmt.setInt(4, compra.getPrecioTotal());
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
			exito = 1;
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;

	}

	public Integer delete(Integer id) {
		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();
	
			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "UPDATE COMPRA SET activo = ? WHERE id = ?";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			//ResultSet rs = pstmt.getGeneratedKeys();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	public Set<TCompra> readByIDCliente(Integer idCliente) {
		Set<TCompra> compras = new HashSet<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM COMPRA WHERE id_cliente = ? AND activo = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idCliente);
			pstmt.setBoolean(2, true);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			
			while(rs.next()) {
				TCompra compra = new TCompra(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6));
				compras.add(compra);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (pstmt != null)pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return compras;
	}

	
}