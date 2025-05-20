package Presentacion.Alergeno_Cafeteria.ConsultarAlergeno;



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

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private JLabel id;
    private JLabel nombre;
    private JLabel riesgo;
    private JLabel fuente;

    public GUIConsultarAlergeno() {
        super("CONSULTAR ALÉRGENO");
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
        JPanel panelParaMostrarRiesgo = new JPanel();
        panelParaMostrarRiesgo.setLayout(layout);
        JPanel panelParaMostrarFuente = new JPanel();
        panelParaMostrarFuente.setLayout(layout);

        JLabel consultarAlergeno = new JLabel("Introduzca el ID del alérgeno: ");
        JTextField cuadroTexto = new JTextField(20);
        cuadroTexto.setEditable(true);
        cuadroTexto.setToolTipText("ID del alérgeno que quieres consultar.");

        JButton ok = new JButton("OK");
        ok.setToolTipText("Aceptar");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean validar = true;

                String idAlergeno = cuadroTexto.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(idAlergeno);
                    if (id <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El ID introducido debe ser mayor que 0.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException m) {
                    validar = false;
                    JOptionPane.showMessageDialog(null,
                            "El ID introducido no debe contener letras ni caracteres especiales. Recuerda no dejar el campo vacío.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (validar) {
                    Controller.getInstance().handle(new Context(Events.CONSULTAR_ALERGENO_OK_VIEW, id));
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

        panelParaUsuario.add(consultarAlergeno);
        panelParaUsuario.add(cuadroTexto);
        panelParaUsuario.add(ok);
        panelParaUsuario.add(ko);

        this.id = new JLabel("ID ALÉRGENO: ");
        this.nombre = new JLabel("NOMBRE ALÉRGENO: ");
        this.riesgo = new JLabel("RIESGO: ");
        this.fuente = new JLabel("FUENTE: ");

        panelParaMostrarID.add(id);
        panelParaMostrarNombre.add(nombre);
        panelParaMostrarRiesgo.add(riesgo);
        panelParaMostrarFuente.add(fuente);

        panelParaMostrar.add(panelParaMostrarID);
        panelParaMostrar.add(panelParaMostrarNombre);
        panelParaMostrar.add(panelParaMostrarRiesgo);
        panelParaMostrar.add(panelParaMostrarFuente);

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

        if (context.getData() != null ) {
            TAlergeno t = (TAlergeno) context.getData();
            if(t.isActivo()) {
            	this.id.setText("ID ALÉRGENO: " + t.getIdAlergeno());
                this.nombre.setText("NOMBRE ALÉRGENO: " + t.getNombre());
                this.riesgo.setText("RIESGO: " + t.getRiesgo());
                this.fuente.setText("FUENTE: " + t.getFuente());
            }
            

        } 
    }
}
