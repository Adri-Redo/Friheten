/**
 * 
 */
package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
import Negocio.Producto_Cafeteria.Producto;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_AltaMarcaOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) {
		
		
		TMarca marca = (TMarca) context.getData();
        
        // Validación básica de datos
        if(marca.getNombre() == null || marca.getNombre().isEmpty() || 
           marca.getCategoria() == null || marca.getCategoria().isEmpty()) {
            Context errorContext = new Context(
                Events.MARCA_ERROR_DATOS_NO_VALIDOS, 
                "Nombre y categoría son campos obligatorios"
            );
            return new AbstractMap.SimpleEntry<>(Events.MARCA_VIEW, errorContext);
        }
		
		int res = FactoryNeg.getInstance().generateSAMarca().create(marca);
		
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if(res == -1) {
			new_context.setEvent(Events.MARCA_ERROR_YA_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);
		}else {
			new_context.setEvent(Events.RES_ALTA_MARCA_OK);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);
		}
	}
}