package ua.QRescue.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "head_osbb")
@Data
public class HeadOsbb {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Ім'я не повинно бути порожнім")
    @Size(min = 2, max = 30, message = "Ім'я повинно містити більше 2 та менше 30 символів")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "Прізвище не повинно бути порожнім")
    @Size(min = 2, max = 30, message = "Прізвище повинно містити більше 2 та менше 30 символів")
    private String lastname;

    @Column(name = "phone")
   // @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    private String phone;

    @Column(name = "email")
    @Email(message = "Пошту введено не коректно")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_osbb", referencedColumnName = "id")
    private Osbb osbb;

    public HeadOsbb() {
    }

    public HeadOsbb(String name, String lastname, String phone, String email) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }
}
