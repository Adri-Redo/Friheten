package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarCompra_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Context new_context = new Context();
		Object[] data = new Object[2];
		data[0] = FactoryNeg.getInstance().generateSACompra().readAll();
		
		data[1] = FactoryNeg.getInstance().generateSAMueble().readAll();

		new_context.setData(data);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_COMPRA_VIEW, new_context);
	}

}
