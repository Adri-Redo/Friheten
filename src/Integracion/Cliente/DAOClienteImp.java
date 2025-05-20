/**
 * 
 */
package Integracion.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transactions.TransactionManager;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TIndividual;

public class DAOClienteImp implements DAOCliente{

	
	@Override
	public int create(TCliente cliente) {
		int idCli = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
				return -1;
				
			con = (Connection) tm.getTransaccion().getResource();
			
			pstmt = con.prepareStatement("INSERT INTO CLIENTE (usuario,contrasenya,correo,nombre,tipo,activo,cp) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, cliente.getUsuario());
			pstmt.setString(2, cliente.getContrasena());
			pstmt.setString(3, cliente.getCorreo());
			pstmt.setBoolean(5, cliente.getTipo());
			pstmt.setBoolean(6, true);
			if (cliente.getTipo()) { 
				TEmpresa empre =  (TEmpresa) cliente;
				pstmt.setString(4, empre.getNombre());
				pstmt.setNull(7, Types.INTEGER);
			} 
			else {
				TIndividual indi = (TIndividual) cliente;
				pstmt.setInt(7, indi.getCodigoPostal());
				pstmt.setNull(4, Types.VARCHAR);
			}
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				idCli = rs.getInt(1);
			} 
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idCli;
	}
	

	@Override
	public int update(TCliente cliente) {
		int exito = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null){
				return exito;
			}
			con = (Connection) tm.getTransaccion().getResource();
			if(cliente.getTipo()) {
				pstmt = con.prepareStatement("UPDATE CLIENTE " + "SET USUARIO = ?, " + 
				"CORREO = ?, " + "CONTRASENYA = ?, " + "NOMBRE = ?, " + "ACTIVO = ? " + "WHERE ID = ?");
				pstmt.setString(4, ((TEmpresa)cliente).getNombre());
			}
			else {
				pstmt = con.prepareStatement("UPDATE CLIENTE " + "SET USUARIO = ?, " + 
				"CORREO = ?, " + "CONTRASENYA = ?, " + "CP = ?, " + "ACTIVO = ? " + "WHERE ID = ?");
				pstmt.setInt(4, ((TIndividual)cliente).getCodigoPostal());
			}
			pstmt.setString(1, cliente.getUsuario());
			pstmt.setString(2, cliente.getCorreo());
			pstmt.setString(3, cliente.getContrasena());
			pstmt.setInt(6, cliente.getId());
			pstmt.setBoolean(5, cliente.getActivo());
			int exec = pstmt.executeUpdate();
				
			
			if (exec > 0) exito = cliente.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	@Override
	public int delete(Integer id) {

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
			pstmt = con.prepareStatement("UPDATE CLIENTE SET activo =? WHERE id =?");
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);
			exito = pstmt.executeUpdate();
			if(exito > 0) exito = id;
			return exito;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<TCliente> readAll() {
		
		List<TCliente> listaClientes = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null)
			{
				return listaClientes;
			}
			
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT* FROM CLIENTE");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				TCliente c1;
				if(!rs.getBoolean("tipo"))
					c1 = new TIndividual(rs.getInt("id"),rs.getString("usuario"), rs.getString("contrasenya"),
							rs.getString("correo"), rs.getBoolean("activo"), rs.getInt("cp"));
				else
					c1 = new TEmpresa(rs.getInt("id"),rs.getString("usuario"), rs.getString("contrasenya"),
							rs.getString("correo"), rs.getBoolean("activo"), rs.getString("nombre"));
				listaClientes.add(c1);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaClientes;
	}
	
	@Override
	public TCliente read(Integer id) {
		TCliente cli = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			TransactionManager tm = TransactionManager.getInstance();
			if(tm.getTransaccion() == null) {
				return cli;
			}
			con = (Connection) tm.getTransaccion().getResource();
			pstmt = con.prepareStatement("SELECT * FROM CLIENTE WHERE id = ? FOR UPDATE", 
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(!rs.getBoolean("tipo")) {
					cli = new TIndividual(rs.getInt("id"), rs.getString("usuario"), rs.getString("contrasenya"), 
							rs.getString("correo"), rs.getBoolean("activo"), rs.getInt("cp"));
				}
				else {
					cli = new TEmpresa(rs.getInt("id"), rs.getString("usuario"), rs.getString("contrasenya"), 
							rs.getString("correo"), rs.getBoolean("activo"), rs.getString("nombre"));
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cli;
	}
	
	/* NO SE USA, PERO NO LO QUITO PORQUE ANTONIO NOS DIJO QUE HABÍA QUE METERLO */
	@Override
	public TCliente readByUser(String usuario) {
		TCliente cli = null;
		List<TCliente> listaClientes = new ArrayList<>();
		listaClientes = this.readAll();

		for (TCliente tc : listaClientes) {
			if (tc.getUsuario().equals(usuario)) {
				if (tc.getActivo()) {
					cli = tc;
					break;// Sal
				}
				else
					break;
			}
		}

		return cli;
	}

}