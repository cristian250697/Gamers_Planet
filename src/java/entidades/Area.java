package entidades;

import java.sql.Timestamp;

public class Area {

    private int idArea;
    private String nombre;
    private int idUsrAlta;
    private String idUsrAltaS;
    private Timestamp fechaAlta;
    private int idUsrMod;
    private String idUsrModS;
    private Timestamp fechaMod;

    public Area() {
    }

    public Area(int idArea, String nombre, int idUsrAlta, Timestamp fechaAlta, int idUsrMod, Timestamp fechaMod) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.idUsrAlta = idUsrAlta;
        this.fechaAlta = fechaAlta;
        this.idUsrMod = idUsrMod;
        this.fechaMod = fechaMod;
    }

    public Timestamp getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Timestamp fechaMod) {
        this.fechaMod = fechaMod;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsrAlta() {
        return idUsrAlta;
    }

    public void setIdUsrAlta(int idUsrAlta) {
        this.idUsrAlta = idUsrAlta;
    }

    public String getIdUsrAltaS() {
        return idUsrAltaS;
    }

    public void setIdUsrAltaS(String idUsrAltaS) {
        this.idUsrAltaS = idUsrAltaS;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getIdUsrMod() {
        return idUsrMod;
    }

    public void setIdUsrMod(int idUsrMod) {
        this.idUsrMod = idUsrMod;
    }

    public String getIdUsrModS() {
        return idUsrModS;
    }

    public void setIdUsrModS(String idUsrModS) {
        this.idUsrModS = idUsrModS;
    }


    
}
