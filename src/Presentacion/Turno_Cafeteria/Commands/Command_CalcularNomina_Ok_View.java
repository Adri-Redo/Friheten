package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_CalcularNomina_Ok_View implements Command {
	@Override
	public Entry<Integer, Context> execute(Context context) {
		Double resultado = FactoryNeg.getInstance().generateSATurno().calcularNomina((int) context.getData());
		Context new_context = new Context();
		new_context.setData(resultado);

		if (resultado == -1) {// NO se ha encontrado el id de ese turno
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.TURNO_CALCULAR_NOMINA_VIEW,new_context);
			
		} 
		else if(resultado == -2) {
			new_context.setEvent(Events.ERROR_TURNO_CALCULAR_NOMINA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.TURNO_CALCULAR_NOMINA_VIEW,new_context);
		}
		else {
			new_context.setEvent(Events.EXITO_TURNO_CALCULAR_NOMINA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.TURNO_CALCULAR_NOMINA_VIEW,new_context);
		}
	}
}
