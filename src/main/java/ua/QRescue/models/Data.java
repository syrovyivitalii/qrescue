package ua.QRescue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "data")
public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "numresidents")
    private Integer numResidents;

    @Column(name = "residentswithdisabilities")
    private String residentsWithDisabilities;

    @Column(name = "emergencyexits")
    private String emergencyExits;

    @Column(name = "fireequipment")
    private String fireEquipment;

    @Column(name = "gassupply")
    private String gasSupply;


    @Column(name = "electricitysupply")
    private String electricitySupply;

    @Column(name = "watersupply")
    private String waterSupply;

    @Transient
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id")
    private Osbb osbb;
    public Data() {
    }

    public Data(int id, String address, Integer numResidents, String residentsWithDisabilities, String emergencyExits, String fireEquipment, String gasSupply, String electricitySupply, String waterSupply, Osbb osbb) {
        this.id = id;
        this.address = address;
        this.numResidents = numResidents;
        this.residentsWithDisabilities = residentsWithDisabilities;
        this.emergencyExits = emergencyExits;
        this.fireEquipment = fireEquipment;
        this.gasSupply = gasSupply;
        this.electricitySupply = electricitySupply;
        this.waterSupply = waterSupply;
        this.osbb = osbb;
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

    public Integer getNumResidents() {
        if (numResidents != null) {
            return numResidents.intValue();
        } else {
            return 0; // Or any default value you prefer
        }
    }

    public void setNumResidents(Integer numResidents) {
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

    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }
}
