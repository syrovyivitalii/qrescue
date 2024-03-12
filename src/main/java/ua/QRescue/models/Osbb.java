package ua.QRescue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "osbb")
public class Osbb {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login",unique = true)
    @NotEmpty(message = "login must be entered")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "password must be entered")
    private String password;

    @OneToOne(mappedBy = "osbb")
    @JsonIgnore
    private HeadOsbb headOSBB;

//    @OneToOne(mappedBy = "osbb")
//    @JsonIgnore
//    private ua.QRescue.models.Data data;

    public Osbb() {
    }

    public Osbb(int id, String login, String password, HeadOsbb headOSBB, Data data) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.headOSBB = headOSBB;
//        this.data = data;
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

    public HeadOsbb getHeadOSBB() {
        return headOSBB;
    }

    public void setHeadOSBB(HeadOsbb headOSBB) {
        this.headOSBB = headOSBB;
    }

//    public Data getData() {
//        return data;
//    }

//    public void setData(Data data) {
//        this.data = data;
//    }
}
