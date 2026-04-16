
package presentacion;

import logica.ParqueoService;
import javax.swing.*;
import java.awt.*;

public class SalidaPanel extends JPanel {
    private JTextField txtPlaca;
    private JLabel lblMensaje;
    private ParqueoService service;

    public SalidaPanel() {
        service = new ParqueoService();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Placa:"), gbc);

        gbc.gridx = 1;
        txtPlaca = new JTextField(15);
        add(txtPlaca, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btn = new JButton("Registrar Salida");
        btn.addActionListener(e -> registrarSalida());
        add(btn, gbc);

        gbc.gridy = 2;
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.BLUE);
        add(lblMensaje, gbc);
    }

    private void registrarSalida() {
        try {
            double monto = service.registrarSalida(txtPlaca.getText());
            lblMensaje.setText("✅ Salida exitosa. Monto: $" + monto);
            txtPlaca.setText("");
        } catch (Exception e) {
            lblMensaje.setText("❌ " + e.getMessage());
        }
    }
}
