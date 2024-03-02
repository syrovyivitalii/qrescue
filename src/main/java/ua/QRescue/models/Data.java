package ua.QRescue.models;

import jakarta.persistence.*;

@Entity
@Table(name = "data")
@lombok.Data
public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number_residents")
    private int numberResidents;

    @Column(name = "immobility_residents")
    private int immobilityResidents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Osbb osbb;
    public Data() {
    }

    public Data(int numberResidents, int immobilityResidents) {
        this.numberResidents = numberResidents;
        this.immobilityResidents = immobilityResidents;
    }
}
