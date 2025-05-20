/**
 * 
 */
package Integracion.Nave;

import Negocio.Nave.TNave;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.TransactionManager;



public class DAONaveImp implements DAONave {

	//private String path = "jdbc:mysql://localhost:3306/friheten";

	public Integer create(TNave nave) {
	
		int idNave = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return idNave;
			}
			
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("INSERT INTO NAVE (localizacion,nombre,capacidad,activo) VALUES(?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nave.getLocalizacion());
			pstmt.setString(2, nave.getNombre());
			pstmt.setInt(3, nave.getCapacidad());
			pstmt.setBoolean(4, true);
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				idNave = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs!= null)rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return idNave;
	}

	

	public TNave read(Integer id) {
		
		TNave nave = new TNave();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return nave;
			}
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM NAVE WHERE id = ? AND activo = ? FOR UPDATE"
					, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			
			pstmt.setBoolean(2, true);
			
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getBoolean("activo")) {
				
				nave.setId(rs.getInt("id"));
				nave.setLocalizacion(rs.getString("localizacion"));
				nave.setNombre(rs.getString("nombre"));
				nave.setCapacidad(rs.getInt("capacidad"));
				nave.setActivo(rs.getBoolean("activo"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs!= null)rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nave;
	}

	
	@Override
	public List<TNave> readAll(){
		
		List<TNave> lista_naves = new ArrayList<TNave>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return lista_naves;
			}
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM nave WHERE activo = ? FOR UPDATE");
			pstmt.setBoolean(1, true);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				TNave nave = new TNave(rs.getInt("id"), rs.getString("localizacion"),rs.getString("nombre"),
						rs.getInt("capacidad"),rs.getBoolean("activo"));
				
				lista_naves.add(nave);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs!= null)rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista_naves;
	}
	
	
	@Override
	public Integer update(TNave nave) {
		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return exito;
			}
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("UPDATE NAVE SET nombre = ?, capacidad = ?, localizacion = ? WHERE id = ?");
			pstmt.setString(1, nave.getNombre());
			pstmt.setInt(2, nave.getCapacidad());
			pstmt.setString(3, nave.getLocalizacion());
			pstmt.setInt(4, nave.getId());
			
			int exec = pstmt.executeUpdate();
			if(exec > 0) exito = 1;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return exito;
	}
	
	
	@Override
	public Integer delete(Integer id) {
		
		int idNave = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return idNave;
			}
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("UPDATE NAVE SET activo =? WHERE id =?");
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);
			
			idNave = pstmt.executeUpdate();
			
			
			if(idNave > 0) idNave = id;
			return idNave;
		
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				pstmt.close();	
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	@Override
	public TNave readByName(String name) {
		
		
		TNave nave = null;
		List<TNave>lista_naves = new ArrayList<>();
		lista_naves = FactoryIntgr.getInstance().generateDAONave().readAll();
		
		for (TNave ship: lista_naves) {
			if(ship.getNombre().equals(name)) {
				if(ship.getActivo()) {
					nave = ship;
					break;
				}
			}
		}
		return nave;
	}
	
	
}
	