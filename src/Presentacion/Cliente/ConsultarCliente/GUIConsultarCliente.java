package Presentacion.Cliente.ConsultarCliente;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TIndividual;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarCliente extends JFrame implements Observer{
private static final long serialVersionUID = 1L;

	JLabel id;
	JLabel usuario;
	JLabel correo;
	JLabel atribDebil;
	Map<Integer,TCliente> clientes;
	
	public GUIConsultarCliente(){
		super("CONSULTAR CLIENTE");
		clientes = new HashMap<>();
		this.initGUI();
	}
	
	private void initGUI() { //No se muestra contrasenya por que no tendria logica
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaUsuario = new JPanel();
		JPanel panelParaMostrar = new JPanel();
		panelParaMostrar.setLayout(new BoxLayout(panelParaMostrar, BoxLayout.Y_AXIS));

		JPanel panelParaMostrarID = new JPanel();
		panelParaMostrarID.setLayout(layout);
		JPanel panelParaMostrarUsuario = new JPanel();
		panelParaMostrarUsuario.setLayout(layout);
		JPanel panelParaMostrarCorreo = new JPanel();
		panelParaMostrarCorreo.setLayout(layout);
		JPanel panelParaMostrarNomEmpresa = new JPanel();
		panelParaMostrarNomEmpresa.setLayout(layout);

		JLabel consultarCliente = new JLabel("Introduzca el ID del cliente: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID del cliente que quieres consultar.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener((e)-> {

			boolean validar = true;

			String idCliente = cuadroTexto.getText().trim();
			int id = 0;
				try {
					id = Integer.parseInt(idCliente);
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
					Controller.getInstance().handle(new Context(Events.CONSULTAR_CLIENTE_OK_VIEW, id));
				}
				else
					Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
				dispose();

		});
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)->{
				Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
				dispose();
		});
		
		
		panelParaUsuario.add(consultarCliente);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
		
		this.id = new JLabel("ID CLIENTE: ");
		this.usuario = new JLabel("USUARIO CLIENTE: ");
		this.correo = new JLabel ("CORREO CLIENTE: ");
		this.atribDebil = new JLabel("");
		
		panelParaMostrarID.add(id);
		panelParaMostrarUsuario.add(usuario);
		panelParaMostrarCorreo.add(correo);
		panelParaMostrarNomEmpresa.add(atribDebil);

		panelParaMostrar.add(panelParaMostrarID);
		panelParaMostrar.add(panelParaMostrarUsuario);
		panelParaMostrar.add(panelParaMostrarCorreo);
		panelParaMostrar.add(panelParaMostrarNomEmpresa);

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
		TCliente _cliente = (TCliente) context.getData();
		if(_cliente != null) {
			if(_cliente.getActivo()) {
				this.id.setText("ID CLIENTE: " + _cliente.getId());
				this.usuario.setText("USUARIO CLIENTE: " + _cliente.getUsuario());
				this.correo.setText("CORREO CLIENTE: " + _cliente.getCorreo());
				if (_cliente.getTipo()) {
					TEmpresa empre = (TEmpresa) _cliente;
					this.atribDebil.setText("NOMBRE EMPRESA: " + empre.getNombre());
				} else {
					TIndividual indiv = (TIndividual) _cliente;
					this.atribDebil.setText("CODIGO POSTAL: " + indiv.getCodigoPostalStr());
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "El cliente con el id " + _cliente.getId() + " esta dado de baja.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		this.pack();
	}
}
