/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaAlergenoOk_View implements Command {
	
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    int resultado = FactoryNeg.getInstance().generateSAAlergeno().delete((Integer) context.getData());
	    Context new_context = new Context();
	    TAlergeno id = new TAlergeno();
	    id.setIdAlergeno((Integer) context.getData());
	    new_context.setData(id);
	    

	    if (resultado == -1) {
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	    } else if (resultado == -2) {
	        new_context.setEvent(Events.ERROR_ALERGENO_YA_DADO_DE_BAJA);
	    } else if (resultado == -3) {
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	    } else if (resultado == -4) {
	        new_context.setEvent(Events.ERROR_ALERGENO_ASOCIADO);
	    } else {
	        new_context.setEvent(Events.EXITO_ALERGENO_BAJA);
	    }

	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_VIEW, new_context);
	}
}