
package accesodatos;

import entidades.Registro;
import entidades.Vehiculo;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArchivoRegistroDAO {
    private final String archivo = "registros.txt";

    public void guardar(List<Registro> lista) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        for (Registro r : lista) {
            bw.write(
                r.getVehiculo().getPlaca() + "," +
                r.getVehiculo().getTipo() + "," +
                r.getHoraEntrada() + "," +
                (r.getHoraSalida() != null ? r.getHoraSalida() : "null") + "," +
                r.getMonto()
            );
            bw.newLine();
        }
        bw.close();
    }

    public List<Registro> cargar() throws IOException {
        List<Registro> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;

        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(",");
            Vehiculo v = new Vehiculo(p[0], p[1]);
            Registro r = new Registro(v, LocalDateTime.parse(p[2]));
            if (!p[3].equals("null")) {
                r.registrarSalida(LocalDateTime.parse(p[3]), Double.parseDouble(p[4]));
            }
            lista.add(r);
        }
        br.close();
        return lista;
    }
}