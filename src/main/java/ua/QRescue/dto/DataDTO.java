package ua.QRescue.dto;

import lombok.*;


@ToString
@EqualsAndHashCode
public class DataDTO {
    private int numberResidents;

    private int immobilityResidents;

    public int getNumberResidents() {
        return numberResidents;
    }

    public void setNumberResidents(int numberResidents) {
        this.numberResidents = numberResidents;
    }

    public int getImmobilityResidents() {
        return immobilityResidents;
    }

    public void setImmobilityResidents(int immobilityResidents) {
        this.immobilityResidents = immobilityResidents;
    }
}
