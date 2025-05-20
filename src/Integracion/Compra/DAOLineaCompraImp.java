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
import Negocio.Compra.TLineaCompra;



public class DAOLineaCompraImp implements DAOLineaCompra {

	
	public Integer create(TLineaCompra lc) {
		
		Integer id = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "INSERT INTO lineacompra (id_mueble, id_compra, unidades, precio, activo) VALUES (?, ?, ?, ?, 1)";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, lc.getIdMueble());
			pstmt.setInt(2, lc.getIdCompra());
			pstmt.setInt(3, lc.getUnidades());
			pstmt.setDouble(4, lc.getPrecio());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt(1);
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
		return id;
	}

	public TLineaCompra read(Integer idMueble, Integer idCompra) {
		TLineaCompra lineaCompra = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT unidades, precio FROM lineacompra WHERE id_compra = ? AND id_mueble = ?";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, idCompra);
			pstmt.setInt(2, idMueble);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			
			
			if(rs.next()) {
				lineaCompra = new TLineaCompra();
				lineaCompra.setIdCompra(idCompra);
				lineaCompra.setIdMueble(idMueble);
				lineaCompra.setPrecio(rs.getInt("precio"));
				lineaCompra.setUnidades(rs.getInt("unidades"));
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
		return lineaCompra;
		
	}

	public Integer update(TLineaCompra lc) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer delete(Integer idMueble, Integer idCompra) {
		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();
	
			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "UPDATE lineacompra SET activo = ? WHERE id_compra = ? AND id_mueble = ?";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, idCompra);
			pstmt.setInt(3, idMueble);
			pstmt.executeUpdate();
			
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

	public Set<TLineaCompra> readAll() {
		Set<TLineaCompra> compras = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM lineacompra WHERE activo = ? FOR UPDATE";
			
			pstmt = con.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TLineaCompra compra = new TLineaCompra(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4));
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

	public Set<TLineaCompra> readLineasCompraPorCompra(Integer idCompra) {
		Set<TLineaCompra> compras = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM lineacompra WHERE activo = ? AND id_compra = ? FOR UPDATE";
			
			pstmt = con.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, idCompra);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();
			
			while(rs.next()) {
				TLineaCompra compra = new TLineaCompra(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4));
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


	public Set<TLineaCompra> readLineasCompraPorMueble(Integer idMueble) {
		Set<TLineaCompra> compras = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TransactionManager tm = TransactionManager.getInstance();

			if(tm.getTransaccion()==null)
				throw new IllegalStateException("Debe iniciarse una transacción");
			
			con = (Connection) tm.getTransaccion().getResource();
			
			String query = "SELECT * FROM lineacompra WHERE activo = ? AND id_mueble = ? FOR UPDATE";
			
			pstmt = con.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, idMueble);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TLineaCompra compra = new TLineaCompra(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4));
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