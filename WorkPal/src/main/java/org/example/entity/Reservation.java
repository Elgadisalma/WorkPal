package org.example.entity;

public class Reservation {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private Long memberId;
    private int espaceId;

    public Reservation(Long id, String dateDebut, String dateFin, Long memberId, int espaceId) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.memberId = memberId;
        this.espaceId = espaceId;
    }

    public Reservation(String dateDebut, String dateFin, Long memberId, int espaceId) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.memberId = memberId;
        this.espaceId = espaceId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getEspaceId() {
        return espaceId;
    }
    public void setEspaceId(int espaceId) {
        this.espaceId = espaceId;
    }

}
