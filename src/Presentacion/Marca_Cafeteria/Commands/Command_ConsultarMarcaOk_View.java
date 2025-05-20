/**
 * 
 */
package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_ConsultarMarcaOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) {
		TMarca tMarca = FactoryNeg.getInstance().generateSAMarca().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if(tMarca == null) {
			new_context.setEvent(Events.MARCA_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW, new_context);	
		}else {
			new_context.setEvent(Events.RES_CONSULTAR_MARCA_OK );
			new_context.setData(tMarca);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_MARCA_VIEW, new_context);
		}
	}
}