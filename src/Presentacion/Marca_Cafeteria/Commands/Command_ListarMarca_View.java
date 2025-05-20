package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarMarca_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		// TODO Auto-generated method stub
		List<TMarca> listarMarcas = FactoryNeg.getInstance().generateSAMarca().readAll();
		Context new_context = new Context();
		new_context.setData(listarMarcas);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_MARCA_VIEW, new_context);
	}

}
