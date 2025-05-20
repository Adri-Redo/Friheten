package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCompra;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_DevolucionProducto_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object[] o5 = (Object[]) context.getData();
		FactoryNeg.getInstance().generateSACompra().update((TCompra) o5[0]);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.COMPRA_VIEW, context);

	}

}
