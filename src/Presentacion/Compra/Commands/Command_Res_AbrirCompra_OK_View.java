package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TCompra;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_AbrirCompra_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCompra compra = (TCompra) context.getData();
		TCarrito carrito = FactoryNeg.getInstance().generateSACompra().createCarrito((TCompra) compra);
		Context new_context =  new Context();
		new_context.setData(context.getData());
		
		if (carrito == null)
			new_context.setEvent(Events.ERROR_ABRIR_COMPRA);
		else {
			new_context.setEvent(Events.EXITO_ABRIR_COMPRA);
			new_context.setData(carrito);
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.COMPRA_VIEW,new_context);
	}

}
