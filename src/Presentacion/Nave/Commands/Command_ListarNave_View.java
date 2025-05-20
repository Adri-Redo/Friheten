package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarNave_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context){
		List<TNave> listaNaves = FactoryNeg.getInstance().generateSANave().readAll();
		Context new_context = new Context();
		new_context.setData(listaNaves);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_NAVE_VIEW, new_context);
	}
}
