package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarProducto_View  implements Command{

	@Override
	public Entry<Integer,Context> execute(Context context){
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PRODUCTO_VIEW,null);
	}
}
