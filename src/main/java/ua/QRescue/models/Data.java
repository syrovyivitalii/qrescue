package ua.QRescue.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number_residents")
    private Integer numberResidents;

    @Column(name = "immobility_residents")
    private Integer immobilityResidents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Osbb osbb;
    public Data() {
    }

    public Data(int id, Integer numberResidents, Integer immobilityResidents, Osbb osbb) {
        this.id = id;
        this.numberResidents = numberResidents;
        this.immobilityResidents = immobilityResidents;
        this.osbb = osbb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumberResidents() {
        return numberResidents;
    }

    public void setNumberResidents(Integer numberResidents) {
        this.numberResidents = numberResidents;
    }

    public Integer getImmobilityResidents() {
        return immobilityResidents;
    }

    public void setImmobilityResidents(Integer immobilityResidents) {
        this.immobilityResidents = immobilityResidents;
    }

    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }
}
