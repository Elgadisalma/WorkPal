package org.example.entity;

public class Evenement {
    private Long id;
    private String name;
    private EvenementType type;
    private String dateDebut;
    private String dateFin;

    public Evenement(Long id, String name, EvenementType type, String dateDebut, String dateFin) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Evenement(String name, EvenementType type, String dateDebut, String dateFin) {
        this.name = name;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public EvenementType getType() {
        return type;
    }
    public void setType(EvenementType type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }


}
