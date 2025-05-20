package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarCompra_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		context.setData(FactoryNeg.getInstance().generateSACompraCafeteria().readAll());
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.LISTAR_COMPRA_CAF_VIEW, context);
	}

}
