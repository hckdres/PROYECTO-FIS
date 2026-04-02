package org.example.ax0006.Entity;

import java.sql.Time;
import java.util.Date;

public class horario {
    Date fecha;
    Time horaInc;
    Time horaFin;
    int idHorario;

    public Date getFecha() {
        return fecha;
    }

    public Time getHoraInc() {
        return horaInc;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public void setHoraInc(Time horaInc) {
        this.horaInc = horaInc;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public horario(Date fecha, Time horaInc, Time horaFin) {
        this.fecha = fecha;
        this.horaInc = horaInc;
        this.horaFin = horaFin;
    }

}
