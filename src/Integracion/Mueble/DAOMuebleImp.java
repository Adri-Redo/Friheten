/**
 * 
 */
package Integracion.Mueble;

import Negocio.Mueble.TMueble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transactions.TransactionManager;

public class DAOMuebleImp implements DAOMueble 
{
	
	@Override
	public Integer create(TMueble mueble) 
	{
		PreparedStatement pstmt = null;
		Connection con = null;

		int resultID = -1;
		ResultSet generatedKeys = null;
		
		try 		
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return resultID;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("INSERT INTO `MUEBLE` (`precio`, `materiales`, `peso`, `x`, `y`, `z`, `nombre`, `cantidad`, `id_nave`, `activo`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setDouble(1, mueble.getPrecio());
			pstmt.setString(2, mueble.getMaterial());
			pstmt.setDouble(3, mueble.getPeso());
			pstmt.setDouble(4, mueble.getMedX());
			pstmt.setDouble(5, mueble.getMedY());
			pstmt.setDouble(6, mueble.getMedZ());
			pstmt.setString(7, mueble.getNombre());
			pstmt.setInt(8, mueble.getStock());
			pstmt.setInt(9, mueble.getIdNave());
			pstmt.setBoolean(10, true);
			
			if (pstmt.executeUpdate() > 0) 
			{
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next())
                	resultID = generatedKeys.getInt(1);                         	
			}
			
			generatedKeys.close();
			pstmt.close();
		}
		catch(Exception e){ e.printStackTrace(); }
		finally 
		{
			try 
			{ 
				pstmt.close();
				generatedKeys.close();
			}
			catch (SQLException e) {e.printStackTrace();}
		}
		
		return resultID;
	}

	@Override
	public TMueble read(Integer id) 
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		TMueble rstMueble = null;
		
		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return rstMueble;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("SELECT * FROM MUEBLE WHERE id = ? ");
			pstmt.setInt(1, id);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			
			if (rs != null)
			{
				while (rs.next()) 
					rstMueble = new TMueble(rs.getInt("id"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("cantidad"),rs.getDouble("peso"),rs.getDouble("x"),rs.getDouble("y"),rs.getDouble("z"),rs.getString("materiales"),rs.getInt("id_nave"),rs.getBoolean("activo"));					
			} 
			
			rs.close();
			pstmt.close();
		}
		catch(Exception e) { e.printStackTrace(); }
		finally 
		{
			try { pstmt.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		return rstMueble;
	}

	@Override
	public List<TMueble> readAll() 
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		List<TMueble> lstMuebles = new ArrayList<>();

		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return lstMuebles;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("SELECT * FROM MUEBLE where activo = 1");
			
			ResultSet rs = (ResultSet)pstmt.executeQuery();
			if (rs != null)
			{
				while (rs.next()) 
				{
					TMueble mueble = new TMueble(rs.getInt("id"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("cantidad"),rs.getDouble("peso"),rs.getDouble("x"),rs.getDouble("y"),rs.getDouble("z"),rs.getString("materiales"),rs.getInt("id_nave"),rs.getBoolean("activo"));					
					lstMuebles.add(mueble);
				}
			} 
			
			rs.close();
			pstmt.close();
		}
		catch(Exception e) { e.printStackTrace(); }
		finally 
		{
			try { pstmt.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		return lstMuebles;
	}
	
	@Override

	public List<TMueble> readMueblePorNave(Integer idNave) 
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		List<TMueble> lstMuebles = new ArrayList<>();

		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return lstMuebles;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("SELECT A.* FROM mydb.mueble A  INNER JOIN mydb.nave B ON A.id_nave = B.id where B.id = ?");
			pstmt.setInt(1, idNave);
			ResultSet rs = (ResultSet)pstmt.executeQuery();
			
			if (rs != null)
			{
				while (rs.next()) 
				{
					TMueble mueble = new TMueble(rs.getInt("id"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("cantidad"),rs.getDouble("peso"),rs.getDouble("x"),rs.getDouble("y"),rs.getDouble("z"),rs.getString("materiales"),rs.getInt("id_nave"),rs.getBoolean("activo"));					
					lstMuebles.add(mueble);
				}
			} 
			
			rs.close();
			pstmt.close();
		}
		catch(Exception e) { e.printStackTrace(); }
		finally 
		{
			try { pstmt.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		return lstMuebles;
	}
	
	public Integer update(TMueble mueble) 
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		int resultID = -1;
		
		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return resultID;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("UPDATE `MUEBLE` SET `precio` = ?, `materiales` = ?, `peso` = ?, `x` = ?, `y` = ?, `z` = ?, `nombre` = ?, `cantidad` = ? WHERE `mueble`.`id` = ?");
			pstmt.setDouble(1, mueble.getPrecio());
			pstmt.setString(2, mueble.getMaterial());
			pstmt.setDouble(3, mueble.getPeso());
			pstmt.setDouble(4, mueble.getMedX());
			pstmt.setDouble(5, mueble.getMedY());
			pstmt.setDouble(6, mueble.getMedZ());
			pstmt.setString(7, mueble.getNombre());
			pstmt.setInt(8, mueble.getStock());
			pstmt.setInt(9, mueble.getId());
			
			resultID = pstmt.executeUpdate();
		
			pstmt.close();

		}
		catch(Exception e) { e.printStackTrace(); }
		finally 
		{
			try { pstmt.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		return resultID > 0 ? 1: -1;
	}

	@Override
	public Integer delete(Integer id) 
	{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		int resultID = -1;

		try 
		{
			TransactionManager tm = TransactionManager.getInstance();
			if (tm.getTransaccion() == null) return resultID;
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("UPDATE `MUEBLE` SET `activo` = '0' WHERE `mueble`.`id` = ?");
			pstmt.setInt(1, id);		
			resultID = pstmt.executeUpdate();	
			
			pstmt.close();
		}
		catch(Exception e) { e.printStackTrace(); }
		finally 
		{
			try { pstmt.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		return resultID > -1 ? resultID : -1;
	}

	@Override
	public TMueble readByName(String nombre) 
	{
		TMueble mueble = null;
		for (TMueble m : this.readAll()) 
		{
			if (m.getNombre().equals(nombre) && m.getActivo()) 
				return m;
		}
		return mueble;
	}
}