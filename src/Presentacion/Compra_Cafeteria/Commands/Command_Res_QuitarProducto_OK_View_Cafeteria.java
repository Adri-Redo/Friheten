package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaCarrito;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_QuitarProducto_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object[] c = (Object[]) context.getData(); // [TCarrito, TLineaCarrito]
		
		TCarrito carrito = (TCarrito) c[0];
		TLineaCarrito lineaCarrito = (TLineaCarrito) c[1];
		
		TCarrito newCar = FactoryNeg.getInstance().generateSACompraCafeteria().deleteProduct(carrito, lineaCarrito);
		Context new_context = new Context();
		new_context.setData(newCar);
		
		if (newCar == null) 
			new_context.setEvent(Events.QUITAR_PRODUCTO_CAF_VIEW);

		else 
			new_context.setEvent(Events.EXITO_QUITAR_PRODUCTO_CAF);

		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.COMPRA_CAF_VIEW, new_context);
	}

}
