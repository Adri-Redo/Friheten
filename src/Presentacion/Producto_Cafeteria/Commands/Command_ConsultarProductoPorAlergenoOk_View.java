package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarProductoPorAlergenoOk_View implements Command {

    @Override
    public Entry<Integer, Context> execute(Context context) {

        Context new_context = new Context();
        Integer idAlergeno = null;

        if (context.getData() instanceof Integer) {
            idAlergeno = (Integer) context.getData();
        } else {
            new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_KO_VIEW);
            new_context.setData("Error interno: Tipo de dato incorrecto para ID de alergeno.");
            return new AbstractMap.SimpleEntry<>(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW, new_context);
        }

        try {
            Set<TProductoCafeteria> resultSet = FactoryNeg.getInstance().generateSAProducto().readByAlergenos(idAlergeno);

            if (resultSet == null || resultSet.isEmpty()) {
                new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_KO_VIEW);
                new_context.setData("No se encontraron productos para el alergeno con ID: " + idAlergeno);
            } else {
                Set<TProductoCafeteria> productList = new HashSet<>(resultSet);
                new_context.setData(productList);
                new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW);
            }

        } catch (Exception e) {
            System.err.println("Error ejecutando SAProducto.readByAlergenos: " + e.getMessage());
            e.printStackTrace();
            new_context.setEvent(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_KO_VIEW);
            new_context.setData("Error inesperado durante la consulta por alergeno: " + e.getMessage());
        }

        return new AbstractMap.SimpleEntry<>(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW, new_context);
    }
}
