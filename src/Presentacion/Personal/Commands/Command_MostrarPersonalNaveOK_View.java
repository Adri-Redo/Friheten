package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarPersonalNaveOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TPersonal> personal = FactoryNeg.getInstance().generateSAPersonal()
				.readPersonalByNave((Integer)context.getData());
		Context new_context =  new Context();
		new_context.setData(personal);
		if (personal == null) {// La nave no existe
			new_context.setEvent(Events.NAVE_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_ERROR_NO_EXISTE,new_context);	
		}else if (personal.isEmpty()) {//Nadie asociado a ese id de nave
			new_context.setEvent(Events.ERROR_NO_HAY_PERSONAL_ASOCIADO_A_TAL_ID_NAVE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_NO_HAY_PERSONAL_ASOCIADO_A_TAL_ID_NAVE,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONAL_MOSTRAR_POR_ID_NAVE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW,new_context);
		}
	}

}
