package com.sai.alpha1.instancias;

public class ferias {
    private int id;
    private String localidad;
    private String fecha;
    private String feria;
    private String urlimage;

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFeria() {
        return feria;
    }

    public void setFeria(String feria) {
        this.feria = feria;
    }
}
