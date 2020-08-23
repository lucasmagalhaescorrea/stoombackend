package com.stoom.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.stoom.backend.dtos.EnderecoDTO;
import com.stoom.backend.entities.Endereco;
import com.stoom.backend.response.Response;
import com.stoom.backend.services.EnderecoService;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco/cadastrar")
@CrossOrigin("*")
public class CadastrarEnderecoController {

    private EnderecoService enderecoService;

    private static final String TOKEN_GOOGLE_API = "AIzaSyA042BXS3QIeuAoEzpoOowFqwOjnP4dCjo";

    @Autowired
    public CadastrarEnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Response<EnderecoDTO>> cadastrar(@Valid @RequestBody EnderecoDTO enderecoDto,
            BindingResult result) throws NoSuchAlgorithmException {

        Response<EnderecoDTO> response = new Response<EnderecoDTO>();
        
        Endereco endereco = this.converterDtoParaEndereco(enderecoDto);

        this.tratarLatitudeLongitude(endereco, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.enderecoService.persistir(endereco);

        response.setData(this.converterEnderecoDto(endereco));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{nome}")
    public String getEndereco(@PathVariable("zipcode") String zipcode) {
        return "Ola " + zipcode;
    }

    private Endereco converterDtoParaEndereco(EnderecoDTO enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setCity(enderecoDto.getCity());
        endereco.setComplement(enderecoDto.getComplement());
        endereco.setCountry(enderecoDto.getCountry());
        endereco.setId(enderecoDto.getId());
        endereco.setLatidade(enderecoDto.getLatidade());
        endereco.setLongitude(enderecoDto.getLongitude());
        endereco.setNeighbourhood(enderecoDto.getNeighbourhood());
        endereco.setNumber(enderecoDto.getNumber());
        endereco.setState(enderecoDto.getState());
        endereco.setStreetName(enderecoDto.getStreetName());
        endereco.setZipcode(enderecoDto.getZipcode());

        return endereco;
    }

    private EnderecoDTO converterEnderecoDto(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCity(endereco.getCity());
        dto.setComplement(endereco.getComplement());
        dto.setCountry(endereco.getCountry());
        dto.setId(endereco.getId());
        dto.setLatidade(endereco.getLatidade());
        dto.setLongitude(endereco.getLongitude());
        dto.setNeighbourhood(endereco.getNeighbourhood());
        dto.setNumber(endereco.getNumber());
        dto.setState(endereco.getState());
        dto.setStreetName(endereco.getStreetName());
        dto.setZipcode(endereco.getZipcode());

        return dto;
    }

    private String getEnderecoMensagem(Endereco endereco) {
        StringBuilder adress = new StringBuilder();
        adress.append(endereco.getNumber());
        adress.append(" " + endereco.getStreetName());
        adress.append(" " + endereco.getNeighbourhood());
        adress.append(" " + endereco.getCity());
        adress.append(", " + endereco.getState());
        adress.append(" " + endereco.getZipcode());

        return adress.toString();
    }

    private void tratarLatitudeLongitude(Endereco endereco, BindingResult result) {
        try {
            if (endereco.getLatidade() == 0
                    || endereco.getLongitude() == 0) {

                GeoApiContext context = new GeoApiContext.Builder()
                        .apiKey(TOKEN_GOOGLE_API)
                        .build();
                GeocodingResult[] geoResults = GeocodingApi.geocode(context,
                        getEnderecoMensagem(endereco)
                ).await();
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                
                if(geoResults.length == 0){
                    result.addError(new ObjectError("Endereço", "Endereço não localizado!"));
                    return;
                }
                
                endereco.setLatidade(geoResults[0].geometry.location.lat);
                endereco.setLongitude(geoResults[0].geometry.location.lng);

            }
        } catch (Exception ex) {
            result.addError(new ObjectError("Endereço", "Erro . Geocoding API : " + ex.getMessage()));
        }
    }

}
