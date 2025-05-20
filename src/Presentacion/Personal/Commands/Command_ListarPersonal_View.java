package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarPersonal_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TPersonal> tp = FactoryNeg.getInstance().generateSAPersonal().readAll();
		Context new_context =  new Context();
		new_context.setData(tp);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_PERSONAL_VIEW,new_context);
	}

}
