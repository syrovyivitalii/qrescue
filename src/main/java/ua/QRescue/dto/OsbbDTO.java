package ua.QRescue.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OsbbDTO {
    @Column(name = "login")
    @NotEmpty(message = "ОСББ повинно мати назву")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    @NotEmpty(message = "ОСББ повинно мати адресу")
    private String address;

}
