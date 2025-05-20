package Presentacion.Controller;

public class Events {

	/*********************** FRIHETEN *****************************/
	public static final int MAIN_VIEW = 0001;
	public static final int CAFETERIA_VIEW = 0002;
	public static final int TIENDA_MUEBLES_VIEW = 0003;

	/*********************** HABILIDAD *****************************/
	//debido a algunos cambios los eventos de Habilidad Ko no se usan
	public static final int HABILIDAD_VIEW = 1000;
	public static final int ALTA_HABILIDAD_VIEW = 1010;
	public static final int ALTA_HABILIDAD_OK_VIEW = 1011;
	public static final int ALTA_HABILIDAD_KO_VIEW = 1012;
	public static final int BAJA_HABILIDAD_VIEW = 1020;
	public static final int BAJA_HABILIDAD_OK_VIEW = 1021;
	public static final int BAJA_HABILIDAD_KO_VIEW = 1022;
	public static final int CONSULTAR_HABILIDAD_VIEW = 1030;
	public static final int CONSULTAR_HABILIDAD_OK_VIEW = 1031;
	public static final int CONSULTAR_HABILIDAD_KO_VIEW = 1032;
	public static final int DESVINCULAR_HABILIDAD_VIEW = 1040;
	public static final int DESVINCULAR_HABILIDAD_OK_VIEW = 1041;
	public static final int DESVINCULAR_HABILIDAD_KO_VIEW = 1042;
	public static final int LISTAR_HABILIDAD_VIEW = 1050;
	public static final int MODIFICAR_HABILIDAD_VIEW = 1060;
	public static final int MODIFICAR_HABILIDAD_OK_VIEW = 1061;
	public static final int MODIFICAR_HABILIDAD_KO_VIEW = 1062;
	public static final int VINCULAR_HABILIDAD_VIEW = 1070;
	public static final int VINCULAR_HABILIDAD_OK_VIEW = 1071;
	public static final int VINCULAR_HABILIDAD_KO_VIEW = 1072;
	public static final int MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW = 1080;
	public static final int MOSTRAR_HABILIDADES_DE_UNA_PERSONA_OK_VIEW = 1081;
	public static final int MOSTRAR_HABILIDADES_DE_UNA_PERSONA_KO_VIEW = 1082;
	public static final int HABILIDAD_CANCEL_VIEW = 1083;

	// ---------------EVENTOS EXITO HABILIDAD (MOSTRARA MENSAJE DISTINTO/OPERACION)

	public static final int EXITO_HABILIDAD_ALTA = 1100;
	public static final int EXITO_HABILIDAD_BAJA = 1101;
	public static final int EXITO_HABILIDAD_CONSULTAR = 1102;
	public static final int EXITO_HABILIDAD_DESVINCULAR = 1103;
	public static final int EXITO_HABILIDAD_MODIFICAR = 1104;
	public static final int EXITO_HABILIDAD_VINCULAR = 1105;
	public static final int EXITO_MOSTRAR_HABILIDAD_POR_PERSONA = 1106;

	// ---------------EVENTOS DE ERROR HABILIDAD.

	public static final int ERROR_HABILIDAD_EXISTE = 1200;
	public static final int ERROR_HABILIDAD_NO_EXISTE = 1201;
	public static final int ERROR_HABILIDAD_YA_VINCULADA_A_PERSONAL = 1202;
	public static final int ERROR_HABILIDAD_NO_VINCULADA_A_PERSONAL = 1203;
	public static final int ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL = 1204;
	public static final int ERROR_NO_TIENE_NINGUNA_HABILIDAD_VINCULADA = 1205;

	/*********************** PERSONAL *****************************/
	public static final int PERSONAL_VIEW = 2000;
	public static final int ALTA_PERSONAL_VIEW = 2010;
	public static final int ALTA_PERSONAL_OK_VIEW = 2011;
	public static final int ALTA_PERSONAL_KO_VIEW = 2012;
	public static final int BAJA_PERSONAL_VIEW = 2020;
	public static final int BAJA_PERSONAL_OK_VIEW = 2021;
	public static final int BAJA_PERSONAL_KO_VIEW = 2022;
	public static final int CONSULTAR_PERSONAL_VIEW = 2030;
	public static final int CONSULTAR_PERSONAL_OK_VIEW = 2031;
	public static final int CONSULTAR_PERSONAL_KO_VIEW = 2032;
	public static final int LISTAR_PERSONAL_VIEW = 2040;
	public static final int MODIFICAR_PERSONAL_VIEW = 2050;
	public static final int MODIFICAR_PERSONAL_OK_VIEW = 2051;
	public static final int MODIFICAR_PERSONAL_KO_VIEW = 2052;
	public static final int MOSTRAR_PERSONAL_PORID_NAVE_VIEW = 2060;
	public static final int MOSTRAR_PERSONAL_PORID_NAVE_OK_VIEW = 2061;
	public static final int MOSTRAR_PERSONAL_PORID_NAVE_KO_VIEW = 2062;
	public static final int MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW = 2070;
	public static final int MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_OK_VIEW = 2071;
	public static final int MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_KO_VIEW = 2072;
	public static final int MOSTRAR_PERSONAL_PORNIF_VIEW = 2080;
	public static final int MOSTRAR_PERSONAL_PORNIF_OK_VIEW = 2081;
	public static final int MOSTRAR_PERSONAL_PORNIF_KO_VIEW = 2082;
	public static final int MOSTRAR_PERSONAL_PORNUMHAB_VIEW = 2083;
	public static final int MOSTRAR_PERSONAL_PORNUMHAB_OK_VIEW = 2084;
	public static final int MOSTRAR_PERSONAL_PORNUMHAB_KO_VIEW = 2085;
	public static final int MOSTRAR_PERSONAL_PORRANGOSUELDO_VIEW = 2086;
	public static final int MOSTRAR_PERSONAL_PORRANGOSUELDO_OK_VIEW = 2087;
	public static final int MOSTRAR_PERSONAL_PORRANGOSUELDO_KO_VIEW = 2088;

	

	// ---------------EVENTOS EXITO PERSONAL (MOSTRARA MENSAJE DISTINTO/OPERACION)

	public static final int EXITO_PERSONAL_ALTA = 2100;
	public static final int EXITO_PERSONAL_BAJA = 2101;
	public static final int EXITO_PERSONAL_CONSULTAR = 2102;
	public static final int EXITO_PERSONAL_MODIFICAR = 2103;
	public static final int EXITO_PERSONAL_MOSTRAR_POR_ID_NAVE = 2104;
	public static final int EXITO_MOSTRAR_PERSONAL_DE_UNA_HABILIDAD = 2105;
	public static final int EXITO_PERSONAL_MOSTRAR_POR_NIF = 2106;

	// ---------------EVENTOS DE ERROR PERSONAL.

	public static final int ERROR_PERSONAL_EXISTE = 2200;
	public static final int ERROR_PERSONAL_NO_EXISTE = 2201;
	public static final int ERROR_PERSONAL_YA_DADO_DE_BAJA = 2202;
	public static final int ERROR_JEFE_ANTES_ERA_EMPLEADO_O_VICEVERSA = 2203;
	public static final int ERROR_NO_HAY_PERSONAL_ASOCIADO_A_TAL_ID_NAVE = 2204;
	public static final int ERROR_NO_TIENE_NINGUNA_PERSONA_VINCULADA = 2205;

	/*********************** NAVE *****************************/
	public static final int NAVE_VIEW = 3000;
	public static final int ALTA_NAVE_VIEW = 3010;
	public static final int ALTA_NAVE_OK_VIEW = 3011;
	public static final int ALTA_NAVE_KO_VIEW = 3012;
	public static final int RES_ALTA_NAVE_OK = 3013;
	public static final int RES_ALTA_NAVE_KO = 3014;
	public static final int BAJA_NAVE_VIEW = 3020;
	public static final int BAJA_NAVE_OK_VIEW = 3021;
	public static final int BAJA_NAVE_KO_VIEW = 3022;
	public static final int RES_BAJA_NAVE_OK = 3023;
	public static final int CONSULTAR_NAVE_VIEW = 3030;
	public static final int CONSULTAR_NAVE_OK_VIEW = 3031;
	public static final int CONSULTAR_NAVE_KO_VIEW = 3032;
	public static final int RES_CONSULTAR_NAVE_OK = 3034;
	public static final int LISTAR_NAVE_VIEW = 3040;
	public static final int MODIFICAR_NAVE_VIEW = 3050;
	public static final int MODIFICAR_NAVE_OK_VIEW = 3051;
	public static final int MODIFICAR_NAVE_KO_VIEW = 3052;
	public static final int RES_MODIFICAR_NAVE_OK = 3053;
	public static final int NAVE_ERROR_YA_EXISTE = 3100;
	public static final int NAVE_ERROR_NO_EXISTE = 3101;
	public static final int NAVE_ERROR_DATOS_NO_VALIDOS = 3102;
	public static final int NAVE_ERROR_DESCONOCIDO = 3103;

	/*********************** MUEBLE *****************************/
	public static final int MUEBLE_VIEW = 4000;
	public static final int ALTA_MUEBLE_VIEW = 4010;
	public static final int ALTA_MUEBLE_OK_VIEW = 4011;
	public static final int ALTA_MUEBLE_KO_VIEW = 4012;
	public static final int BAJA_MUEBLE_VIEW = 4020;
	public static final int BAJA_MUEBLE_OK_VIEW = 4021;
	public static final int BAJA_MUEBLE_KO_VIEW = 4022;
	public static final int CONSULTAR_MUEBLE_VIEW = 4030;
	public static final int CONSULTAR_MUEBLE_OK_VIEW = 4031;
	public static final int CONSULTAR_MUEBLE_KO_VIEW = 4032;
	public static final int LISTAR_MUEBLE_VIEW = 4040;
	public static final int MODIFICAR_MUEBLE_VIEW = 4050;
	public static final int MODIFICAR_MUEBLE_OK_VIEW = 4051;
	public static final int MODIFICAR_MUEBLE_KO_VIEW = 4052;
	public static final int MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW = 4060;
	public static final int MOSTRAR_MUEBLE_POR_ID_NAVE_OK_VIEW = 4061;
	public static final int MOSTRAR_MUEBLE_POR_ID_NAVE_KO_VIEW = 4062;
	

	// ---------------EVENTOS EXITO MUEBLE (MOSTRARA MENSAJE DISTINTO/OPERACION)
	public static final int EXITO_MUEBLE_ALTA = 4100;
	public static final int EXITO_MUEBLE_BAJA = 4101;
	public static final int EXITO_MUEBLE_CONSULTAR = 4102;
	public static final int EXITO_MUEBLE_MODIFICAR = 4103;
	public static final int EXITO_MUEBLE_MOSTRAR_POR_ID_NAVE = 4104;

	// ---------------EVENTOS DE ERROR MUEBLE----------------------------
	public static final int ERROR_MUEBLE_EXISTE = 4200;
	public static final int ERROR_MUEBLE_NO_EXISTE = 4201;
	public static final int ERROR_NOMBRE_ERRONEO = 4202;
	public static final int ERROR_NO_HAY_MUEBLE_ASOCIADO_A_TAL_ID_NAVE = 4203;

	
	/******************************* PRODUCTO CAFETERIA ***************************	*/
	
	public static final int PRODUCTO_VIEW  = 4400;
	public static final int ALTA_PRODUCTO_VIEW  = 4401;
	public static final int ALTA_PRODUCTO_OK_VIEW  = 4402;
	public static final int ALTA_PRODUCTO_KO_VIEW  = 4403;
	public static final int BAJA_PRODUCTO_VIEW  = 4404;
	public static final int BAJA_PRODUCTO_OK_VIEW  = 4405;
	public static final int BAJA_PRODUCTO_KO_VIEW  = 4406;
	public static final int CONSULTAR_PRODUCTO_VIEW  = 4407;
	public static final int CONSULTAR_PRODUCTO_OK_VIEW  = 4408;
	public static final int CONSULTAR_PRODUCTO_KO_VIEW  = 4409;

	public static final int MODIFICAR_PRODUCTO_VIEW  = 4411;
	public static final int MODIFICAR_PRODUCTO_OK_VIEW  = 4412;
	public static final int MODIFICAR_PRODUCTO_KO_VIEW  = 4413;
	public static final int CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW  = 4414;
	public static final int CONSULTAR_PRODUCTO_POR_ID_MARCA_OK_VIEW  = 4415;
	public static final int CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW  = 4416;
	public static final int CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW  = 4417;
	public static final int CONSULTAR_PRODUCTO_POR_ID_ALERGENO_OK_VIEW  = 4418;
	public static final int CONSULTAR_PRODUCTO_POR_ID_ALERGENO_KO_VIEW  = 4419;
	public static final int LISTAR_PRODUCTO_VIEW  = 4420;
	public static final int LISTAR_PRODUCTO_OK_VIEW  = 4421;
	public static final int LISTAR_PRODUCTO_KO_VIEW  = 4422;


	//Mensajes OK para producto cafeteria
	
	public static final int EXITO_PRODUCTO_ALTA  = 4500;
	public static final int EXITO_PRODUCTO_BAJA  = 4501;
	public static final int EXITO_PRODUCTO_CONSULTAR  = 4502;
	public static final int EXITO_PRODUCTO_MODIFICAR  = 4503;
	public static final int EXITO_PRODUCTO_MOSTRAR_POR_ID_MARCA = 4504;
	public static final int EXITO_PRODUCTO_MOSTRAR_POR_ID_ALERGENO = 4504;

	//Mensajes ERROR para producto cafeteria

	public static final int ERROR_PRODUCTO_EXISTE  = 4600;
	public static final int ERROR_PRODUCTO_NO_EXISTE  = 4601;
	public static final int ERROR_PRODUCTO_NOMBRE_ERRONEO  = 4602;
	public static final int ERROR_NO_HAY_PRODUCTO_ASOCIADO_A_TAL_ID_MARCA  = 4603;
	public static final int ERROR_NO_HAY_PRODUCTO_ASOCIADO_A_TAL_ID_ALERGENO  = 4604;

	

	
	/*********************** CLIENTE *****************************/
	public static final int CLIENTE_VIEW = 5000;
	public static final int ALTA_CLIENTE_VIEW = 5010;
	public static final int ALTA_CLIENTE_OK_VIEW = 5011;
	public static final int BAJA_CLIENTE_VIEW = 5020;
	public static final int BAJA_CLIENTE_OK_VIEW = 5021;
	public static final int CONSULTAR_CLIENTE_VIEW = 5030;
	public static final int CONSULTAR_CLIENTE_OK_VIEW = 5031;
	public static final int LISTAR_CLIENTE_VIEW = 5040;
	public static final int MODIFICAR_CLIENTE_VIEW = 5050;
	public static final int MODIFICAR_CLIENTE_OK_VIEW = 5051;
	public static final int CLIENTE_KO_VIEW = 5060;

	// ---------------EVENTOS EXITO CLIENTE (MOSTRARA MENSAJE DISTINTO/OPERACION)
	public static final int EXITO_CLIENTE_ALTA = 5100;
	public static final int EXITO_CLIENTE_BAJA = 5101;
	public static final int EXITO_CLIENTE_CONSULTAR = 5102;
	public static final int EXITO_CLIENTE_MODIFICAR = 5103;
	
	// ---------------EVENTOS DE ERROR CLIENTE----------------------------
	public static final int ERROR_CLIENTE_EXISTE = 5200;
	public static final int ERROR_CLIENTE_NO_EXISTE = 5201;
	public static final int ERROR_CLIENTE_ERRONEO = 5202;
	public static final int ERROR_CLIENTE_NUMBER_FORMAT_EXCEPTION = 5203;
	public static final int ERROR_CORREO_EXISTE = 5204;
	public static final int ERROR_TIPOS_DISTINTOS = 5205;
	public static final int ERROR_TIPOS_DISTINTOS_MOD = 5206;
	public static final int ERROR_CLIENTE_EXISTE_MOD = 5207;
	public static final int ERROR_CORREO_EXISTE_MOD = 5208;
	// public static final int ERROR_CLIENTE_NUMBER_NEGATIVE = 5204;
	
	
	/*********************** TURNO *****************************/
	public static final int TURNO_VIEW = 5300;
	public static final int ALTA_TURNO_VIEW = 5310;
	public static final int ALTA_TURNO_OK_VIEW = 5311;
	public static final int BAJA_TURNO_VIEW = 5320;
	public static final int BAJA_TURNO_OK_VIEW = 5321;
	public static final int CONSULTAR_TURNO_VIEW = 5330;
	public static final int CONSULTAR_TURNO_OK_VIEW = 5331;
	public static final int LISTAR_TURNO_VIEW = 5340;
	public static final int MODIFICAR_TURNO_VIEW = 5350;
	public static final int MODIFICAR_TURNO_OK_VIEW = 5351;
	public static final int TURNO_KO_VIEW = 5360;
	public static final int TURNO_CALCULAR_NOMINA_VIEW = 5370;
	public static final int TURNO_CALCULAR_NOMINA_OK_VIEW = 5371;

	// ---------------EVENTOS EXITO TURNO (MOSTRARA MENSAJE DISTINTO/OPERACION)
	public static final int EXITO_TURNO_ALTA = 5400;
	public static final int EXITO_TURNO_BAJA = 5401;
	public static final int EXITO_TURNO_CONSULTAR = 5402;
	public static final int EXITO_TURNO_MODIFICAR = 5403;
	public static final int EXITO_TURNO_CALCULAR_NOMINA = 5404;
	
	
	// ---------------EVENTOS DE ERROR TURNO----------------------------
	public static final int ERROR_TURNO_EXISTE = 5500;
	public static final int ERROR_TURNO_NO_EXISTE = 5501;
	public static final int ERROR_TURNO_ERRONEO = 5502;
	public static final int ERROR_TURNO_NUMBER_FORMAT_EXCEPTION = 5503;
	public static final int ERROR_NOMBRE_EXISTE = 5504;
	public static final int ERROR_TURNO_DE_BAJA = 5505;
	public static final int ERROR_TURNO_CALCULAR_NOMINA = 5506;
	public static final int ERROR_TURNO_ACTUALIZANDO = 5507;
	public static final int ERROR_TURNO_VINCULADO_A_PERSONAL = 5508;
	

	/*********************** COMPRA *****************************/
	public static final int COMPRA_VIEW = 6000;
	public static final int ABRIR_COMPRA_VIEW = 6010;
	public static final int RES_ABRIR_COMPRA_OK_VIEW = 6011;
	public static final int RES_ABRIR_COMPRA_KO_VIEW = 6012;
	public static final int ANYADIR_PRODUCTO_VIEW = 6020;
	public static final int RES_ANYADIR_PRODUCTO_OK_VIEW = 6021;
	public static final int RES_ANYADIR_PRODUCTO_KO_VIEW = 6022;
	public static final int CERRAR_COMPRA_VIEW = 6030;
	public static final int RES_CERRAR_COMPRA_OK_VIEW = 6031;
	public static final int RES_CERRAR_COMPRA_KO_VIEW = 6032;
	public static final int CONSULTAR_COMPRA_VIEW = 6040;
	public static final int RES_CONSULTAR_COMPRA_OK_VIEW = 6041;
	public static final int RES_CONSULTAR_COMPRA_KO_VIEW = 6042;
	public static final int RES_CONSULTAR_COMPRA_ID_OK_VIEW = 6300;
	public static final int DEVOLUCION_PRODUCTO_VIEW = 6050;
	public static final int RES_DEVOLUCION_PRODUCTO_OK_VIEW = 6051;
	public static final int RES_DEVOLUCION_PRODUCTO_KO_VIEW = 6052;
	public static final int RES_DEVOLUCION_PRODUCTO_ID_OK_VIEW = 6053;
	public static final int LISTAR_COMPRA_VIEW = 6060;
	public static final int MOSTRAR_COMPRA_VIEW = 6070;
	public static final int RES_MOSTRAR_COMPRA_OK_VIEW = 6071;
	public static final int RES_MOSTRAR_COMPRA_KO_VIEW = 6072;
	public static final int MOSTRAR_COMPRA_POR_ID_CLIENTE_VIEW = 6080;
	public static final int RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_OK_VIEW = 6081;
	public static final int RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_KO_VIEW = 6082;
	public static final int QUITAR_PRODUCTO_VIEW = 6090;
	public static final int RES_QUITAR_PRODUCTO_OK_VIEW = 6091;
	public static final int RES_QUITAR_PRODUCTO_KO_VIEW = 6092;
	public static final int MOSTRAR_CARRITO_VIEW = 6100;
	public static final int RES_MOSTRAR_CARRITO_OK_VIEW = 6101;
	public static final int RES_MOSTRAR_CARRITO_KO_VIEW = 6102;
	public static final int MODIFICAR_COMPRA_VIEW = 6200;
	public static final int RES_MODIFICAR_COMPRA_OK_VIEW = 6201;
	public static final int RES_MODIFICAR_COMPRA_KO_VIEW = 6202;

	// ---------------EVENTOS DE ERROR COMPRA----------------------------

	public static final int ERROR_ABRIR_COMPRA = 6103;
	public static final int ERROR_CONSULTAR_COMPRA = 6104;
	public static final int ERROR_ANYADIR_PRODUCTO = 6105;
	public static final int ERROR_DEVOLUCION_PRODUCTO = 6106;
	public static final int ERROR_LISTAR_COMPRA = 6107;
	public static final int ERROR_MOSTRAR_COMPRA_POR_ID_CLIENTE = 6108;
	public static final int ERROR_QUITAR_PREODUCTO = 6109;
	public static final int ERROR_MODIFICAR_COMPRA = 6110;
	
	// ---------------EVENTOS EXITO COMPRA ------------------------------
	
	public static final int EXITO_ABRIR_COMPRA = 6203;
	public static final int EXITO_CONSULTAR_COMPRA = 6204;
	public static final int EXITO_ANYADIR_PRODUCTO = 6205;
	public static final int EXITO_DEVOLUCION_PRODUCTO = 6206;
	public static final int EXITO_LISTAR_COMPRA = 6207;
	public static final int EXITO_MOSTRAR_COMPRA_POR_ID_CLIENTE = 6208;
	public static final int EXITO_QUITAR_PREODUCTO = 6209;
	public static final int EXITO_MODIFICAR_COMPRA = 6210;
	public static final int EXITO_CERRAR_COMPRA = 6211;
	
	/*********************** COMPRA CAFETERIA *****************************/
	public static final int COMPRA_CAF_VIEW = 60000;
	public static final int ABRIR_COMPRA_CAF_VIEW = 60010;
	public static final int RES_ABRIR_COMPRA_CAF_OK_VIEW = 60011;
	public static final int RES_ABRIR_COMPRA_CAF_KO_VIEW = 60012;
	public static final int ANYADIR_PRODUCTO_CAF_VIEW = 60020;
	public static final int RES_ANYADIR_PRODUCTO_CAF_OK_VIEW = 60021;
	public static final int RES_ANYADIR_PRODUCTO_CAF_KO_VIEW = 60022;
	public static final int CERRAR_COMPRA_CAF_VIEW = 60030;
	public static final int RES_CERRAR_COMPRA_CAF_OK_VIEW = 60031;
	public static final int RES_CERRAR_COMPRA_CAF_KO_VIEW = 60032;
	public static final int CONSULTAR_COMPRA_CAF_VIEW = 60040;
	public static final int RES_CONSULTAR_COMPRA_CAF_OK_VIEW = 60041;
	public static final int RES_CONSULTAR_COMPRA_CAF_KO_VIEW = 60042;
	public static final int RES_CONSULTAR_COMPRA_CAF_ID_OK_VIEW = 60300;
	public static final int DEVOLUCION_PRODUCTO_CAF_VIEW = 60050;
	public static final int RES_DEVOLUCION_PRODUCTO_CAF_OK_VIEW = 60051;
	public static final int RES_DEVOLUCION_PRODUCTO_CAF_KO_VIEW = 60052;
	public static final int RES_DEVOLUCION_PRODUCTO_CAF_ID_OK_VIEW = 60053;
	public static final int LISTAR_COMPRA_CAF_VIEW = 60060;
	public static final int MOSTRAR_COMPRA_CAF_VIEW = 60070;
	public static final int RES_MOSTRAR_COMPRA_CAF_OK_VIEW = 60071;
	public static final int RES_MOSTRAR_COMPRA_CAF_KO_VIEW = 60072;
//	public static final int MOSTRAR_COMPRA_CAF_POR_ID_CLIENTE_VIEW = 60080;
//	public static final int RES_MOSTRAR_COMPRA_CAF_POR_ID_CLIENTE_OK_VIEW = 60081;
//	public static final int RES_MOSTRAR_COMPRA_CAF_POR_ID_CLIENTE_KO_VIEW = 60082;
	public static final int QUITAR_PRODUCTO_CAF_VIEW = 60090;
	public static final int RES_QUITAR_PRODUCTO_CAF_OK_VIEW = 60091;
	public static final int RES_QUITAR_PRODUCTO_CAF_KO_VIEW = 60092;
	public static final int MOSTRAR_CARRITO_CAF_VIEW = 60100;
	public static final int RES_MOSTRAR_CARRITO_CAF_OK_VIEW = 60101;
	public static final int RES_MOSTRAR_CARRITO_CAF_KO_VIEW = 60102;
	public static final int MODIFICAR_COMPRA_CAF_VIEW = 60200;
	public static final int RES_MODIFICAR_COMPRA_CAF_OK_VIEW = 60201;
	public static final int RES_MODIFICAR_COMPRA_CAF_KO_VIEW = 60202;

	// ---------------EVENTOS DE ERROR COMPRA CAFETERIA ----------------------------

	public static final int ERROR_ABRIR_COMPRA_CAF = 60103;
	public static final int ERROR_CONSULTAR_COMPRA_CAF = 60104;
	public static final int ERROR_ANYADIR_PRODUCTO_CAF = 60105;
	public static final int ERROR_DEVOLUCION_PRODUCTO_CAF = 60106;
	public static final int ERROR_LISTAR_COMPRA_CAF = 60107;
//	public static final int ERROR_MOSTRAR_COMPRA_CAF_POR_ID_CLIENTE = 60108;
	public static final int ERROR_QUITAR_PRODUCTO_CAF = 60109;
	public static final int ERROR_MODIFICAR_COMPRA_CAF = 60110;
	
	// ---------------EVENTOS EXITO COMPRA CAFETERIA ------------------------------
	
	public static final int EXITO_ABRIR_COMPRA_CAF = 60203;
	public static final int EXITO_CONSULTAR_COMPRA_CAF = 60204;
	public static final int EXITO_ANYADIR_PRODUCTO_CAF = 60205;
	public static final int EXITO_DEVOLUCION_PRODUCTO_CAF = 60206;
	public static final int EXITO_LISTAR_COMPRA_CAF = 60207;
//	public static final int EXITO_MOSTRAR_COMPRA_CAF_POR_ID_CLIENTE = 60208;
	public static final int EXITO_QUITAR_PRODUCTO_CAF = 60209;
	public static final int EXITO_MODIFICAR_COMPRA_CAF = 60210;
	public static final int EXITO_CERRAR_COMPRA_CAF = 60211;
	
	/*********************** MARCA *****************************/
	public static final int MARCA_VIEW = 7000;
	public static final int ALTA_MARCA_VIEW = 7010;
	public static final int ALTA_MARCA_OK_VIEW = 7011;
	public static final int ALTA_MARCA_KO_VIEW = 7012;
	public static final int RES_ALTA_MARCA_OK = 7013;
	public static final int RES_ALTA_MARCA_KO = 7014;
	public static final int BAJA_MARCA_VIEW = 7020;
	public static final int BAJA_MARCA_OK_VIEW = 7021;
	public static final int BAJA_MARCA_KO_VIEW = 7022;
	public static final int RES_BAJA_MARCA_OK = 7023;
	public static final int CONSULTAR_MARCA_VIEW = 7030;
	public static final int CONSULTAR_MARCA_OK_VIEW = 7031;
	public static final int CONSULTAR_MARCA_KO_VIEW = 7032;
	public static final int RES_CONSULTAR_MARCA_OK = 7034;
	public static final int LISTAR_MARCA_VIEW = 7040;
	public static final int MODIFICAR_MARCA_VIEW = 7050;
	public static final int MODIFICAR_MARCA_OK_VIEW = 7051;
	public static final int MODIFICAR_MARCA_KO_VIEW = 7052;
	public static final int RES_MODIFICAR_MARCA_OK = 7053;
	public static final int MARCA_ERROR_YA_EXISTE = 7100;
	public static final int MARCA_ERROR_NO_EXISTE = 7101;
	public static final int MARCA_ERROR_DATOS_NO_VALIDOS = 7102;
	public static final int MARCA_ERROR_DESCONOCIDO = 7103;
	public static final int MARCA_ERROR_PRODUCTO_NO_ENCONTRADO = 7104;
	public static final int MARCA_ERROR_PRODUCTOS_VINCULADOS_ACTIVOS = 7107;
	public static final int RES_MOSTRARMARCASPORIDPRODUCTO_OK_VIEW = 7105;
	public static final int MOSTRARMARCASPORIDPRODUCTO_VIEW = 7106;
	
	/******************** PERSONAL CAFETERIA ******************/
	
	public static final int PERSONALCAFETERIA_VIEW = 8000;
	public static final int ALTA_PERSONALCAFETERIA_VIEW = 8010;
	public static final int ALTA_PERSONALCAFETERIA_OK_VIEW = 8011;
	public static final int ALTA_PERSONALCAFETERIA_KO_VIEW = 8012;
	public static final int BAJA_PERSONALCAFETERIA_VIEW = 8020;
	public static final int BAJA_PERSONALCAFETERIA_OK_VIEW = 8021;
	public static final int BAJA_PERSONALCAFETERIA_KO_VIEW = 8022;
	public static final int CONSULTAR_PERSONALCAFETERIA_VIEW = 8030;
	public static final int CONSULTAR_PERSONALCAFETERIA_OK_VIEW = 8031;
	public static final int CONSULTAR_PERSONALCAFETERIA_KO_VIEW = 8032;
	public static final int LISTAR_PERSONALCAFETERIA_VIEW = 8040;
	public static final int MODIFICAR_PERSONALCAFETERIA_VIEW = 8050;
	public static final int MODIFICAR_PERSONALCAFETERIA_OK_VIEW = 8051;
	public static final int MODIFICAR_PERSONALCAFETERIA_KO_VIEW = 8052;
	public static final int MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_VIEW = 8060;
	public static final int MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_OK_VIEW = 8061;
	public static final int MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_KO_VIEW = 8062;
	
	// ---------------EVENTOS EXITO PERSONAL CAFETERIA

		public static final int EXITO_PERSONALCAFETERIA_ALTA = 8100;
		public static final int EXITO_PERSONALCAFETERIA_BAJA = 8101;
		public static final int EXITO_PERSONALCAFETERIA_CONSULTAR = 8102;
		public static final int EXITO_PERSONALCAFETERIA_MODIFICAR = 8103;
		public static final int EXITO_PERSONALCAFETERIA_MOSTRAR_POR_ID_TURNO = 8104;

		// ---------------EVENTOS DE ERROR PERSONAL CAFETERIA

		public static final int ERROR_PERSONALCAFETERIA_EXISTE = 8200;
		public static final int ERROR_PERSONALCAFETERIA_NO_EXISTE = 8201;
		public static final int ERROR_PERSONALCAFETERIA_YA_DADO_DE_BAJA = 8202;
		public static final int ERROR_NO_HAY_PERSONALCAFETERIA_ASOCIADO_A_ID_TURNO = 8203;
		public static final int ERROR_PERSONALCAFETERIA_ALTA = 8204;
		public static final int ERROR_PERSONALCAFETERIA_BAJA = 8205;
		public static final int ERROR_PERSONALCAFETERIA_CONSULTAR = 8206;
		public static final int ERROR_PERSONALCAFETERIA_MODIFICAR = 8207;
		public static final int ERROR_PERSONALCAFETERIA_MOSTRAR_POR_ID_TURNO = 8208;
		public static final int ERROR_PERSONALCAFETERIA_YA_ACTIVO = 8209;
	
	/*********************** ALERGENO *****************************/
		public static final int ALERGENO_VIEW = 9000;
		public static final int ALTA_ALERGENO_VIEW = 9010;
		public static final int ALTA_ALERGENO_OK_VIEW =9011;
		public static final int ALTA_ALERGENO_KO_VIEW = 9012;
		public static final int BAJA_ALERGENO_VIEW = 9020;
		public static final int BAJA_ALERGENO_OK_VIEW = 9021;
		public static final int BAJA_ALERGENO_KO_VIEW = 9022;
		public static final int CONSULTAR_ALERGENO_VIEW = 9030;
		public static final int CONSULTAR_ALERGENO_OK_VIEW = 9031;
		public static final int CONSULTAR_ALERGENO_KO_VIEW = 9032;
		public static final int LISTAR_ALERGENO_VIEW = 9040;
		public static final int MODIFICAR_ALERGENO_VIEW = 9050;
		public static final int MODIFICAR_ALERGENO_OK_VIEW = 9051;
		public static final int MODIFICAR_ALERGENO_KO_VIEW = 9052;
		public static final int MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW = 9060;
		public static final int MOSTRAR_ALERGENO_PORID_PRODUCTO_OK_VIEW = 9061;
		public static final int MOSTRAR_ALERGENO_PORID_PRODUCTO_KO_VIEW = 9062;
		public static final int VINCULAR_ALERGENO_VIEW = 9065;
		public static final int VINCULAR_ALERGENO_OK_VIEW = 9066;
		public static final int VINCULAR_ALERGENO_KO_VIEW = 9067;
		public static final int DESVINCULAR_ALERGENO_VIEW = 9068;
		public static final int DESVINCULAR_ALERGENO_OK_VIEW = 9069;
		public static final int DESVINCULAR_ALERGENO_KO_VIEW = 9070;
		
		public static final int ALERGENO_CANCEL_VIEW = 9500;
		// ---------------EVENTOS EXITO ALERGENO
		public static final int EXITO_ALERGENO_ALTA = 9100;
		public static final int EXITO_ALERGENO_BAJA = 9101;
		public static final int EXITO_ALERGENO_CONSULTAR = 9102;
		public static final int EXITO_ALERGENO_MODIFICAR = 9103;
		public static final int EXITO_ALERGENO_MOSTRAR_PORID_PRODUCTO = 9104;
		public static final int EXITO_ALERGENO_VINCULAR = 9105;
		public static final int EXITO_ALERGENO_DESVINCULAR = 9106;

		// ---------------EVENTOS DE ERROR ALERGENO
		public static final int ERROR_ALERGENO_EXISTE = 9200;
		public static final int ERROR_ALERGENO_NO_EXISTE = 9201;
		public static final int ERROR_ALERGENO_YA_DADO_DE_BAJA = 9202;
		public static final int ERROR_NO_HAY_ALERGENO_ASOCIADO_A_ID_PRODUCTO = 9203;
		public static final int ERROR_ALERGENO_ALTA = 9204;
		public static final int ERROR_ALERGENO_BAJA = 9205;
		public static final int ERROR_ALERGENO_CONSULTAR = 9206;
		public static final int ERROR_ALERGENO_MODIFICAR = 9207;
		public static final int ERROR_ALERGENO_MOSTRAR_POR_ID_TURNO = 9208;
		public static final int ERROR_ALERGENO_VINCULAR = 9209;
		public static final int ERROR_ALERGENO_DESVINCULAR = 9210;
		public static final int ERROR_ALERGENO_ASOCIADO = 9211;
		public static final int ERROR_ALERGENO_DADO_BAJA = 9212;
	
	// ---------------EVENTOS DE CANCEL ------------------------------
	
	public static final int CANCEL_VIEW = 10000;


}