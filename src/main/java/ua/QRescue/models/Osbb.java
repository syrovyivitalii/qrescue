package ua.QRescue.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "osbb")
@Data
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

    public Osbb(String login, String password, String address) {
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Osbb(String login, String password, String address, HeadOsbb headOSBB, ua.QRescue.models.Data data) {
        this.login = login;
        this.password = password;
        this.address = address;
        this.headOSBB = headOSBB;
        this.data = data;
    }
}
