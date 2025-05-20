package Presentacion.Alergeno_Cafeteria.ListarAlergeno;



import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Alergeno_Cafeteria.TAlergeno;


/** 
* Clase para listar alérgenos.
*/
public class GUIListarAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel _tableModel;
    private Set<TAlergeno> _listaAlergenos;

    public GUIListarAlergeno() {
        super("LISTAR ALÉRGENOS");
        this._listaAlergenos = new HashSet<>();
        this.initGUI();
    }

    public void initGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel textPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        JLabel label = new JLabel("Listado de todos los alérgenos de la Base de Datos");
        textPanel.add(label);
        mainPanel.add(textPanel);

        this._tableModel = new DefaultTableModel(
                new Object[] { "Nombre", "Riesgo", "Fuente" }, 0);

        JTable table = new JTable(this._tableModel) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        // Desactivar el ajuste automático de las columnas
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Modelo de las columnas de nuestra tabla
        TableColumnModel columnModel = table.getColumnModel();

        // Establecer el ancho preferido para cada columna
        columnModel.getColumn(0).setPreferredWidth(200); // Nombre
        columnModel.getColumn(1).setPreferredWidth(100); // Riesgo
        columnModel.getColumn(2).setPreferredWidth(300); // Fuente

        table.setPreferredScrollableViewportSize(new Dimension(600, 400));

        JScrollPane scroll = new JScrollPane(table);
        infoPanel.add(scroll);
        mainPanel.add(infoPanel);

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(700, 500));
        setLocation(500, 500);
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(Context context) {
        this._tableModel.setRowCount(0);

        this._listaAlergenos = new HashSet<>((Set<TAlergeno>) context.getData());
        if (!_listaAlergenos.isEmpty()) {
            for (TAlergeno tA : this._listaAlergenos) {
                if (tA.isActivo()) {
                    Object[] rowData = { tA.getNombre(), tA.getRiesgo(), tA.getFuente() };
                    this._tableModel.addRow(rowData);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay alérgenos dados de alta en la base de datos", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
