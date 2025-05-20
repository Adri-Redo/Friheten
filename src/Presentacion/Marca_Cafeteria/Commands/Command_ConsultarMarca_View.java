package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarMarca_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		// TODO Auto-generated method stub
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_MARCA_VIEW,context);
	}

}
