package Presentacion.Nave.ConsultarNave;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import Integracion.FactoryIntegracion.FactoryIntgr;
import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarNave extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel id;
	private JLabel localizacion;
	private JLabel nombre;
	private JLabel capacidad;

	public GUIConsultarNave() {
		super("Consultar Nave");
		
		initGUI();
	}

	private void initGUI() {
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel userPanel = new JPanel();
		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(layout);
		JPanel locationPanel = new JPanel();
		locationPanel.setLayout(layout);
		JPanel namePanel = new JPanel();
		namePanel.setLayout(layout);
		JPanel capacityPanel = new JPanel();
		capacityPanel.setLayout(layout);
		JPanel activePanel = new JPanel();
		activePanel.setLayout(layout);
		
		
		JLabel consultarNave = new JLabel("Introduzca el ID de la nave: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la nave que quieres consultar.");
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		}); 
		
		JButton consultarButton = new JButton("Consultar");
		consultarButton.setToolTipText("Consultar");
		consultarButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean validar = true;
				String idNave = cuadroTexto.getText().trim();
				Integer id = 0;
				
				try {
					id = Integer.parseInt(idNave);
					if (id <= 0) {

						validar = false;
						JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {
					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id introducido no debe contener letras ni caracteres especiales. Recuerda no dejar el campo vacio.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
				if(validar) Controller.getInstance().handle(new Context(Events.CONSULTAR_NAVE_OK_VIEW,id));
				dispose();
			}
		});
			
		
		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			
			Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
			dispose();

		});
		
		userPanel.add(consultarNave);
		userPanel.add(cuadroTexto);
		userPanel.add(consultarButton);
		userPanel.add(ko);
		
		this.id = new JLabel("ID NAVE: ");
		this.localizacion = new JLabel("LOCALIZACION: ");
		this.nombre = new JLabel("NOMBRE NAVE: ");
		this.capacidad = new JLabel("CAPACIDAD: ");

		idPanel.add(id);
		locationPanel.add(localizacion);
		namePanel.add(nombre);
		capacityPanel.add(capacidad);

		viewPanel.add(idPanel);
		viewPanel.add(locationPanel);
		viewPanel.add(namePanel);
		viewPanel.add(capacityPanel);
		viewPanel.add(activePanel);

		mainPanel.add(userPanel);
		mainPanel.add(viewPanel);

		this.setContentPane(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
	}

	@Override
	public void actualizar(Context context) {
		
		if(context.getData() != null) {
			TNave n = (TNave) context.getData();
			
			if(n.getActivo() == null) {
				JOptionPane.showMessageDialog(null, "La nave con dicho id no está activa en la base de datos", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			else if(n.getActivo()) {
				this.id.setText("ID NAVE: " + n.getId());
				this.localizacion.setText("LOCALIZACION: " + n.getLocalizacion());
				this.nombre.setText("NOMBRE NAVE: " + n.getNombre());
				this.capacidad.setText("CAPACIDAD: " + n.getCapacidad());
			}
			
		}
			
			
		
		
		
	}
}
