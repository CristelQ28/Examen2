
package presentacion;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("🚗 Sistema de Parqueo 🚗");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Ingreso", new IngresoPanel());
        tabs.addTab("Salida", new SalidaPanel());
        tabs.addTab("Activos", new ActivosPanel());
        tabs.addTab("Historial", new HistorialPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new Main().setVisible(true);
            }
        });
    }
    
    
}
