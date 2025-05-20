package Presentacion.Habilidad.ConsultarHabilidad;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Habilidad.THabilidad;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel id;
	private JLabel nombre;

	public GUIConsultarHabilidad() {
		super("CONSULTAR HABILIDAD");
		this.initGUI();
	}

	private void initGUI() {

		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaUsuario = new JPanel();
		JPanel panelParaMostrar = new JPanel();
		panelParaMostrar.setLayout(new BoxLayout(panelParaMostrar, BoxLayout.Y_AXIS));

		JPanel panelParaMostrarID = new JPanel();
		panelParaMostrarID.setLayout(layout);
		JPanel panelParaMostrarNombre = new JPanel();
		panelParaMostrarNombre.setLayout(layout);
		JPanel panelParaMostrarActivo = new JPanel();
		panelParaMostrarActivo.setLayout(layout);

		JLabel consultarHabilidad = new JLabel("Introduzca el ID de la habilidad: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la habilidad que quieres consultar.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idHabilidad = cuadroTexto.getText().trim();
				int id = 0;
				try {
					id = Integer.parseInt(idHabilidad);
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
				if (validar) {
					Controller.getInstance().handle(new Context(Events.CONSULTAR_HABILIDAD_OK_VIEW, id));
				}
				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.HABILIDAD_CANCEL_VIEW, null));
			dispose();

		});

		panelParaUsuario.add(consultarHabilidad);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(ko);

		this.id = new JLabel("ID HABILIDAD: ");
		this.nombre = new JLabel("NOMBRE HABILIDAD: ");

		panelParaMostrarID.add(id);
		panelParaMostrarNombre.add(nombre);

		panelParaMostrar.add(panelParaMostrarID);
		panelParaMostrar.add(panelParaMostrarNombre);
		panelParaMostrar.add(panelParaMostrarActivo);

		panelPrincipal.add(panelParaUsuario);
		panelPrincipal.add(panelParaMostrar);

		this.setContentPane(panelPrincipal);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);

	}

	@Override
	public void actualizar(Context context) {
		
		if(context.getData() !=  null)
		{
			THabilidad t = (THabilidad) context.getData();

			t.getActivo();
			this.id.setText("ID HABILIDAD: " + t.getId());
			this.nombre.setText("NOMBRE HABILIDAD: " + t.getNombre());
			

		}
		

	}

}
