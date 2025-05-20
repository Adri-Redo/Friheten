package Presentacion.Compra_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra_Cafeteria.TOACompraCafeteria;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_ConsultarCompra_OK_View_Cafeteria implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TOACompraCafeteria tConsultar = FactoryNeg.getInstance().generateSACompraCafeteria().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (tConsultar == null) // No existe
			new_context.setEvent(Events.ERROR_CONSULTAR_COMPRA_CAF);

		else {
			new_context.setEvent(Events.EXITO_CONSULTAR_COMPRA_CAF);
			Object[] data = new Object[2];
			data[0] = tConsultar;
			data[1] = FactoryNeg.getInstance().generateSAProducto().readAll();
			new_context.setData(data);
			
		}
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_COMPRA_CAF_VIEW, new_context);
	}
	
//	Object compra = FactoryNeg.getInstance().generateSACompra().read((int) data);
//	if (compra == null) {
//		FactoryPresentacion.getInstance().generateGUI(Events.ERROR_CONSULTAR_COMPRA)
//				.actualizar(Events.ERROR_CONSULTAR_COMPRA, compra);
//	} else {
//		FactoryPresentacion.getInstance().generateGUI(event).actualizar(event, compra);
//	}

}
