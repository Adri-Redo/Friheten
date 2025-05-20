package Presentacion.Command;

import Presentacion.Controller.Events;

public abstract class CommandFactory {
	private static CommandFactory instance;
	
	public static CommandFactory getInstance() {
		
        if (instance == null)
            instance = new CommandFactoryImp();

        return instance;
    
	}
	
	public abstract Command getCommand(int id);
	
}
