package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarPersonalNifOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		String nif = (String) context.getData();
		TPersonal personal = FactoryNeg.getInstance().generateSAPersonal()
				.readPersonalByNif(nif);
		Context new_context =  new Context();
		new_context.setData(personal);
		
		if (personal == null) {// El Nif no existe
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_NO_EXISTE,new_context);	
		} else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONAL_MOSTRAR_POR_NIF);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_PERSONAL_PORNIF_VIEW,new_context);
		}
	}
	
}
