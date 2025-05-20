package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCompra;
import Negocio.Compra_Cafeteria.TCompraCafeteria;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_ModificarCompra_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCompraCafeteria compra = (TCompraCafeteria) context.getData();
		
		Integer res = FactoryNeg.getInstance().generateSACompraCafeteria().update(compra);
		Context new_context = new Context();
		if(res < 0) {
			new_context.setEvent(Events.ERROR_MODIFICAR_COMPRA_CAF);
			new_context.setData("Error en modificar");
		}else {
			new_context.setEvent(Events.EXITO_MODIFICAR_COMPRA_CAF);
			new_context.setData("Se ha modificado con exito");
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.COMPRA_CAF_VIEW, new_context);
	}

}
