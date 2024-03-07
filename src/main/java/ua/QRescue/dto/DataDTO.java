package ua.QRescue.dto;

import lombok.*;


@ToString
@EqualsAndHashCode
public class DataDTO {
    private int id;
    private String address;

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
}
