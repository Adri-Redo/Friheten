package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra_Cafeteria.TOACompraCafeteria;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_DevolucionProducto_ID_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TOACompraCafeteria devComprasID = FactoryNeg.getInstance().generateSACompraCafeteria().read((int) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		
		if (devComprasID == null)
			new_context.setEvent(Events.ERROR_DEVOLUCION_PRODUCTO_CAF);
		else {
			new_context.setEvent(Events.EXITO_DEVOLUCION_PRODUCTO_CAF);
			Object[] data = new Object[2];
			data[0] = devComprasID;
			
			data[1] = FactoryNeg.getInstance().generateSAProducto().readAll();
			new_context.setData(data);
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.DEVOLUCION_PRODUCTO_CAF_VIEW,new_context);
	}

}
