package ua.QRescue.dto;

import lombok.*;


@ToString
@EqualsAndHashCode
public class DataDTO {
    private int id;
    private String address;
    private int numResidents;
    private String residentsWithDisabilities;
    private String emergencyExits;
    private String fireEquipment;
    private String gasSupply;
    private String electricitySupply;
    private String waterSupply;


    public DataDTO() {
    }
    //other form fields

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumResidents() {
        return numResidents;
    }

    public void setNumResidents(int numResidents) {
        this.numResidents = numResidents;
    }

    public String getResidentsWithDisabilities() {
        return residentsWithDisabilities;
    }

    public void setResidentsWithDisabilities(String residentsWithDisabilities) {
        this.residentsWithDisabilities = residentsWithDisabilities;
    }

    public String getEmergencyExits() {
        return emergencyExits;
    }

    public void setEmergencyExits(String emergencyExits) {
        this.emergencyExits = emergencyExits;
    }

    public String getFireEquipment() {
        return fireEquipment;
    }

    public void setFireEquipment(String fireEquipment) {
        this.fireEquipment = fireEquipment;
    }

    public String getGasSupply() {
        return gasSupply;
    }

    public void setGasSupply(String gasSupply) {
        this.gasSupply = gasSupply;
    }

    public String getElectricitySupply() {
        return electricitySupply;
    }

    public void setElectricitySupply(String electricitySupply) {
        this.electricitySupply = electricitySupply;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(String waterSupply) {
        this.waterSupply = waterSupply;
    }
}
