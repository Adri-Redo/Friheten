package Presentacion.Command;

import Presentacion.Alergeno_Cafeteria.Alergeno.GUIAlergeno;
import Presentacion.Alergeno_Cafeteria.AltaAlergeno.GUIAltaAlergeno;
import Presentacion.Alergeno_Cafeteria.BajaAlergeno.GUIBajaAlergeno;
import Presentacion.Alergeno_Cafeteria.Commands.Command_AlergenoCancel_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_Alergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_AltaAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_AltaAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_AltaAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_BajaAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_BajaAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_BajaAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ConsultarAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ConsultarAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ConsultarAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_DesvincularAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_DesvincularAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_DesvincularAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ListarAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ListarAlergenosProductoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ListarAlergenosProductoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_ListarAlergenosProducto_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_UpdateAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_UpdateAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_UpdateAlergeno_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_VincularAlergenoKo_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_VincularAlergenoOk_View;
import Presentacion.Alergeno_Cafeteria.Commands.Command_VincularAlergeno_View;
import Presentacion.Alergeno_Cafeteria.DesvincularAlergeno.GUIDesvincularAlergeno;
import Presentacion.Alergeno_Cafeteria.ListarAlergeno.GUIListarAlergeno;
import Presentacion.Alergeno_Cafeteria.ListarAlergenosProducto.GUIListarAlergenosProducto;
import Presentacion.Alergeno_Cafeteria.ModificarAlergeno.GUIModificarAlergeno;
import Presentacion.Alergeno_Cafeteria.VincularAlergeno.GUIVincularAlergeno;
import Presentacion.Cliente.Commands.Command_AltaClienteOk_View;
import Presentacion.Cliente.Commands.Command_AltaCliente_View;
import Presentacion.Cliente.Commands.Command_BajaClienteOk_View;
import Presentacion.Cliente.Commands.Command_BajaCliente_View;
import Presentacion.Cliente.Commands.Command_ClientKoView;
import Presentacion.Cliente.Commands.Command_Cliente_View;
import Presentacion.Cliente.Commands.Command_ConsultarClienteOk_View;
import Presentacion.Cliente.Commands.Command_ConsultarCliente_View;
import Presentacion.Cliente.Commands.Command_ListarCliente_View;
import Presentacion.Cliente.Commands.Command_ModificarClienteOk_View;
import Presentacion.Cliente.Commands.Command_ModificarCliente_View;
import Presentacion.Compra.Commands.Command_AbrirCompra_View;
import Presentacion.Compra.Commands.Command_AnyadirProducto_View;
import Presentacion.Compra.Commands.Command_CerrarCompra_View;
import Presentacion.Compra.Commands.Command_Compra_View;
import Presentacion.Compra.Commands.Command_ConsultarCompra_View;
import Presentacion.Compra.Commands.Command_DevolucionProducto_View;
import Presentacion.Compra.Commands.Command_ListarCompra_View;
import Presentacion.Compra.Commands.Command_ModificarCompra_View;
import Presentacion.Compra.Commands.Command_MostrarCarrito_View;
import Presentacion.Compra.Commands.Command_MostrarCompra_IDCliente_View;
import Presentacion.Compra.Commands.Command_QuitarProducto_View;
import Presentacion.Compra.Commands.Command_Res_AbrirCompra_KO_View;
import Presentacion.Compra.Commands.Command_Res_AbrirCompra_OK_View;
import Presentacion.Compra.Commands.Command_Res_AnyadirProducto_KO_View;
import Presentacion.Compra.Commands.Command_Res_AnyadirProducto_OK_View;
import Presentacion.Compra.Commands.Command_Res_ConsultarCompra_KO_View;
import Presentacion.Compra.Commands.Command_Res_ConsultarCompra_OK_View;
import Presentacion.Compra.Commands.Command_Res_DevolucionProducto_ID_OK_View;
import Presentacion.Compra.Commands.Command_Res_DevolucionProducto_KO_View;
import Presentacion.Compra.Commands.Command_Res_DevolucionProducto_OK_View;
import Presentacion.Compra.Commands.Command_Res_ModificarCompra_KO_View;
import Presentacion.Compra.Commands.Command_Res_ModificarCompra_OK_View;
import Presentacion.Compra.Commands.Command_Res_MostrarCarrito_OK_View;
import Presentacion.Compra.Commands.Command_Res_MostrarCompra_IDCliente_OK_View;
import Presentacion.Compra.Commands.Command_Res_QuitarProducto_KO_View;
import Presentacion.Compra.Commands.Command_Res_QuitarProducto_OK_View;
import Presentacion.Compra_Cafeteria.Commands.Command_AbrirCompra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_AnyadirProducto_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_CerrarCompra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Compra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_ConsultarCompra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_DevolucionProducto_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_ListarCompra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_ModificarCompra_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_MostrarCarrito_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_QuitarProducto_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_AbrirCompra_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_AbrirCompra_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_AnyadirProducto_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_AnyadirProducto_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_ConsultarCompra_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_ConsultarCompra_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_DevolucionProducto_ID_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_DevolucionProducto_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_DevolucionProducto_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_ModificarCompra_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_ModificarCompra_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_MostrarCarrito_OK_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_QuitarProducto_KO_View_Cafeteria;
import Presentacion.Compra_Cafeteria.Commands.Command_Res_QuitarProducto_OK_View_Cafeteria;
import Presentacion.Controller.Events;
import Presentacion.GUIFriheten.Command_Friheten;
import Presentacion.GUIFriheten.Command_FrihetenDAO;
import Presentacion.GUIFriheten.Command_FrihetenJPA;
import Presentacion.Habilidad.Commands.Command_AltaHabilidadOk_View;
import Presentacion.Habilidad.Commands.Command_AltaHabilidad_View;
import Presentacion.Habilidad.Commands.Command_BajaHabilidadOk_View;
import Presentacion.Habilidad.Commands.Command_BajaHabilidad_View;
import Presentacion.Habilidad.Commands.Command_ConsultarHabilidadOk_View;
import Presentacion.Habilidad.Commands.Command_ConsultarHabilidad_View;
import Presentacion.Habilidad.Commands.Command_DesvincularHabilidadOk_View;
import Presentacion.Habilidad.Commands.Command_DesvincularHabilidad_View;
import Presentacion.Habilidad.Commands.Command_HabilidadCancel_View;
import Presentacion.Habilidad.Commands.Command_Habilidad_View;
import Presentacion.Habilidad.Commands.Command_ListarHabilidades_View;
import Presentacion.Habilidad.Commands.Command_ModificarHabilidadesOk_View;
import Presentacion.Habilidad.Commands.Command_ModificarHabilidades_View;
import Presentacion.Habilidad.Commands.Command_MostrarHabilidadesDeUnaPersonaOk_View;
import Presentacion.Habilidad.Commands.Command_MostrarHabilidadesDeUnaPersona_View;
import Presentacion.Habilidad.Commands.Command_VincularHabilidadOk_View;
import Presentacion.Habilidad.Commands.Command_VincularHabilidad_View;
import Presentacion.Marca_Cafeteria.Commands.Command_AltaMarcaKo_View;
import Presentacion.Marca_Cafeteria.Commands.Command_AltaMarcaOk_View;
import Presentacion.Marca_Cafeteria.Commands.Command_AltaMarca_View;
import Presentacion.Marca_Cafeteria.Commands.Command_BajaMarcaKo_View;
import Presentacion.Marca_Cafeteria.Commands.Command_BajaMarcaOk_View;
import Presentacion.Marca_Cafeteria.Commands.Command_BajaMarca_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ConsultarMarcaKo_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ConsultarMarcaOk_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ConsultarMarca_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ListarMarca_View;
import Presentacion.Marca_Cafeteria.Commands.Command_Marca_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ModificarMarcaKo_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ModificarMarcaOk_View;
import Presentacion.Marca_Cafeteria.Commands.Command_ModificarMarca_View;
import Presentacion.Mueble.Commands.Command_AltaMuebleKO_View;
import Presentacion.Mueble.Commands.Command_AltaMuebleOk_View;
import Presentacion.Mueble.Commands.Command_AltaMueble_View;
import Presentacion.Mueble.Commands.Command_BajaMuebleKO_View;
import Presentacion.Mueble.Commands.Command_BajaMuebleOK_View;
import Presentacion.Mueble.Commands.Command_BajaMueble_View;
import Presentacion.Mueble.Commands.Command_ConsultaMuebleKO_View;
import Presentacion.Mueble.Commands.Command_ConsultaMuebleOK_View;
import Presentacion.Mueble.Commands.Command_ConsultaMueble_View;
import Presentacion.Mueble.Commands.Command_ListarMueble_View;
import Presentacion.Mueble.Commands.Command_ModificarMuebleKO_View;
import Presentacion.Mueble.Commands.Command_ModificarMuebleOK_View;
import Presentacion.Mueble.Commands.Command_ModificarMueble_View;
import Presentacion.Mueble.Commands.Command_MostrarMueblePorIdNaveKO_View;
import Presentacion.Mueble.Commands.Command_MostrarMueblePorIdNaveOK_View;
import Presentacion.Mueble.Commands.Command_MostrarMueblePorIdNave_View;
import Presentacion.Mueble.Commands.Command_Mueble_View;
import Presentacion.Nave.Commands.Command_AltaNaveKo_View;
import Presentacion.Nave.Commands.Command_AltaNaveOk_View;
import Presentacion.Nave.Commands.Command_AltaNave_View;
import Presentacion.Nave.Commands.Command_BajaNaveKo_View;
import Presentacion.Nave.Commands.Command_BajaNaveOk_View;
import Presentacion.Nave.Commands.Command_BajaNave_View;
import Presentacion.Nave.Commands.Command_ConsultarNaveKo_View;
import Presentacion.Nave.Commands.Command_ConsultarNaveOk_View;
import Presentacion.Nave.Commands.Command_ConsultarNave_View;
import Presentacion.Nave.Commands.Command_ListarNave_View;
import Presentacion.Nave.Commands.Command_ModificarNaveKo_View;
import Presentacion.Nave.Commands.Command_ModificarNaveOk_View;
import Presentacion.Nave.Commands.Command_ModificarNave_View;
import Presentacion.Nave.Commands.Command_Nave_View;
import Presentacion.Personal.Commands.Command_AltaPersonalKO_View;
import Presentacion.Personal.Commands.Command_AltaPersonalOK_View;
import Presentacion.Personal.Commands.Command_AltaPersonal_View;
import Presentacion.Personal.Commands.Command_BajaPersonalKO_View;
import Presentacion.Personal.Commands.Command_BajaPersonalOK_View;
import Presentacion.Personal.Commands.Command_BajaPersonal_View;
import Presentacion.Personal.Commands.Command_ConsultarPersonalKO_View;
import Presentacion.Personal.Commands.Command_ConsultarPersonalOK_View;
import Presentacion.Personal.Commands.Command_ConsultarPersonal_View;
import Presentacion.Personal.Commands.Command_ListarPersonal_View;
import Presentacion.Personal.Commands.Command_ModificarPersonalKO_View;
import Presentacion.Personal.Commands.Command_ModificarPersonalOK_View;
import Presentacion.Personal.Commands.Command_ModificarPersonal_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalHabilidadKO_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalHabilidadOK_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalHabilidad_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNaveKO_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNaveOK_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNave_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNifKO_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNifOK_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalNif_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorNumHabKO_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorNumHabOK_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorNumHab_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorRangoSueldoKO_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorRangoSueldoOK_View;
import Presentacion.Personal.Commands.Command_MostrarPersonalPorRangoSueldo_View;
import Presentacion.Personal.Commands.Command_Personal_View;
import Presentacion.Personal_Cafeteria.Commands.Command_AltaPersonalCafKO_View;
import Presentacion.Personal_Cafeteria.Commands.Command_AltaPersonalCafOK_View;
import Presentacion.Personal_Cafeteria.Commands.Command_AltaPersonalCaf_View;
import Presentacion.Personal_Cafeteria.Commands.Command_BajaPersonalCafKO_View;
import Presentacion.Personal_Cafeteria.Commands.Command_BajaPersonalCafOK_View;
import Presentacion.Personal_Cafeteria.Commands.Command_BajaPersonalCaf_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ConsultarPersonalCafKO_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ConsultarPersonalCafOK_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ConsultarPersonalCaf_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ListarPersonalCaf_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ModificarPersonalCafKO_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ModificarPersonalCafOK_View;
import Presentacion.Personal_Cafeteria.Commands.Command_ModificarPersonalCaf_View;
import Presentacion.Personal_Cafeteria.Commands.Command_MostrarPersonalIDTurnoKO_View;
import Presentacion.Personal_Cafeteria.Commands.Command_MostrarPersonalIDTurnoOK_View;
import Presentacion.Personal_Cafeteria.Commands.Command_MostrarPersonalIDTurno_View;
import Presentacion.Personal_Cafeteria.Commands.Command_PersonalCaf_View;
import Presentacion.Producto_Cafeteria.Commands.Command_AltaProductoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_AltaProductoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_AltaProducto_View;
import Presentacion.Producto_Cafeteria.Commands.Command_BajaProductoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_BajaProductoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_BajaProducto_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorAlergenoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorAlergenoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorAlergeno_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorMarcaKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorMarcaOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProductoPorMarca_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ConsultarProducto_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ListarProductoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ListarProductoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ModificarProductoKo_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ModificarProductoOk_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ModificarProducto_View;
import Presentacion.Producto_Cafeteria.Commands.Command_ProductoCafeteria_View;
import Presentacion.Turno_Cafeteria.Commands.Command_LeerTurnoKo_View;
import Presentacion.Turno_Cafeteria.Commands.Command_AltaTurnoOk_View;
import Presentacion.Turno_Cafeteria.Commands.Command_AltaTurno_View;
import Presentacion.Turno_Cafeteria.Commands.Command_BajaTurnoOk_View;
import Presentacion.Turno_Cafeteria.Commands.Command_BajaTurno_View;
import Presentacion.Turno_Cafeteria.Commands.Command_CalcularNomina_Ok_View;
import Presentacion.Turno_Cafeteria.Commands.Command_CalcularNomina_View;
import Presentacion.Turno_Cafeteria.Commands.Command_ConsultarTurnoOk_View;
import Presentacion.Turno_Cafeteria.Commands.Command_ConsultarTurno_View;
import Presentacion.Turno_Cafeteria.Commands.Command_ListarTurno_View;
import Presentacion.Turno_Cafeteria.Commands.Command_ModificarTurnoOk_View;
import Presentacion.Turno_Cafeteria.Commands.Command_ModificarTurno_View;
import Presentacion.Turno_Cafeteria.Commands.Command_LeerTurno_View;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command getCommand(int id) {
		switch (id) {

		/************************* GUI FRIHETEN *******************************/

		case Events.MAIN_VIEW:
			return new Command_Friheten();
		case Events.CAFETERIA_VIEW:
			return new Command_FrihetenJPA();
		case Events.TIENDA_MUEBLES_VIEW:
			return new Command_FrihetenDAO();

		/************************* GUI HABILIDAD *******************************/

		case Events.HABILIDAD_VIEW:
			return new Command_Habilidad_View();
		case Events.ALTA_HABILIDAD_VIEW:

			return new Command_AltaHabilidad_View();

		case Events.ALTA_HABILIDAD_OK_VIEW:

			return new Command_AltaHabilidadOk_View();

		case Events.BAJA_HABILIDAD_VIEW:

			return new Command_BajaHabilidad_View();

		case Events.BAJA_HABILIDAD_OK_VIEW:

			return new Command_BajaHabilidadOk_View();

		case Events.CONSULTAR_HABILIDAD_VIEW:

			return new Command_ConsultarHabilidad_View();

		case Events.CONSULTAR_HABILIDAD_OK_VIEW:

			return new Command_ConsultarHabilidadOk_View();

		case Events.HABILIDAD_CANCEL_VIEW:
			return new Command_HabilidadCancel_View();

		case Events.LISTAR_HABILIDAD_VIEW:
			return new Command_ListarHabilidades_View();

		case Events.MODIFICAR_HABILIDAD_VIEW:
			return new Command_ModificarHabilidades_View();

		case Events.MODIFICAR_HABILIDAD_OK_VIEW:
			return new Command_ModificarHabilidadesOk_View();
		case Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW:
			return new Command_MostrarHabilidadesDeUnaPersona_View();
		case Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_OK_VIEW:
			return new Command_MostrarHabilidadesDeUnaPersonaOk_View();
		case Events.VINCULAR_HABILIDAD_VIEW:
			return new Command_VincularHabilidad_View();
		case Events.VINCULAR_HABILIDAD_OK_VIEW:
			return new Command_VincularHabilidadOk_View();
		case Events.DESVINCULAR_HABILIDAD_VIEW:
			return new Command_DesvincularHabilidad_View();
		case Events.DESVINCULAR_HABILIDAD_OK_VIEW:
			return new Command_DesvincularHabilidadOk_View();

		/************************* GUI NAVE *******************************/

		case Events.NAVE_VIEW:
			return new Command_Nave_View();

		case Events.ALTA_NAVE_VIEW:

			return new Command_AltaNave_View();

		case Events.ALTA_NAVE_OK_VIEW:

			return new Command_AltaNaveOk_View();

		case Events.ALTA_NAVE_KO_VIEW:

			return new Command_AltaNaveKo_View();

		case Events.BAJA_NAVE_VIEW:

			return new Command_BajaNave_View();

		case Events.BAJA_NAVE_OK_VIEW:

			return new Command_BajaNaveOk_View();

		case Events.BAJA_NAVE_KO_VIEW:

			return new Command_BajaNaveKo_View();

		case Events.CONSULTAR_NAVE_VIEW:

			return new Command_ConsultarNave_View();
		case Events.CONSULTAR_NAVE_OK_VIEW:

			return new Command_ConsultarNaveOk_View();
		case Events.CONSULTAR_NAVE_KO_VIEW:

			return new Command_ConsultarNaveKo_View();
		case Events.LISTAR_NAVE_VIEW:

			return new Command_ListarNave_View();

		case Events.MODIFICAR_NAVE_VIEW:

			return new Command_ModificarNave_View();

		case Events.MODIFICAR_NAVE_OK_VIEW:

			return new Command_ModificarNaveOk_View();
		case Events.MODIFICAR_NAVE_KO_VIEW:

			return new Command_ModificarNaveKo_View();

		/************************* GUI MUEBLE *******************************/
		case Events.MUEBLE_VIEW:
			return new Command_Mueble_View();

		case Events.ALTA_MUEBLE_VIEW:
			return new Command_AltaMueble_View();

		case Events.ALTA_MUEBLE_OK_VIEW:
			return new Command_AltaMuebleOk_View();

		case Events.ALTA_MUEBLE_KO_VIEW:
			return new Command_AltaMuebleKO_View();

		case Events.BAJA_MUEBLE_VIEW:
			return new Command_BajaMueble_View();

		case Events.BAJA_MUEBLE_OK_VIEW:
			return new Command_BajaMuebleOK_View();

		case Events.BAJA_MUEBLE_KO_VIEW:
			return new Command_BajaMuebleKO_View();

		case Events.CONSULTAR_MUEBLE_VIEW:
			return new Command_ConsultaMueble_View();

		case Events.CONSULTAR_MUEBLE_OK_VIEW:
			return new Command_ConsultaMuebleOK_View();

		case Events.CONSULTAR_MUEBLE_KO_VIEW:
			return new Command_ConsultaMuebleKO_View();

		case Events.LISTAR_MUEBLE_VIEW:
			return new Command_ListarMueble_View();
		case Events.MODIFICAR_MUEBLE_VIEW:
			return new Command_ModificarMueble_View();

		case Events.MODIFICAR_MUEBLE_OK_VIEW:
			return new Command_ModificarMuebleOK_View();

		case Events.MODIFICAR_MUEBLE_KO_VIEW:
			return new Command_ModificarMuebleKO_View();

		case Events.MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW:
			return new Command_MostrarMueblePorIdNave_View();

		case Events.MOSTRAR_MUEBLE_POR_ID_NAVE_OK_VIEW:
			return new Command_MostrarMueblePorIdNaveOK_View();

		case Events.MOSTRAR_MUEBLE_POR_ID_NAVE_KO_VIEW:
			return new Command_MostrarMueblePorIdNaveKO_View();

		/************************* GUI PERSONAL *******************************/

		case Events.PERSONAL_VIEW:
			return new Command_Personal_View();
		case Events.ALTA_PERSONAL_VIEW:

			return new Command_AltaPersonal_View();

		case Events.ALTA_PERSONAL_OK_VIEW:

			return new Command_AltaPersonalOK_View();

		case Events.ALTA_PERSONAL_KO_VIEW:
			return new Command_AltaPersonalKO_View();

		case Events.BAJA_PERSONAL_VIEW:

			return new Command_BajaPersonal_View();

		case Events.BAJA_PERSONAL_OK_VIEW:
			return new Command_BajaPersonalOK_View();

		case Events.BAJA_PERSONAL_KO_VIEW:

			return new Command_BajaPersonalKO_View();

		case Events.CONSULTAR_PERSONAL_VIEW:

			return new Command_ConsultarPersonal_View();

		case Events.CONSULTAR_PERSONAL_OK_VIEW:

			return new Command_ConsultarPersonalOK_View();

		case Events.CONSULTAR_PERSONAL_KO_VIEW:

			return new Command_ConsultarPersonalKO_View();

		case Events.LISTAR_PERSONAL_VIEW:
			return new Command_ListarPersonal_View();

		case Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW:
			return new Command_MostrarPersonalHabilidad_View();

		case Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_OK_VIEW:
			return new Command_MostrarPersonalHabilidadOK_View();

		case Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_KO_VIEW:

			return new Command_MostrarPersonalHabilidadKO_View();

		case Events.MODIFICAR_PERSONAL_VIEW:

			return new Command_ModificarPersonal_View();

		case Events.MODIFICAR_PERSONAL_OK_VIEW:
			return new Command_ModificarPersonalOK_View();

		case Events.MODIFICAR_PERSONAL_KO_VIEW:
			return new Command_ModificarPersonalKO_View();

		case Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW:
			return new Command_MostrarPersonalNave_View();

		case Events.MOSTRAR_PERSONAL_PORID_NAVE_OK_VIEW:
			return new Command_MostrarPersonalNaveOK_View();

		case Events.MOSTRAR_PERSONAL_PORID_NAVE_KO_VIEW:
			return new Command_MostrarPersonalNaveKO_View();

		case Events.MOSTRAR_PERSONAL_PORNIF_VIEW:
			return new Command_MostrarPersonalNif_View();

		case Events.MOSTRAR_PERSONAL_PORNIF_OK_VIEW:
			return new Command_MostrarPersonalNifOK_View();

		case Events.MOSTRAR_PERSONAL_PORNIF_KO_VIEW:
			return new Command_MostrarPersonalNifKO_View();
		case Events.MOSTRAR_PERSONAL_PORNUMHAB_VIEW:
			return new Command_MostrarPersonalPorNumHab_View();
		case Events.MOSTRAR_PERSONAL_PORNUMHAB_OK_VIEW:
			return new Command_MostrarPersonalPorNumHabOK_View();
		case Events.MOSTRAR_PERSONAL_PORNUMHAB_KO_VIEW:
			return new Command_MostrarPersonalPorNumHabKO_View();
		case Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_VIEW:
			return new Command_MostrarPersonalPorRangoSueldo_View();
		case Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_OK_VIEW:
			return new Command_MostrarPersonalPorRangoSueldoOK_View();
		case Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_KO_VIEW:
			return new Command_MostrarPersonalPorRangoSueldoKO_View();

		/************************* GUI NAVE *******************************/

		/** GUI CLIENTE **/

		case Events.CLIENTE_VIEW:
			return new Command_Cliente_View();

		case Events.ALTA_CLIENTE_VIEW:
			return new Command_AltaCliente_View();

		case Events.ALTA_CLIENTE_OK_VIEW:
			return new Command_AltaClienteOk_View();

		case Events.CLIENTE_KO_VIEW:
			return new Command_ClientKoView();

		case Events.BAJA_CLIENTE_VIEW:
			return new Command_BajaCliente_View();

		case Events.BAJA_CLIENTE_OK_VIEW:
			return new Command_BajaClienteOk_View();

		case Events.CONSULTAR_CLIENTE_VIEW:
			return new Command_ConsultarCliente_View();

		case Events.CONSULTAR_CLIENTE_OK_VIEW:
			return new Command_ConsultarClienteOk_View();

		case Events.LISTAR_CLIENTE_VIEW:
			return new Command_ListarCliente_View();

		case Events.MODIFICAR_CLIENTE_VIEW:
			return new Command_ModificarCliente_View();

		case Events.MODIFICAR_CLIENTE_OK_VIEW:
			return new Command_ModificarClienteOk_View();

		/************************* GUI COMPRA *******************************/

		case Events.COMPRA_VIEW:

//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_Compra_View();

		case Events.ABRIR_COMPRA_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_AbrirCompra_View();

		case Events.RES_ABRIR_COMPRA_OK_VIEW:
			return new Command_Res_AbrirCompra_OK_View();

		case Events.RES_ABRIR_COMPRA_KO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_Res_AbrirCompra_KO_View();

		case Events.ANYADIR_PRODUCTO_VIEW:
			return new Command_AnyadirProducto_View();

		case Events.RES_ANYADIR_PRODUCTO_OK_VIEW:
			return new Command_Res_AnyadirProducto_OK_View();

		case Events.RES_ANYADIR_PRODUCTO_KO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(1, (TCarrito) data);
			return new Command_Res_AnyadirProducto_KO_View();

		case Events.MOSTRAR_CARRITO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.MOSTRAR_CARRITO_VIEW, data);
			return new Command_MostrarCarrito_View();

		case Events.RES_MOSTRAR_CARRITO_OK_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.RES_MOSTRAR_CARRITO_OK_VIEW,
//					(TCarrito) data);
			return new Command_Res_MostrarCarrito_OK_View();
		case Events.CERRAR_COMPRA_VIEW:
//			FactoryNeg.getInstance().generateSACompra().close((TCarrito) data);
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.RES_MOSTRAR_CARRITO_KO_VIEW, null);

			return new Command_CerrarCompra_View();

		case Events.CONSULTAR_COMPRA_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_ConsultarCompra_View();

		case Events.RES_CONSULTAR_COMPRA_ID_OK_VIEW:
			return new Command_Res_ConsultarCompra_OK_View();

		case Events.RES_CONSULTAR_COMPRA_KO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_Res_ConsultarCompra_KO_View();

		case Events.DEVOLUCION_PRODUCTO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_DevolucionProducto_View();

		case Events.RES_DEVOLUCION_PRODUCTO_ID_OK_VIEW:
			return new Command_Res_DevolucionProducto_ID_OK_View();

		case Events.RES_DEVOLUCION_PRODUCTO_OK_VIEW:
			return new Command_Res_DevolucionProducto_OK_View();

		case Events.RES_DEVOLUCION_PRODUCTO_KO_VIEW:
			return new Command_Res_DevolucionProducto_KO_View();

		case Events.LISTAR_COMPRA_VIEW:
			return new Command_ListarCompra_View();

		case Events.MOSTRAR_COMPRA_POR_ID_CLIENTE_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event);
			return new Command_MostrarCompra_IDCliente_View();

		case Events.RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_OK_VIEW:
			return new Command_Res_MostrarCompra_IDCliente_OK_View();

		case Events.QUITAR_PRODUCTO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(event, data);
			return new Command_QuitarProducto_View();

		case Events.RES_QUITAR_PRODUCTO_OK_VIEW:
			return new Command_Res_QuitarProducto_OK_View();

		case Events.RES_QUITAR_PRODUCTO_KO_VIEW:
//			FactoryPresentacion.getInstance().generateGUI(event).actualizar(event, data);
			return new Command_Res_QuitarProducto_KO_View();

		case Events.MODIFICAR_COMPRA_VIEW:
			return new Command_ModificarCompra_View();

		case Events.RES_MODIFICAR_COMPRA_OK_VIEW:
			return new Command_Res_ModificarCompra_OK_View();

		case Events.RES_MODIFICAR_COMPRA_KO_VIEW:
			return new Command_Res_ModificarCompra_KO_View();

/************************* GUI COMPRA CAFETERIA *******************************/

			case Events.COMPRA_CAF_VIEW:

//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_Compra_View_Cafeteria();

			case Events.ABRIR_COMPRA_CAF_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_AbrirCompra_View_Cafeteria();

			case Events.RES_ABRIR_COMPRA_CAF_OK_VIEW:
				return new Command_Res_AbrirCompra_OK_View_Cafeteria();

			case Events.RES_ABRIR_COMPRA_CAF_KO_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_Res_AbrirCompra_KO_View_Cafeteria();

			case Events.ANYADIR_PRODUCTO_CAF_VIEW:
				return new Command_AnyadirProducto_View_Cafeteria();

			case Events.RES_ANYADIR_PRODUCTO_CAF_OK_VIEW:
				return new Command_Res_AnyadirProducto_OK_View_Cafeteria();

			case Events.RES_ANYADIR_PRODUCTO_CAF_KO_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(1, (TCarrito) data);
				return new Command_Res_AnyadirProducto_KO_View_Cafeteria();

			case Events.MOSTRAR_CARRITO_CAF_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.MOSTRAR_CARRITO_VIEW, data);
				return new Command_MostrarCarrito_View_Cafeteria();

			case Events.RES_MOSTRAR_CARRITO_CAF_OK_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.RES_MOSTRAR_CARRITO_OK_VIEW,
//						(TCarrito) data);
				return new Command_Res_MostrarCarrito_OK_View_Cafeteria();
			case Events.CERRAR_COMPRA_CAF_VIEW:
//				FactoryNeg.getInstance().generateSACompra().close((TCarrito) data);
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(Events.RES_MOSTRAR_CARRITO_KO_VIEW, null);

				return new Command_CerrarCompra_View_Cafeteria();

			case Events.CONSULTAR_COMPRA_CAF_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_ConsultarCompra_View_Cafeteria();

			case Events.RES_CONSULTAR_COMPRA_CAF_ID_OK_VIEW:
				return new Command_Res_ConsultarCompra_OK_View_Cafeteria();

			case Events.RES_CONSULTAR_COMPRA_CAF_KO_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_Res_ConsultarCompra_KO_View_Cafeteria();

			case Events.DEVOLUCION_PRODUCTO_CAF_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event);
				return new Command_DevolucionProducto_View_Cafeteria();

			case Events.RES_DEVOLUCION_PRODUCTO_CAF_ID_OK_VIEW:
				return new Command_Res_DevolucionProducto_ID_OK_View_Cafeteria();

			case Events.RES_DEVOLUCION_PRODUCTO_CAF_OK_VIEW:
				return new Command_Res_DevolucionProducto_OK_View_Cafeteria();

			case Events.RES_DEVOLUCION_PRODUCTO_CAF_KO_VIEW:
				return new Command_Res_DevolucionProducto_KO_View_Cafeteria();

			case Events.LISTAR_COMPRA_CAF_VIEW:
				return new Command_ListarCompra_View_Cafeteria();

			case Events.QUITAR_PRODUCTO_CAF_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(event, data);
				return new Command_QuitarProducto_View_Cafeteria();

			case Events.RES_QUITAR_PRODUCTO_CAF_OK_VIEW:
				return new Command_Res_QuitarProducto_OK_View_Cafeteria();

			case Events.RES_QUITAR_PRODUCTO_CAF_KO_VIEW:
//				FactoryPresentacion.getInstance().generateGUI(event).actualizar(event, data);
				return new Command_Res_QuitarProducto_KO_View_Cafeteria();

			case Events.MODIFICAR_COMPRA_CAF_VIEW:
				return new Command_ModificarCompra_View_Cafeteria();

			case Events.RES_MODIFICAR_COMPRA_CAF_OK_VIEW:
				return new Command_Res_ModificarCompra_OK_View_Cafeteria();

			case Events.RES_MODIFICAR_COMPRA_CAF_KO_VIEW:
				return new Command_Res_ModificarCompra_KO_View_Cafeteria();

		/************************* GUI PRODUCTO *******************************/
		/*-----------------------------------------------------*/
				
		case Events.PRODUCTO_VIEW:
			return new  Command_ProductoCafeteria_View();
			
		/*-----------------------------------------------------*/
			
		case Events.ALTA_PRODUCTO_VIEW:
			return new  Command_AltaProducto_View();
			
		case Events.ALTA_PRODUCTO_OK_VIEW:
			return new Command_AltaProductoOk_View();
			
		case Events.ALTA_PRODUCTO_KO_VIEW:
			return new Command_AltaProductoKo_View();
			
		/*-----------------------------------------------------*/
			
		case Events.BAJA_PRODUCTO_VIEW:
			return new Command_BajaProducto_View();

		case Events.BAJA_PRODUCTO_OK_VIEW:
			return new Command_BajaProductoOk_View();

		case Events.BAJA_PRODUCTO_KO_VIEW:
			return new Command_BajaProductoKo_View();
			
		/*-----------------------------------------------------*/
			
		case Events.CONSULTAR_PRODUCTO_VIEW:
			return new Command_ConsultarProducto_View();

		case Events.CONSULTAR_PRODUCTO_OK_VIEW:
			return new Command_ConsultarProductoOk_View();

		case Events.CONSULTAR_PRODUCTO_KO_VIEW:
			return new Command_ConsultarProductoKo_View();
			
		/*-----------------------------------------------------*/
			
			case Events.LISTAR_PRODUCTO_VIEW:
				return new Command_ListarProductoOk_View();

			case Events.LISTAR_PRODUCTO_KO_VIEW:
				return new Command_ListarProductoKo_View();

			case Events.LISTAR_PRODUCTO_OK_VIEW:
				return new Command_ListarProductoOk_View();		
				
		/*-----------------------------------------------------*/
			
		case Events.MODIFICAR_PRODUCTO_VIEW:
			return new  Command_ModificarProducto_View();

		case Events.MODIFICAR_PRODUCTO_OK_VIEW:
			return new Command_ModificarProductoOk_View();

		case Events.MODIFICAR_PRODUCTO_KO_VIEW:
			return new Command_ModificarProductoKo_View();
			
		/*-----------------------------------------------------*/			

		case Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW:
			return new Command_ConsultarProductoPorAlergeno_View();

		case Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_KO_VIEW:
			return new Command_ConsultarProductoPorAlergenoKo_View();

		case Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_OK_VIEW:
			return new Command_ConsultarProductoPorAlergenoOk_View();
			
		/*-----------------------------------------------------*/
			
		case Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW:
			return new Command_ConsultarProductoPorMarca_View();

		case Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW:
			return new Command_ConsultarProductoPorMarcaKo_View();

		case Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_OK_VIEW:
			return new Command_ConsultarProductoPorMarcaOk_View();

			
		/*-----------------------------------------------------*/

		/************************* GUI PERSONAL_CAFETERIA *****************************/

		case Events.PERSONALCAFETERIA_VIEW:
			return new Command_PersonalCaf_View();
		case Events.ALTA_PERSONALCAFETERIA_VIEW:

			return new Command_AltaPersonalCaf_View();

		case Events.ALTA_PERSONALCAFETERIA_OK_VIEW:

			return new Command_AltaPersonalCafOK_View();

		case Events.ALTA_PERSONALCAFETERIA_KO_VIEW:
			return new Command_AltaPersonalCafKO_View();

		case Events.BAJA_PERSONALCAFETERIA_VIEW:

			return new Command_BajaPersonalCaf_View();

		case Events.BAJA_PERSONALCAFETERIA_OK_VIEW:
			return new Command_BajaPersonalCafOK_View();

		case Events.BAJA_PERSONALCAFETERIA_KO_VIEW:

			return new Command_BajaPersonalCafKO_View();

		case Events.CONSULTAR_PERSONALCAFETERIA_VIEW:

			return new Command_ConsultarPersonalCaf_View();

		case Events.CONSULTAR_PERSONALCAFETERIA_OK_VIEW:

			return new Command_ConsultarPersonalCafOK_View();

		case Events.CONSULTAR_PERSONALCAFETERIA_KO_VIEW:

			return new Command_ConsultarPersonalCafKO_View();

		case Events.LISTAR_PERSONALCAFETERIA_VIEW:
			return new Command_ListarPersonalCaf_View();

		case Events.MODIFICAR_PERSONALCAFETERIA_VIEW:

			return new Command_ModificarPersonalCaf_View();

		case Events.MODIFICAR_PERSONALCAFETERIA_OK_VIEW:
			return new Command_ModificarPersonalCafOK_View();

		case Events.MODIFICAR_PERSONALCAFETERIA_KO_VIEW:
			return new Command_ModificarPersonalCafKO_View();

		case Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_VIEW:

			return new Command_MostrarPersonalIDTurno_View();

		case Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_OK_VIEW:
			return new Command_MostrarPersonalIDTurnoOK_View();

		case Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_KO_VIEW:
			return new Command_MostrarPersonalIDTurnoKO_View();

		/************************* GUI ALERGENO *******************************/

		case Events.ALERGENO_VIEW:
			return new Command_Alergeno_View();
		case Events.ALTA_ALERGENO_VIEW:
			return new Command_AltaAlergeno_View();
		case Events.ALTA_ALERGENO_OK_VIEW:
			return new Command_AltaAlergenoOk_View();
		case Events.ALTA_ALERGENO_KO_VIEW:
			return new Command_AltaAlergenoKo_View();
		case Events.BAJA_ALERGENO_VIEW:
			return new Command_BajaAlergeno_View();
		case Events.BAJA_ALERGENO_OK_VIEW:
			return new Command_BajaAlergenoOk_View();
		case Events.BAJA_ALERGENO_KO_VIEW:
			return new Command_BajaAlergenoKo_View();
		case Events.LISTAR_ALERGENO_VIEW:
			return new Command_ListarAlergeno_View();
		case Events.MODIFICAR_ALERGENO_VIEW:
			return new Command_UpdateAlergeno_View();
		case Events.MODIFICAR_ALERGENO_OK_VIEW:
			return new Command_UpdateAlergenoOk_View();
		case Events.MODIFICAR_ALERGENO_KO_VIEW:
			return new Command_UpdateAlergenoKo_View();
		case Events.CONSULTAR_ALERGENO_VIEW:
			return new Command_ConsultarAlergeno_View();
		case Events.CONSULTAR_ALERGENO_OK_VIEW:
			return new Command_ConsultarAlergenoOk_View();
		case Events.CONSULTAR_ALERGENO_KO_VIEW:
			return new Command_ConsultarAlergenoKo_View();
		case Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW:
			return new Command_ListarAlergenosProducto_View();
		case Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_OK_VIEW:
			return new Command_ListarAlergenosProductoOk_View();
		case Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_KO_VIEW:
			return new Command_ListarAlergenosProductoKo_View();
		case Events.VINCULAR_ALERGENO_VIEW:
			return new Command_VincularAlergeno_View();
		case Events.VINCULAR_ALERGENO_OK_VIEW:
			return new Command_VincularAlergenoOk_View();
		case Events.VINCULAR_ALERGENO_KO_VIEW:
			return new Command_VincularAlergenoKo_View();
		case Events.DESVINCULAR_ALERGENO_VIEW:
			return new Command_DesvincularAlergeno_View();
		case Events.DESVINCULAR_ALERGENO_OK_VIEW:
			return new Command_DesvincularAlergenoOk_View();
		case Events.DESVINCULAR_ALERGENO_KO_VIEW:
			return new Command_DesvincularAlergenoKo_View();
		case Events.ALERGENO_CANCEL_VIEW:
			return new Command_AlergenoCancel_View();


			/** GUI TURNO **/

		case Events.TURNO_VIEW:
			return new Command_LeerTurno_View();

		case Events.ALTA_TURNO_VIEW:
			return new Command_AltaTurno_View();

		case Events.ALTA_TURNO_OK_VIEW:
			return new Command_AltaTurnoOk_View();

		case Events.TURNO_KO_VIEW:
			return new Command_LeerTurnoKo_View();

		case Events.BAJA_TURNO_VIEW:
			return new Command_BajaTurno_View();

		case Events.BAJA_TURNO_OK_VIEW:
			return new Command_BajaTurnoOk_View();

		case Events.CONSULTAR_TURNO_VIEW:
			return new Command_ConsultarTurno_View();

		case Events.CONSULTAR_TURNO_OK_VIEW:
			return new Command_ConsultarTurnoOk_View();

		case Events.LISTAR_TURNO_VIEW:
			return new Command_ListarTurno_View();

		case Events.MODIFICAR_TURNO_VIEW:
			return new Command_ModificarTurno_View();

		case Events.MODIFICAR_TURNO_OK_VIEW:
			return new Command_ModificarTurnoOk_View();
		case Events.TURNO_CALCULAR_NOMINA_VIEW:
			return new Command_CalcularNomina_View();
		case Events.TURNO_CALCULAR_NOMINA_OK_VIEW:
			return new Command_CalcularNomina_Ok_View();


		/************************* GUI MARCA *******************************/
			
		case Events.MARCA_VIEW:
			return new Command_Marca_View();
			
		case Events.ALTA_MARCA_VIEW:
			return new Command_AltaMarca_View();
			
		case Events.ALTA_MARCA_OK_VIEW:
			return new Command_AltaMarcaOk_View();
			
		case Events.ALTA_MARCA_KO_VIEW:
			return new Command_AltaMarcaKo_View();		

		case Events.BAJA_MARCA_VIEW:
			return new Command_BajaMarca_View();
			
		case Events.BAJA_MARCA_OK_VIEW:
			return new Command_BajaMarcaOk_View();
			
		case Events.BAJA_MARCA_KO_VIEW:
			return new Command_BajaMarcaKo_View();
			
		case Events.LISTAR_MARCA_VIEW:
			return new Command_ListarMarca_View();
			
		case Events.CONSULTAR_MARCA_VIEW:
			return new Command_ConsultarMarca_View();
			
		case Events.CONSULTAR_MARCA_OK_VIEW:
			return new Command_ConsultarMarcaOk_View();
			
		case Events.CONSULTAR_MARCA_KO_VIEW:
			return new Command_ConsultarMarcaKo_View();
			
		case Events.MODIFICAR_MARCA_VIEW:
			return new Command_ModificarMarca_View();
			
		case Events.MODIFICAR_MARCA_OK_VIEW:
			return new Command_ModificarMarcaOk_View();
			
		case Events.MODIFICAR_MARCA_KO_VIEW:
			return new Command_ModificarMarcaKo_View();
			
		default:
			return null;
		}


	}

}
