package Integracion.Queries;

import Integracion.Personal.PersonalPorNumHabilidades;
import Integracion.Personal.PersonalPorRangoSueldo;

public class FactoryQueryImp extends FactoryQuery{

	@Override
	public Query nuevaQuery(String nombre) {
		
		switch(nombre) {
		case "PersonalPorNumHabilidades":
			return new PersonalPorNumHabilidades();
		case "PersonalPorRangoSueldo":
			return new PersonalPorRangoSueldo();
			
		}
		
		return null;
	}

}
