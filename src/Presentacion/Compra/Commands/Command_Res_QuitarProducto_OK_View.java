package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCarrito;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_QuitarProducto_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object[] c = (Object[]) context.getData();

		TCarrito newCar = FactoryNeg.getInstance().generateSACompra().deleteProduct((TCarrito) c[0], (String) c[1]);
		Context new_context = new Context();
		new_context.setData(newCar);
		
		if (newCar == null) 
			new_context.setEvent(Events.QUITAR_PRODUCTO_VIEW);

		else 
			new_context.setEvent(Events.EXITO_QUITAR_PREODUCTO);

		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.COMPRA_VIEW, new_context);
	}

}
