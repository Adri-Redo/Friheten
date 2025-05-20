package Presentacion.Alergeno_Cafeteria.ListarAlergenosProducto;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIListarAlergenosProducto extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private DefaultTableModel _tableModel;

    public GUIListarAlergenosProducto() {
        super("LISTAR ALÉRGENOS DE UN PRODUCTO");
        this.initGUI();
    }

    private void initGUI() {

        JPanel panelPrincipal = new JPanel();
        JPanel panelParaProducto = new JPanel();

        JLabel mostrarProducto = new JLabel("Introduzca el ID del producto: ");
        JTextField nombreTexto = new JTextField(20);
        nombreTexto.setEditable(true);
        nombreTexto.setToolTipText(
                "ID del producto del cual quieres mostrar los alérgenos a los que está vinculado.");

        JButton ok = new JButton("OK");
        ok.setToolTipText("Mostrar alérgenos vinculados a ese producto.");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validar = true;
                String idProducto = nombreTexto.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(idProducto);
                    if (id <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El ID del producto debe ser mayor que 0.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException m) {
                    validar = false;
                    JOptionPane.showMessageDialog(null,
                            "El ID del producto debe ser un número entero mayor que 0, no puede contener letras ni estar vacío.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                if (validar) {
                    Controller.getInstance().handle(new Context(Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_OK_VIEW, id));
                }

                dispose();
            }
        });

        JButton ko = new JButton("CANCEL");
        ko.setToolTipText("Cancelar");
        ko.addActionListener((e) -> {
            Controller.getInstance().handle(new Context(Events.ALERGENO_CANCEL_VIEW, null));
            dispose();
        });

        panelParaProducto.add(mostrarProducto);
        panelParaProducto.add(nombreTexto);
        panelParaProducto.add(ok);
        panelParaProducto.add(ko);

        panelPrincipal.add(panelParaProducto);

        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(Context context) {
        if (context.getData() != null) {
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            JPanel textPanel = new JPanel();
            JPanel infoPanel = new JPanel();

            JLabel label = new JLabel("Listado de todos los alérgenos vinculados al producto con el ID proporcionado.");
            textPanel.add(label);
            mainPanel.add(textPanel);

            this._tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre" }, 0);

            JTable table = new JTable(this._tableModel) {

                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            // Desactivar el ajuste automático de las columnas
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            // Modelo de las columnas de la tabla
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(200); // ID
            columnModel.getColumn(1).setPreferredWidth(200); // Nombre

            table.setPreferredScrollableViewportSize(new Dimension(400, 400));

            JScrollPane scroll = new JScrollPane(table);
            infoPanel.add(scroll);
            mainPanel.add(infoPanel);

            this._tableModel.setRowCount(0);
            Set<TAlergeno> listaAlergenos = new HashSet<>();
            if (context.getData() != null) {
                listaAlergenos = (Set<TAlergeno>) context.getData();
            }

            for (TAlergeno tA : listaAlergenos) {
                if (tA.isActivo()) {
                    Object[] rowData = { tA.getIdAlergeno(), tA.getNombre() };
                    this._tableModel.addRow(rowData);
                }
            }

            setContentPane(mainPanel);
            setPreferredSize(new Dimension(500, 500));
            setLocation(250, 250);
            pack();
            this.setLocationRelativeTo(null);
            setVisible(true);
        }
    }
}
