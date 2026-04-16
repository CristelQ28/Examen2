package presentacion;

import logica.ParqueoService;

import javax.swing.*;
import java.awt.*;

public class IngresoPanel extends JPanel {

    private JTextField txtPlaca;
    private JComboBox<String> cbTipo;
    private JLabel lblMensaje;
    private ParqueoService service;

    public IngresoPanel() {
        
        service = new ParqueoService();

        setLayout(new GridLayout(4,2));

        txtPlaca = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});
        lblMensaje = new JLabel();

        JButton btn = new JButton("Registrar");

        btn.addActionListener(e -> registrar());

        add(new JLabel("Placa:"));
        add(txtPlaca);
        add(new JLabel("Tipo:"));
        add(cbTipo);
        add(btn);
        add(lblMensaje);
    }

    private void registrar() {
        try {
            service.registrarIngreso(
                txtPlaca.getText(),
                cbTipo.getSelectedItem().toString()
            );
            lblMensaje.setText("Ingreso exitoso");
        } catch (Exception e) {
            lblMensaje.setText(e.getMessage());
        }
    }
}

