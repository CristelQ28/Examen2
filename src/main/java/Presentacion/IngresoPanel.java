
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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Placa:"), gbc);

        gbc.gridx = 1;
        txtPlaca = new JTextField(15);
        add(txtPlaca, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        cbTipo = new JComboBox<>(new String[]{"🚗 Carro", "🏍️ Moto"});
        add(cbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton btn = new JButton("Registrar Ingreso");
        btn.addActionListener(e -> registrar());
        add(btn, gbc);

        gbc.gridy = 3;
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.BLUE);
        add(lblMensaje, gbc);
    }

    private void registrar() {
        try {
            String tipo = cbTipo.getSelectedItem().toString().replace("🚗 ", "").replace("🏍️ ", "");
            service.registrarIngreso(txtPlaca.getText(), tipo);
            lblMensaje.setText("✅ Ingreso exitoso");
            txtPlaca.setText("");
        } catch (Exception e) {
            lblMensaje.setText("❌ " + e.getMessage());
        }
    }
}