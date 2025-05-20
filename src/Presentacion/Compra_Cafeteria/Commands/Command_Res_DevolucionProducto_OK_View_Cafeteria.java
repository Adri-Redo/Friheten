package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCompra;
import Negocio.Compra_Cafeteria.TCompraCafeteria;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_DevolucionProducto_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object[] o5 = (Object[]) context.getData(); // La estructura que tendrá el o5 sera [IDCompra, IDProducto, Cantidad]
		Integer res = FactoryNeg.getInstance().generateSACompraCafeteria().returnProduct((Integer) o5[0], (Integer) o5[1], (Integer) o5[2]);
		Context new_context = new Context();
		if(res < 0) {
			new_context.setEvent(Events.ERROR_DEVOLUCION_PRODUCTO_CAF);
			new_context.setData("Error en modificar");
		}else {
			new_context.setEvent(Events.EXITO_DEVOLUCION_PRODUCTO_CAF);
			new_context.setData("Se ha devuelto con exito");
		}
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.COMPRA_CAF_VIEW, new_context);

	}

}
