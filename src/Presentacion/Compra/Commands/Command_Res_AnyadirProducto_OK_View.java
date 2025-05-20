package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCarrito;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_AnyadirProducto_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object[] o = (Object[]) context.getData();
		TCarrito car = FactoryNeg.getInstance().generateSACompra().addProduct((TCarrito) o[0], (Integer) o[1],
				(Integer) o[2], (String) o[3]);
		Context new_context = new Context();
		new_context.setData(car);
		
		if (car == null) 
			new_context.setEvent(Events.ERROR_ANYADIR_PRODUCTO);

		else
			new_context.setEvent(Events.EXITO_ANYADIR_PRODUCTO);
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.COMPRA_VIEW,new_context);
	}
	

}
