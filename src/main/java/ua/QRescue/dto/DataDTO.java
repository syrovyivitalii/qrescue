package ua.QRescue.dto;

import jakarta.persistence.Column;

public class DataDTO {
    @Column(name = "number_residents")
    private int numberResidents;

    @Column(name = "immobility_residents")
    private int immobilityResidents;
}
