package Presentacion.Nave.AltaNave;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAltaNave extends JFrame implements Observer {

	JTextField texto1;
	TNave t_nave;

	public GUIAltaNave() {
		super("Alta Nave");
		initGUI();
	}

	public void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel questionPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		JPanel title = new JPanel();
		JLabel titlet = new JLabel("Dar de alta una nueva nave en la base de datos");
		title.add(titlet);
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel pregunta_nombre = new JPanel();
		JLabel noml = new JLabel("Nombre: ");
		JTextField nom = new JTextField(10);
		pregunta_nombre.add(noml);
		pregunta_nombre.add(nom);

		JPanel pregunta_localiz = new JPanel();
		JLabel localizl = new JLabel("Localización: ");
		JTextField localiz = new JTextField(10);
		pregunta_localiz.add(localizl);
		pregunta_localiz.add(localiz);

		JPanel pregunta_cap = new JPanel();
		JLabel capl = new JLabel("Capacidad: ");
		JTextField cap = new JTextField(10);
		pregunta_cap.add(capl);
		pregunta_cap.add(cap);

		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		a.setPreferredSize(new Dimension(50, 40));
		b.setPreferredSize(new Dimension(50, 50));
		questionPanel.add(a);
		questionPanel.add(pregunta_nombre);
		questionPanel.add(pregunta_localiz);
		questionPanel.add(pregunta_cap);
		questionPanel.add(b);

		mainPanel.add(questionPanel, BorderLayout.CENTER);

		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		        
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
        
	JButton cancel = new JButton("Calcelar");
		cancel.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_NAVE_KO_VIEW, t_nave));
			dispose();
		});

		JButton accept = new JButton("Aceptar");
		accept.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;
				
				t_nave = new TNave();
				t_nave.setActivo(true);
				
				if (cap.getText().isEmpty() || localiz.getText().isEmpty() || nom.getText().isEmpty()) {
					validar = false;
					JOptionPane.showMessageDialog(null, "Error: Los campos no pueden estar vacios", "Error",
							JOptionPane.ERROR_MESSAGE);
					Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
				} else {
					t_nave.setCapacidad(Integer.valueOf(cap.getText()));
					t_nave.setLocalizacion(localiz.getText());
					t_nave.setNombre(nom.getText());

					if (Integer.valueOf(cap.getText()) < 0) {
						validar = false;
						JOptionPane.showMessageDialog(mainPanel, "Capacidad no válida");
					} else {
						if(validar)	Controller.getInstance().handle(new Context(Events.ALTA_NAVE_OK_VIEW, t_nave));
					}
				}
				dispose();
			}
			
			
		});
		
		bottomPanel.add(accept);
		bottomPanel.add(cancel);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(500, 500));
		setLocation(500, 500);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(Context context) {
		
	}

}
