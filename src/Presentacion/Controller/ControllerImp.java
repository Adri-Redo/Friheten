package Presentacion.Controller;

import java.util.Map.Entry;

import Presentacion.Observer;
import Presentacion.Command.Command;
import Presentacion.Command.CommandFactory;
import Presentacion.Context.Context;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class ControllerImp extends Controller {

	@Override
	public void handle(Context context) {
		
		// en este caso commandFactory actua de commandMapper
		CommandFactory c_factory = CommandFactory.getInstance();
		Command currCommand = c_factory.getCommand(context.getEvent());
		Entry<Integer,Context> responseContext = currCommand.execute(context);
		
		//genera la vista correspondiente
		Observer vista = FactoryPresentacion.getInstance().generateGUI(responseContext.getKey());
		if(vista != null)
		{
		
			vista.actualizar(responseContext.getValue());
		}
		
		
		
	}

}
