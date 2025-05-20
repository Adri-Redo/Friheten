package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarMarca_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_MARCA_VIEW,context);
	}

}
