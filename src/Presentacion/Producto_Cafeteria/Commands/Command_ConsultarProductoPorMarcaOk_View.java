/**
 *
 */
package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.ArrayList; // Import ArrayList
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarProductoPorMarcaOk_View implements Command {

    @Override
    public Entry<Integer, Context> execute(Context context) {

        Context new_context = new Context();
        Integer idMarca = null;

        if (context.getData() instanceof Integer) {
            idMarca = (Integer) context.getData();
        } else {
            new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW);
            new_context.setData("Error interno: Tipo de dato incorrecto para ID de marca.");
            return new AbstractMap.SimpleEntry<>(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW, new_context);
        }

        try {
            Set<TProductoCafeteria> resultSet = FactoryNeg.getInstance().generateSAProducto().readByIdMarca(idMarca);

            if (resultSet == null || resultSet.isEmpty()) {
                new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW);
                new_context.setData("No se encontraron productos para la marca con ID: " + idMarca);
            } else {
                Set<TProductoCafeteria> productList = new HashSet<>(resultSet);
                new_context.setData(productList);
                new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW);
            }

        } catch (Exception e) {
            System.err.println("Error ejecutando SAProducto.readByIdMarca: " + e.getMessage());
            e.printStackTrace();
            new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW);
            new_context.setData("Error inesperado durante la consulta: " + e.getMessage());
        }

        return new AbstractMap.SimpleEntry<>(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW, new_context);
    }
}
