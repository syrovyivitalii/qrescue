package ua.QRescue.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "osbb")
public class Osbb {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    @NotEmpty(message = "ОСББ повинно мати назву")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    @NotEmpty(message = "ОСББ повинно мати адресу")
    private String address;

    @OneToOne(mappedBy = "osbb")
    private HeadOsbb headOSBB;

    @OneToOne(mappedBy = "osbb")
    private ua.QRescue.models.Data data;

    public Osbb() {
    }
    public Osbb(int id, String login, String password, String address, HeadOsbb headOSBB, Data data) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
        this.headOSBB = headOSBB;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HeadOsbb getHeadOSBB() {
        return headOSBB;
    }

    public void setHeadOSBB(HeadOsbb headOSBB) {
        this.headOSBB = headOSBB;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
