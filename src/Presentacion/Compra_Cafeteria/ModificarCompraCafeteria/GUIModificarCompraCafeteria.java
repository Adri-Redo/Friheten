package Presentacion.Compra_Cafeteria.ModificarCompraCafeteria;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Compra.TCompra;
import Negocio.Compra_Cafeteria.TCompraCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIModificarCompraCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JTextField idCompraField;
    private JTextField idPersonalField;
    private JTextField fechaField;

    public GUIModificarCompraCafeteria() {
        super("Modificar Personal o Fecha de Compra");
        initGUI();
    }

    private void initGUI() {
    	this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		});
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // IDCompra
        JPanel idCompraPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idCompraLabel = new JLabel("IDCompra a modificar: ");
        idCompraField = new JTextField(20);
        idCompraPanel.add(idCompraLabel);
        idCompraPanel.add(idCompraField);

        // IDPersonal
        JPanel idPersonalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idPersonalLabel = new JLabel("Nuevo IDPersonal: ");
        idPersonalField = new JTextField(20);
        idPersonalPanel.add(idPersonalLabel);
        idPersonalPanel.add(idPersonalField);

        // Fecha
        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel fechaLabel = new JLabel("Nueva Fecha: ");
        fechaField = new JTextField(20);
        fechaPanel.add(fechaLabel);
        fechaPanel.add(fechaField);

        // Botones Aceptar y Cancelar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
        
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TCompraCafeteria tcompra = new TCompraCafeteria();
            	tcompra.setId(Integer.parseInt(idCompraField.getText()));
            	tcompra.setIdPersonal(Integer.parseInt(idPersonalField.getText()));
            	tcompra.setFecha(getFecha());
                Controller.getInstance().handle(new Context(Events.RES_MODIFICAR_COMPRA_CAF_OK_VIEW, tcompra));
                dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));
                dispose();
            }
        });
        
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        // Agregar todos los paneles al panel principal
        mainPanel.add(idCompraPanel);
        mainPanel.add(idPersonalPanel);
        mainPanel.add(fechaPanel);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 300));
        setLocation(200, 200);
        pack();
        setVisible(true);
    }

    // Métodos para obtener los valores ingresados
    public String getIdCompra() {
        return idCompraField.getText();
    }

    public String getIdPersonal() {
        return idPersonalField.getText();
    }

    public String getFecha() {
        return fechaField.getText();
    }

	@Override
	public void actualizar(Context context) {
		
		
	}
}
