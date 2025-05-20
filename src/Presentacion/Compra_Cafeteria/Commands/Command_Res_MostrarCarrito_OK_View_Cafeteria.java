package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_MostrarCarrito_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object compras = FactoryNeg.getInstance().generateSACompraCafeteria().readAll();
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if (compras == null) 
			new_context.setEvent(Events.ERROR_LISTAR_COMPRA_CAF);
		else {
			new_context.setEvent(Events.EXITO_LISTAR_COMPRA_CAF);
			new_context.setData(compras);
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.COMPRA_CAF_VIEW,new_context);
	}

}
