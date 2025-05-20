package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCompra;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_ModificarCompra_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCompra compra = (TCompra) context.getData();
		
		Integer res = FactoryNeg.getInstance().generateSACompra().update(compra);
		Context new_context = new Context();
		if(res < 0) {
			new_context.setEvent(Events.ERROR_MODIFICAR_COMPRA);
			new_context.setData("Error en modificar");
		}else {
			new_context.setEvent(Events.EXITO_MODIFICAR_COMPRA);
			new_context.setData("Se ha modificado con exito");
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.COMPRA_VIEW, new Context(Events.COMPRA_VIEW, new_context));
	}

}
