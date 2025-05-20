package Presentacion.FactoryPresentacion;

import javax.swing.JOptionPane;

import Presentacion.Observer;
import Presentacion.Alergeno_Cafeteria.Alergeno.GUIAlergeno;
import Presentacion.Alergeno_Cafeteria.AltaAlergeno.GUIAltaAlergeno;
import Presentacion.Alergeno_Cafeteria.BajaAlergeno.GUIBajaAlergeno;
import Presentacion.Alergeno_Cafeteria.ConsultarAlergeno.GUIConsultarAlergeno;
import Presentacion.Alergeno_Cafeteria.DesvincularAlergeno.GUIDesvincularAlergeno;
import Presentacion.Alergeno_Cafeteria.ListarAlergeno.GUIListarAlergeno;
import Presentacion.Alergeno_Cafeteria.ListarAlergenosProducto.GUIListarAlergenosProducto;
import Presentacion.Alergeno_Cafeteria.ModificarAlergeno.GUIModificarAlergeno;
import Presentacion.Alergeno_Cafeteria.VincularAlergeno.GUIVincularAlergeno;
import Presentacion.Cliente.AltaCliente.GUIAltaCliente;
import Presentacion.Cliente.BajaCliente.GUIBajaCliente;
import Presentacion.Cliente.Cliente.GUICliente;
import Presentacion.Cliente.ConsultarCliente.GUIConsultarCliente;
import Presentacion.Cliente.ListarClientes.GUIListarClientes;
import Presentacion.Cliente.ModificarCliente.GUIModificarCliente;
import Presentacion.Compra.AbrirCompra.GUIAbrirCompra;
import Presentacion.Compra.AnyadirProducto.GUIAnyadirProducto;
import Presentacion.Compra.Compra.GUICompra;
import Presentacion.Compra.ConsultarCompra.GUIConsultarCompra;
import Presentacion.Compra.DevolucionProducto.GUIDevolucionProducto;
import Presentacion.Compra.ListarCompra.GUIListarCompra;
import Presentacion.Compra.ModificarCompra.GUIModificarCompra;
import Presentacion.Compra.MostrarCarrito.GUIMostrarCarrito;
import Presentacion.Compra.MostrarCompraPorIDCliente.GUIMostrarCompraIDCliente;
import Presentacion.Compra.QuitarProducto.GUIQuitarProducto;
import Presentacion.Compra_Cafeteria.AbrirCompra.GUIAbrirCompraCafeteria;
import Presentacion.Compra_Cafeteria.AnyadirProducto.GUIAddProductCafeteria;
import Presentacion.Compra_Cafeteria.Compra_Cafeteria.GUICompraCafeteria;
import Presentacion.Compra_Cafeteria.ConsultarCompra.GUIConsultarCompraCafeteria;
import Presentacion.Compra_Cafeteria.DevolverProducto.GUIDevolverProducto;
import Presentacion.Compra_Cafeteria.ListarCompra.GUIListarCompraCafeteria;
import Presentacion.Compra_Cafeteria.ModificarCompraCafeteria.GUIModificarCompraCafeteria;
import Presentacion.Compra_Cafeteria.MostrarCarrito.GUIMostrarCarritoCafeteria;
import Presentacion.Compra_Cafeteria.QuitarProducto.GUIQuitarProductoCafeteria;
import Presentacion.Controller.Events;
import Presentacion.GUIFriheten.GUIFriheten;
import Presentacion.GUIFriheten.GUIFrihetenDAO;
import Presentacion.GUIFriheten.GUIFrihetenJPA;
import Presentacion.Habilidad.AltaHabilidad.GUIAltaHabilidad;
import Presentacion.Habilidad.BajaHabilidad.GUIBajaHabilidad;
import Presentacion.Habilidad.ConsultarHabilidad.GUIConsultarHabilidad;
import Presentacion.Habilidad.DesvincularHabilidad.GUIDesvincularHabilidad;
import Presentacion.Habilidad.Habilidad.GUIHabilidad;
import Presentacion.Habilidad.ListarHabilidad.GUIListarHabilidad;
import Presentacion.Habilidad.ListarHabilidadesDeUnaPersona.GUIListarHabilidadesDeUnaPersona;
import Presentacion.Habilidad.ModificarHabilidad.GUIModificarHabilidad;
import Presentacion.Habilidad.VincularHabilidad.GUIVincularHabilidad;
import Presentacion.Marca_Cafeteria.AltaMarca.GUIAltaMarca;
import Presentacion.Marca_Cafeteria.BajaMarca.GUIBajaMarca;
import Presentacion.Marca_Cafeteria.ConsultarMarca.GUIConsultarMarca;
import Presentacion.Marca_Cafeteria.ListarMarca.GUIListarMarca;
import Presentacion.Marca_Cafeteria.Marca.GUIMarca;
import Presentacion.Marca_Cafeteria.ModificarMarca.GUIModificarMarca;
import Presentacion.Mueble.AltaMueble.GUIAltaMueble;
import Presentacion.Mueble.BajaMueble.GUIBajaMueble;
import Presentacion.Mueble.ConsultarMueble.GUIConsultarMueble;
import Presentacion.Mueble.ListarMuebles.GUIListarMuebles;
import Presentacion.Mueble.ModificarMueble.GUIModificarMueble;
import Presentacion.Mueble.MostrarMueblePorIdNave.GUIMostrarMueblePorIdNave;
import Presentacion.Mueble.Mueble.GUIMueble;
import Presentacion.Nave.AltaNave.GUIAltaNave;
import Presentacion.Nave.BajaNave.GUIBajaNave;
import Presentacion.Nave.ConsultarNave.GUIConsultarNave;
import Presentacion.Nave.ListarNave.GUIListarNave;
import Presentacion.Nave.ModificarNave.GUIModificarNave;
import Presentacion.Nave.Nave.GUINave;
import Presentacion.Personal.AltaPersonal.GUIAltaPersonal;
import Presentacion.Personal.BajaPersonal.GUIBajaPersonal;
import Presentacion.Personal.ConsultarPersonal.GUIConsultarPersonal;
import Presentacion.Personal.ListarPersonal.GUIListarPersonal;
import Presentacion.Personal.ListarPersonalDeUnaHabilidad.GUIListarPersonalDeUnaHabilidad;
import Presentacion.Personal.ModificarPersonal.GUIModificarPersonal;
import Presentacion.Personal.MostrarPersonalPorIDNave.GUIMostrarPersonalPorIDNave;
import Presentacion.Personal.MostrarPersonalPorNif.GUIMostrarPersonalPorNif;
import Presentacion.Personal.Personal.GUIPersonal;
import Presentacion.Personal.PersonalPorNumHabilidades.GUIPersonalPorNumHabilidades;
import Presentacion.Personal.PersonalPorRangoSueldo.GUIPersonalPorRangoSueldo;
import Presentacion.Personal_Cafeteria.AltaPersonalCafeteria.GUIAltaPersonalCafeteria;
import Presentacion.Personal_Cafeteria.BajaPersonalCafeteria.GUIBajaPersonalCafeteria;
import Presentacion.Personal_Cafeteria.ConsultarPersonalCafeteria.GUIConsultarPersonalCafeteria;
import Presentacion.Personal_Cafeteria.ListarPersonalCafeteria.GUIListarPersonalCafeteria;
import Presentacion.Personal_Cafeteria.ModificarPersonalCafeteria.GUIModificarPersonalCafeteria;
import Presentacion.Personal_Cafeteria.MostrarPersonalPorIdTurno.GUIMostrarPersonalIDTurno;
import Presentacion.Personal_Cafeteria.Personal_Cafeteria.GUIPersonalCafeteria;
import Presentacion.Producto_Cafeteria.AltaProductoCafeteria.GUIAltaProductoCafeteria;
import Presentacion.Producto_Cafeteria.BajaProductoCafeteria.GUIBajaProductoCafeteria;
import Presentacion.Producto_Cafeteria.ConsultarProductoCafeteria.GUIConsultarProductoCafeteria;
import Presentacion.Producto_Cafeteria.ConsultarProductoCafeteriaPorAlergerno.GUIConsultarProductoCafeteriaPorAlergeno;
import Presentacion.Producto_Cafeteria.ConsultarProductoCafeteriaPorMarca.GUIConsultarProductoCafeteriaMarca;
import Presentacion.Producto_Cafeteria.ListarProductoCafeteria.GUIListarProductoCafeteria;
import Presentacion.Producto_Cafeteria.ModificarProductoCafeteria.GUIModificarProductoCafeteria;
import Presentacion.Producto_Cafeteria.Producto_Cafeteria.GUIProducto_Cafeteria;
import Presentacion.Turno_Cafeteria.AltaTurnoCafeteria.GUIAltaTurnoCafeteria;
import Presentacion.Turno_Cafeteria.BajaTurnoCafeteria.GUIBajaTurnoCafeteria;
import Presentacion.Turno_Cafeteria.CalcularNomina.GUICalcularNomina;
import Presentacion.Turno_Cafeteria.ConsultarTurnoCafeteria.GUIConsultarTurnoCafeteria;
import Presentacion.Turno_Cafeteria.ListarTurnoCafeteria.GUIListarTurnoCafeteria;
import Presentacion.Turno_Cafeteria.ModificarTurnoCafeteria.GUIModificarTurnoCafeteria;
import Presentacion.Turno_Cafeteria.TurnoCafeteria.GUITurno_Cafeteria;

public class FactoryPresentacionImp extends FactoryPresentacion {

	@Override
	public Observer generateGUI(int event) {

		Observer vista = null;

		switch (event) {

		/************************* GUI FRIHETEN *******************************/

		case Events.MAIN_VIEW:

			vista = new GUIFriheten();
			break;

		case Events.CAFETERIA_VIEW:

			vista = new GUIFrihetenJPA();
			break;
		case Events.TIENDA_MUEBLES_VIEW:
			vista = new GUIFrihetenDAO();
			break;
		/************************* GUI HABILIDAD *******************************/

		case Events.HABILIDAD_VIEW:

			vista = new GUIHabilidad();
			break;

		case Events.ALTA_HABILIDAD_VIEW:

			vista = new GUIAltaHabilidad();

			break;

		case Events.BAJA_HABILIDAD_VIEW:

			vista = new GUIBajaHabilidad();
			break;

		case Events.CONSULTAR_HABILIDAD_VIEW:

			vista = new GUIConsultarHabilidad();
			break;

		case Events.CONSULTAR_HABILIDAD_KO_VIEW:

			JOptionPane.showMessageDialog(null, "Error al consultar habilidad", "Error", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.DESVINCULAR_HABILIDAD_VIEW:

			vista = new GUIDesvincularHabilidad();
			break;

		case Events.LISTAR_HABILIDAD_VIEW:

			vista = new GUIListarHabilidad();

			break;
		case Events.MODIFICAR_HABILIDAD_VIEW:

			vista = new GUIModificarHabilidad();

			break;

		case Events.VINCULAR_HABILIDAD_VIEW:

			vista = new GUIVincularHabilidad();

			break;

		case Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW:

			vista = new GUIListarHabilidadesDeUnaPersona();

			break;


		/************************* GUI PERSONAL *******************************/

		case Events.PERSONAL_VIEW:

			vista = new GUIPersonal();

			break;
		case Events.ALTA_PERSONAL_VIEW:

			vista = new GUIAltaPersonal();

			break;

		case Events.BAJA_PERSONAL_VIEW:

			vista = new GUIBajaPersonal();
			break;

		case Events.CONSULTAR_PERSONAL_VIEW:

			vista = new GUIConsultarPersonal();

			break;

		case Events.LISTAR_PERSONAL_VIEW:

			vista = new GUIListarPersonal();

			break;

		case Events.MODIFICAR_PERSONAL_VIEW:

			vista = new GUIModificarPersonal();

			break;

		case Events.MOSTRAR_PERSONAL_PORNIF_VIEW:

			vista = new GUIMostrarPersonalPorNif();
			break;

		case Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW:

			vista = new GUIMostrarPersonalPorIDNave();
			break;

		case Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW:

			vista = new GUIListarPersonalDeUnaHabilidad();
			break;
		case Events.MOSTRAR_PERSONAL_PORNUMHAB_VIEW:
			vista = new GUIPersonalPorNumHabilidades();
			break;
		case Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_VIEW:
			vista = new GUIPersonalPorRangoSueldo();
			break;

		/************************* GUI NAVE *******************************/

		case Events.NAVE_VIEW:
			vista = new GUINave();
			break;

		case Events.ALTA_NAVE_VIEW:
			vista = new GUIAltaNave();
			break;

		case Events.BAJA_NAVE_VIEW:
			vista = new GUIBajaNave();
			break;

		case Events.CONSULTAR_NAVE_VIEW:
			vista = new GUIConsultarNave();
			break;

		case Events.LISTAR_NAVE_VIEW:
			vista = new GUIListarNave();
			break;

		case Events.MODIFICAR_NAVE_VIEW:
			vista = new GUIModificarNave();
			break;

		/************************* GUI MUEBLE *******************************/

		case Events.MUEBLE_VIEW:
			vista = new GUIMueble();
			break;

		case Events.ALTA_MUEBLE_VIEW:
			vista = new GUIAltaMueble();
			break;

		case Events.BAJA_MUEBLE_VIEW:
			vista = new GUIBajaMueble();
			break;

		case Events.CONSULTAR_MUEBLE_VIEW:
			vista = new GUIConsultarMueble();
			break;

		case Events.LISTAR_MUEBLE_VIEW:
			vista = new GUIListarMuebles();
			break;

		case Events.MODIFICAR_MUEBLE_VIEW:
			vista = new GUIModificarMueble();
			break;
		case Events.MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW:
			vista = new GUIMostrarMueblePorIdNave();
			break;

		/************************* GUI CLIENTE *******************************/

		case Events.CLIENTE_VIEW:
			vista = new GUICliente();
			break;

		case Events.ALTA_CLIENTE_VIEW:
			vista = new GUIAltaCliente();
			break;

		case Events.BAJA_CLIENTE_VIEW:
			vista = new GUIBajaCliente();
			break;

		case Events.CONSULTAR_CLIENTE_VIEW:
			vista = new GUIConsultarCliente();
			break;

		case Events.LISTAR_CLIENTE_VIEW:
			vista = new GUIListarClientes();
			break;

		case Events.MODIFICAR_CLIENTE_VIEW:
			vista = new GUIModificarCliente();
			break;

		/************************* GUI COMPRA *******************************/

		case Events.COMPRA_VIEW:
			vista = new GUICompra();
			break;

		case Events.ABRIR_COMPRA_VIEW:
			vista = new GUIAbrirCompra();
			break;
		case Events.RES_ABRIR_COMPRA_OK_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_ABRIR_COMPRA_KO_VIEW:
			vista = new GUICompra();
			break;

		case Events.ANYADIR_PRODUCTO_VIEW:
			vista = new GUIAnyadirProducto();
			break;
		case Events.RES_ANYADIR_PRODUCTO_OK_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_ANYADIR_PRODUCTO_KO_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_MOSTRAR_CARRITO_OK_VIEW:
			vista = new GUICompra();
			break;
		case Events.CERRAR_COMPRA_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_CERRAR_COMPRA_OK_VIEW:

			break;

		case Events.RES_CERRAR_COMPRA_KO_VIEW:

			break;

		case Events.CONSULTAR_COMPRA_VIEW:
			vista = new GUIConsultarCompra();
			break;

		case Events.RES_CONSULTAR_COMPRA_ID_OK_VIEW:
			vista = new GUIConsultarCompra();
			break;

		case Events.RES_CONSULTAR_COMPRA_OK_VIEW:
			vista = new GUIConsultarCompra();
			break;

		case Events.RES_CONSULTAR_COMPRA_KO_VIEW:

			break;

		case Events.DEVOLUCION_PRODUCTO_VIEW:
			vista = new GUIDevolucionProducto();
			break;

		case Events.RES_DEVOLUCION_PRODUCTO_OK_VIEW:
			vista = new GUIDevolucionProducto();
			break;
		case Events.RES_DEVOLUCION_PRODUCTO_ID_OK_VIEW:
			vista = new GUIDevolucionProducto();
			break;

		case Events.RES_DEVOLUCION_PRODUCTO_KO_VIEW:

			break;

		case Events.LISTAR_COMPRA_VIEW:
			vista = new GUIListarCompra();
			break;

		case Events.MOSTRAR_COMPRA_POR_ID_CLIENTE_VIEW:
			vista = new GUIMostrarCompraIDCliente();
			break;

		case Events.RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_OK_VIEW:
			vista = new GUIMostrarCompraIDCliente();
			break;

		case Events.RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_KO_VIEW:

			break;

		case Events.QUITAR_PRODUCTO_VIEW:
			vista = new GUIQuitarProducto();
			break;

		case Events.RES_QUITAR_PRODUCTO_OK_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_QUITAR_PRODUCTO_KO_VIEW:
			vista = new GUICompra();
			break;
		case Events.MOSTRAR_CARRITO_VIEW:
			vista = new GUIMostrarCarrito();
			break;

		case Events.ERROR_CONSULTAR_COMPRA:
			vista = new GUICompra();
			break;

		case Events.MODIFICAR_COMPRA_VIEW:
			vista = new GUIModificarCompra();
			break;

		case Events.RES_MODIFICAR_COMPRA_OK_VIEW:
			vista = new GUICompra();
			break;

		case Events.RES_MODIFICAR_COMPRA_KO_VIEW:
			vista = new GUICompra();
			break;

		case Events.CANCEL_VIEW:
			break;


		 /*************************************************************************/
		 /*************************************************************************/
		 /*************************************************************************/
						/**        CAFETERIA GUI 	 **/
		 /*************************************************************************/
		 /*************************************************************************/
		 /*************************************************************************/

		/************************* GUI PRODUCTO CAFETERIA *******************************/

		case Events.PRODUCTO_VIEW:
			vista = new GUIProducto_Cafeteria();
			break;

		case Events.ALTA_PRODUCTO_VIEW:
			vista = new GUIAltaProductoCafeteria();
			break;		
			
		case Events.BAJA_PRODUCTO_VIEW:
			vista = new GUIBajaProductoCafeteria();
			break;			

		case Events.CONSULTAR_PRODUCTO_VIEW:
			vista = new GUIConsultarProductoCafeteria();
			break;
			
		case Events.LISTAR_PRODUCTO_VIEW:
			vista = new GUIListarProductoCafeteria();
			break;
			
		case Events.MODIFICAR_PRODUCTO_VIEW:
			vista = new GUIModificarProductoCafeteria();
			break;			

		case Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW:
			vista = new GUIConsultarProductoCafeteriaPorAlergeno();
			break;	
			
		case Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW:
			vista = new GUIConsultarProductoCafeteriaMarca();
			break;			
			
		/************************* GUI MARCA CAFETERIA *******************************/

		case Events.MARCA_VIEW:
			vista = new GUIMarca();
			break;

		case Events.ALTA_MARCA_VIEW:
			vista = new GUIAltaMarca();
			break;

		case Events.BAJA_MARCA_VIEW:
			vista = new GUIBajaMarca();
			break;

		case Events.LISTAR_MARCA_VIEW:
			vista = new GUIListarMarca();
			break;

		case Events.CONSULTAR_MARCA_VIEW:
			vista = new GUIConsultarMarca();
			break;

		case Events.MODIFICAR_MARCA_VIEW:
			vista = new GUIModificarMarca();
			break;
			
		/************************* GUI TURNO CAFETERIA *******************************/

		case Events.TURNO_VIEW:
			vista = new GUITurno_Cafeteria();
			break;

		case Events.ALTA_TURNO_VIEW:
			vista = new GUIAltaTurnoCafeteria();
			break;

		case Events.BAJA_TURNO_VIEW:
			vista = new GUIBajaTurnoCafeteria();
			break;

		case Events.CONSULTAR_TURNO_VIEW:
			vista = new GUIConsultarTurnoCafeteria();
			break;

		case Events.LISTAR_TURNO_VIEW:
			vista = new GUIListarTurnoCafeteria();
			break;

		case Events.MODIFICAR_TURNO_VIEW:
			vista = new GUIModificarTurnoCafeteria();
			break;
		case Events.TURNO_CALCULAR_NOMINA_VIEW:
			vista = new GUICalcularNomina();
			break;

		/************************* GUI ALERGENO*******************************/
		case Events.ALERGENO_VIEW:
			vista = new GUIAlergeno();
			break;
		case Events.ALTA_ALERGENO_VIEW:
			vista = new GUIAltaAlergeno();
			break;
		case Events.BAJA_ALERGENO_VIEW:
			vista = new GUIBajaAlergeno();
			break;
		case Events.LISTAR_ALERGENO_VIEW:
			vista = new GUIListarAlergeno();
			break;
		case Events.MODIFICAR_ALERGENO_VIEW:
			vista = new GUIModificarAlergeno();
			break;
		case Events.CONSULTAR_ALERGENO_VIEW:
			vista = new GUIConsultarAlergeno();
			break;
		case Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW:
			vista = new GUIListarAlergenosProducto();
			break;
		case Events.VINCULAR_ALERGENO_VIEW:
			vista = new GUIVincularAlergeno();
			break;
		case Events.DESVINCULAR_ALERGENO_VIEW:
			vista = new GUIDesvincularAlergeno();
			break;
		case Events.ALERGENO_CANCEL_VIEW:
			vista = new GUIAlergeno();
			break;
			/************************* GUI PERSONAL CAFETERIA*******************************/

		case Events.PERSONALCAFETERIA_VIEW:
			vista = new GUIPersonalCafeteria();
			break;
		case Events.ALTA_PERSONALCAFETERIA_VIEW:
			vista = new GUIAltaPersonalCafeteria();
			break;
		case Events.BAJA_PERSONALCAFETERIA_VIEW:
			vista = new GUIBajaPersonalCafeteria();
			break;
		case Events.CONSULTAR_PERSONALCAFETERIA_VIEW:
			vista = new GUIConsultarPersonalCafeteria();
			break;
		case Events.LISTAR_PERSONALCAFETERIA_VIEW:
			vista = new GUIListarPersonalCafeteria();
			break;
		case Events.MODIFICAR_PERSONALCAFETERIA_VIEW:
			vista = new GUIModificarPersonalCafeteria();
			break;
		case Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_VIEW:
			vista = new GUIMostrarPersonalIDTurno();
			break;



		/************************* GUI COMPRA_CAFETERIA*******************************/


		case Events.COMPRA_CAF_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.ABRIR_COMPRA_CAF_VIEW:
			vista = new GUIAbrirCompraCafeteria();
			break;
		case Events.RES_ABRIR_COMPRA_CAF_OK_VIEW:
			vista = new GUICompraCafeteria();
			JOptionPane.showMessageDialog(null, "La compra se ha abierto con éxito", "Success", JOptionPane.INFORMATION_MESSAGE);
			break;

		case Events.RES_ABRIR_COMPRA_CAF_KO_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.ANYADIR_PRODUCTO_CAF_VIEW:
			vista = new GUIAddProductCafeteria();
			break;
		case Events.RES_ANYADIR_PRODUCTO_CAF_OK_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.RES_ANYADIR_PRODUCTO_CAF_KO_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.RES_MOSTRAR_CARRITO_CAF_OK_VIEW:
			vista = new GUICompraCafeteria();
			break;
		case Events.CERRAR_COMPRA_CAF_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.RES_CERRAR_COMPRA_CAF_OK_VIEW:
			break;

		case Events.RES_CERRAR_COMPRA_CAF_KO_VIEW:

			break;

		case Events.CONSULTAR_COMPRA_CAF_VIEW:
			vista = new GUIConsultarCompraCafeteria();
			break;

		case Events.RES_CONSULTAR_COMPRA_CAF_ID_OK_VIEW:
			vista = new GUIConsultarCompraCafeteria();
			break;

		case Events.RES_CONSULTAR_COMPRA_CAF_OK_VIEW:
			vista = new GUIConsultarCompraCafeteria();
			break;

		case Events.RES_CONSULTAR_COMPRA_CAF_KO_VIEW:

			break;

		case Events.DEVOLUCION_PRODUCTO_CAF_VIEW:
			vista = new GUIDevolverProducto();
			break;

		case Events.RES_DEVOLUCION_PRODUCTO_CAF_OK_VIEW:
			vista = new GUIDevolverProducto();
			break;
		case Events.RES_DEVOLUCION_PRODUCTO_CAF_ID_OK_VIEW:
			vista = new GUIDevolverProducto();
			break;

		case Events.RES_DEVOLUCION_PRODUCTO_CAF_KO_VIEW:

			break;

		case Events.LISTAR_COMPRA_CAF_VIEW:
			vista = new GUIListarCompraCafeteria();
			break;

		case Events.QUITAR_PRODUCTO_CAF_VIEW:
			vista = new GUIQuitarProductoCafeteria();
			break;

		case Events.RES_QUITAR_PRODUCTO_CAF_OK_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.RES_QUITAR_PRODUCTO_CAF_KO_VIEW:
			vista = new GUICompraCafeteria();
			break;
		case Events.MOSTRAR_CARRITO_CAF_VIEW:
			vista = new GUIMostrarCarritoCafeteria();
			break;

		case Events.ERROR_CONSULTAR_COMPRA_CAF:
			vista = new GUICompraCafeteria();
			break;

		case Events.MODIFICAR_COMPRA_CAF_VIEW:
			vista = new GUIModificarCompraCafeteria();
			break;

		case Events.RES_MODIFICAR_COMPRA_CAF_OK_VIEW:
			vista = new GUICompraCafeteria();
			break;

		case Events.RES_MODIFICAR_COMPRA_CAF_KO_VIEW:
			vista = new GUICompraCafeteria();
			break;

		}
		return vista;
	}
}
