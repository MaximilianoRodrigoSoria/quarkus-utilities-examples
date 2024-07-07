package ar.com.laboratory.domain.model;


import lombok.Data;

@Data
public class TelefonoResponse {
    private int id;
    private String code;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String number;
    private String area;
    private String type;

}