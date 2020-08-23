package com.stoom.backend.dtos;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class EnderecoDTO {
    
    private Long id;
    private String streetName;
    private String number;
    private String complement;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private double latidade;
    private double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "Rua não pode ser vazio!")
    @Length(min = 0, max = 255, message = "Rua deve ter no máximo 255 caracteres")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @NotEmpty(message = "Número não pode ser vazio!")
    @Length(min = 0, max = 10, message = "Número deve ter no máximo 10 caracteres")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Length(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @NotEmpty(message = "Bairro não pode ser vazio!")
    @Length(min = 0, max = 100, message = "Bairro deve ter no máximo 100 caracteres")
    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    @NotEmpty(message = "Cidade não pode ser vazio!")
    @Length(min = 0, max = 50, message = "Cidade deve ter no máximo 50 caracteres")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotEmpty(message = "Estado não pode ser vazio!")
    @Length(min = 0, max = 50, message = "Estado deve ter no máximo 50 caracteres")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @NotEmpty(message = "País não pode ser vazio!")
    @Length(min = 0, max = 50, message = "País deve ter no máximo 50 caracteres")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotEmpty(message = "CEP nao pode ser vazio!")
    @Length(min = 0, max = 20, message = "CEP deve ter no maximo 20 caracteres")
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public double getLatidade() {
        return latidade;
    }

    public void setLatidade(double latidade) {
        this.latidade = latidade;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
}
