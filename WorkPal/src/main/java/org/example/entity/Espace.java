package org.example.entity;

public class Espace {

    private Long id;
    private String name;
    private EspaceType type;
    private boolean disponibilite;
    private String taille;

    public Espace(Long id, String name, EspaceType type, boolean disponibilite, String taille) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.disponibilite = disponibilite;
        this.taille = taille;
    }

    public Espace(String name, EspaceType type, boolean disponibilite, String taille) {
        this.name = name;
        this.type = type;
        this.disponibilite = disponibilite;
        this.taille = taille;
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

    public EspaceType getType() {
        return type;
    }
    public void setType(EspaceType type) {
        this.type = type;
    }

    public boolean isDisponible() {
        return disponibilite;
    }
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getTaille() {
        return taille;
    }
    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Espace{" +
                "id=" + id +
                ", name= " + name  +
                ", type= " + type +
                ", disponibilite= " + disponibilite +
                ", taille= " + taille +
                '}';
    }
}
