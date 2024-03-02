package ua.QRescue.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HeadOsbbDTO {
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

}
