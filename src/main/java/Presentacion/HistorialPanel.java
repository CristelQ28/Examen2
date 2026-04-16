
package presentacion;

import logica.ParqueoService;
import entidades.Registro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HistorialPanel extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private ParqueoService service;

    public HistorialPanel() {
        service = new ParqueoService();
        setLayout(new BorderLayout());
        modelo = new DefaultTableModel(new String[]{"Placa", "Tipo", "Entrada", "Salida", "Monto"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarHistorial());
        add(btnActualizar, BorderLayout.SOUTH);
        cargarHistorial();
    }

    private void cargarHistorial() {
        modelo.setRowCount(0);
        List<Registro> historial = service.getHistorial().stream()
            .filter(r -> !r.estaActivo())
            .collect(Collectors.toList());
        for (Registro r : historial) {
            modelo.addRow(new Object[]{
                r.getVehiculo().getPlaca(),
                r.getVehiculo().getTipo(),
                r.getHoraEntrada(),
                r.getHoraSalida(),
                "$" + r.getMonto()
            });
        }
    }
}