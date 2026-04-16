package logica;

import accesodatos.ArchivoRegistroDAO;
import entidades.Registro;
import entidades.Vehiculo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class ParqueoService {

    private List<Registro> registros;
    private ArchivoRegistroDAO dao;

    private final double TARIFA = 500;

    public ParqueoService() {
        
        dao = new ArchivoRegistroDAO();
        try {
            registros = dao.cargar();
        } catch (Exception e) {
            registros = new ArrayList<>();
        }
    }

    //INGRESO
    public void registrarIngreso(String placa, String tipo) throws Exception {

        if (placa == null || placa.isEmpty())
            throw new Exception("Placa obligatoria");

        if (tipo == null || tipo.isEmpty())
            throw new Exception("Tipo obligatorio");

        for (Registro r : registros) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa) && r.estaActivo()) {
                throw new Exception("Vehículo ya está dentro");
            }
        }

        Vehiculo v = new Vehiculo(placa, tipo);
        Registro r = new Registro(v, LocalDateTime.now());

        registros.add(r);
        guardar();
    }

    //SALIDA
    public double registrarSalida(String placa) throws Exception {
        for (Registro r : registros) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa) && r.estaActivo()) {

                LocalDateTime salida = LocalDateTime.now();

                long horas = Duration.between(r.getHoraEntrada(), salida).toHours();
                if (horas == 0) horas = 1;

                double monto = horas * TARIFA;

                r.registrarSalida(salida, monto);
                guardar();

                return monto;
            }
        }

        throw new Exception("Vehículo no encontrado");
    }

    public List<Registro> getActivos() {
        List<Registro> activos = new ArrayList<>();
        for (Registro r : registros) {
            if (r.estaActivo()) activos.add(r);
        }
        return activos;
    }

    public List<Registro> getHistorial() {
        return registros;
    }

    private void guardar() throws Exception {
        dao.guardar(registros);
        
        
    }
    
    
}


