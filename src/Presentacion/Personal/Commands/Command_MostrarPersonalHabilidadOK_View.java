package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarPersonalHabilidadOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer hab = (Integer) context.getData();
		List<TPersonal> personal = FactoryNeg.getInstance().generateSAPersonal().readPersonalByHabilidad(hab);
		Context new_context =  new Context();
		new_context.setData(personal);
		if (personal == null) {//Habilidad no existe
			new_context.setEvent(Events.ERROR_HABILIDAD_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_HABILIDAD_NO_EXISTE,new_context);	
		}else if (personal.isEmpty()) {//Nadie tiene esa habilidad
			new_context.setEvent(Events.ERROR_NO_TIENE_NINGUNA_PERSONA_VINCULADA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_NO_TIENE_NINGUNA_PERSONA_VINCULADA,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_MOSTRAR_PERSONAL_DE_UNA_HABILIDAD);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW,new_context);
		}
	}

}
