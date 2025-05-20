/**
 * 
 */
package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;


public class Command_ModificarProductoOk_View implements Command {


	public Entry<Integer, Context> execute(Context context) 
	{
		if (esNumerico(context.getData()))
		{
			int idProducto = (int) context.getData();
			
			TProductoCafeteria pr = FactoryNeg.getInstance().generateSAProducto().read(idProducto);
			Context contextnew =  new Context();
			
			if (pr != null)
			{
				contextnew.setEvent(Events.EXITO_PRODUCTO_CONSULTAR);
				contextnew.setData(pr);
				return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_PRODUCTO_VIEW,contextnew);
			}
		}
		else
		{
			TProductoCafeteria rp = (TProductoCafeteria) context.getData();
			int res = FactoryNeg.getInstance().generateSAProducto().update(rp);
			Context new_context = new Context();
			 
			if (res < 0 ) 
			{
					new_context.setEvent(Events.ERROR_PRODUCTO_NO_EXISTE);
					return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PRODUCTO_NO_EXISTE,new_context);
			}
			else 
			 {
					new_context.setEvent(Events.EXITO_PRODUCTO_MODIFICAR);
					new_context.setData(rp);
					return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_PRODUCTO_VIEW,new_context);
			}
			 
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_PRODUCTO_VIEW,null);
	}
	

	public static boolean esNumerico(Object obj) {
	    return obj instanceof Number || 
	           obj instanceof Integer || 
	           obj instanceof Double || 
	           obj instanceof Float || 
	           obj instanceof Long;
	}
	 
	
}