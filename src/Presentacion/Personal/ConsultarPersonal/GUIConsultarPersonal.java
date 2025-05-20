package Presentacion.Personal.ConsultarPersonal;

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

import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarPersonal extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel id;
	private JLabel NIF;
	private JLabel nombre;
	private JLabel apellido;
	private JLabel idNave;
	private JLabel cargo;

	public GUIConsultarPersonal() {
		super("CONSULTAR PERSONAL");
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
		JPanel panelParaMostrarApellidos = new JPanel();
		panelParaMostrarApellidos.setLayout(layout);
		JPanel panelParaMostrarNIE = new JPanel();
		panelParaMostrarNIE.setLayout(layout);
		JPanel panelParaMostrarCargo = new JPanel();
		panelParaMostrarCargo.setLayout(layout);

		JLabel consultarHabilidad = new JLabel("Introduzca el ID de la persona: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("Nombre de la persona que quieres consultar.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idPersonal = cuadroTexto.getText().trim();
				int id = 0;
				try {

					id = Integer.parseInt(idPersonal);
					if (id <= 0) {

						validar = false;
						JOptionPane.showMessageDialog(null, "El identificador de la persona debe ser mayor que 0.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {
					validar = false;
					JOptionPane.showMessageDialog(null,
							"El identificador de la persona debe ser un numero entero mayor que 0, sin letras.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {

					Controller.getInstance().handle(new Context(Events.CONSULTAR_PERSONAL_OK_VIEW, id));
				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.CONSULTAR_PERSONAL_KO_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(consultarHabilidad);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(ko);

		id = new JLabel("ID PERSONA: ");
		NIF = new JLabel("NIF: ");
		nombre = new JLabel("NOMBRE: ");
		apellido = new JLabel("APELLIDO: ");
		idNave = new JLabel("ID NAVE: ");
		cargo = new JLabel("CARGO EN LA EMPRESA: ");

		panelParaMostrarID.add(id);
		panelParaMostrarNombre.add(nombre);
		panelParaMostrarApellidos.add(apellido);
		panelParaMostrarNIE.add(NIF);
		panelParaMostrarCargo.add(cargo);

		panelParaMostrar.add(panelParaMostrarID);
		panelParaMostrar.add(panelParaMostrarNombre);
		panelParaMostrar.add(panelParaMostrarApellidos);
		panelParaMostrar.add(panelParaMostrarNIE);
		panelParaMostrar.add(panelParaMostrarCargo);

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

		TPersonal personal = (TPersonal) context.getData();
		if(personal!=null ) {

		if (personal.getActivo()) {
			this.id.setText("ID PERSONA: " + personal.getId());
			this.NIF.setText("NIF: " + personal.getNif());
			this.nombre.setText("NOMBRE: " + personal.getNombre());
			this.apellido.setText("APELLIDO: " + personal.getApellido());
			this.idNave.setText("ID NAVE: " + personal.getIdNave());

			if (personal instanceof TEmpleado) {

				TEmpleado empleado = (TEmpleado) personal;

				this.cargo.setText("CARGO: " + empleado.getCargo());
				this.cargo.setVisible(true);
			} else if (personal instanceof TJefe) {
				this.cargo.setVisible(false);
			}

			this.revalidate();
			this.repaint();

		}

		else {

			JOptionPane.showMessageDialog(null, "El usuario esta dado de baja en la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		}

	}

}
