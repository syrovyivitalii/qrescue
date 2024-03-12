package ua.QRescue.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class HeadOsbbDataDTO {
    private String name;

    private String lastname;

    private String phone;

    private String email;

    private int id;
    private String address;
    private int numResidents;
    private String residentsWithDisabilities;
    private String emergencyExits;
    private String fireEquipment;
    private String gasSupply;
    private String electricitySupply;
    private String waterSupply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
