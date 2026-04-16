package entidades;

import java.time.LocalDateTime;

public class Registro {
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double monto;

    public Registro(Vehiculo vehiculo, LocalDateTime entrada) {
        this.vehiculo = vehiculo;
        this.horaEntrada = entrada;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public double getMonto() {
        return monto;
    }

    public void registrarSalida(LocalDateTime salida, double monto) {
        this.horaSalida = salida;
        this.monto = monto;
    }

    public boolean estaActivo() {
        return horaSalida == null;
    }
}