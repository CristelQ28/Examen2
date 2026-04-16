package presentacion;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Sistema de Parqueo");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centrar

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Ingreso", new IngresoPanel());
        tabs.add("Activos", new ActivosPanel()); 
        tabs.add("Salida", new SalidaPanel());
        tabs.add("Historial", new HistorialPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
