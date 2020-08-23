package com.stoom.backend.controllers;

import com.stoom.backend.dtos.EnderecoDTO;
import com.stoom.backend.entities.Endereco;
import com.stoom.backend.response.Response;
import com.stoom.backend.services.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco/buscar")
@CrossOrigin("*")
public class BuscarEnderecoController {

    private EnderecoService enderecoService;

    @Autowired
    public BuscarEnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Response<EnderecoDTO>> buscarPorId(
            @PathVariable("id") Long id) {

        Response<EnderecoDTO> response = new Response<EnderecoDTO>();

        Optional<Endereco> endereco;
        
        if(!(endereco = this.enderecoService.findById(id)).isPresent()){
            response.getErrors().add("Nenhum endereço encontrado!");
            return ResponseEntity.badRequest().body(response);
        }
        
        EnderecoDTO enderecoDTO = this.converterEnderecoParaDTO(endereco.get());

        response.setData(enderecoDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/cep/{cep}")
    public ResponseEntity<Response<List<EnderecoDTO>>> buscarPorCep(
            @PathVariable("cep") String cep) {

        Response<List<EnderecoDTO>> response = new Response<List<EnderecoDTO>>();

        Optional<List<Endereco>> endereco;
        
        if(!(endereco = this.enderecoService.findByZipcode(cep)).isPresent()){
            response.getErrors().add("Nenhum endereço encontrado!");
            return ResponseEntity.badRequest().body(response);
        }
        
       List<EnderecoDTO> vEnderecoDTO = new ArrayList<>();
       
       for(Endereco oEndereco : endereco.get()){
           vEnderecoDTO.add(this.converterEnderecoParaDTO(oEndereco));
        }
       
        response.setData(vEnderecoDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/cep/numero/{cep}/{numero}")
    public ResponseEntity<Response<EnderecoDTO>> buscarPorCepENumero(
            @PathVariable("cep") String cep, @PathVariable("numero") String numero) {

        Response<EnderecoDTO> response = new Response<EnderecoDTO>();

        Optional<Endereco> endereco;
        
        if(!(endereco = this.enderecoService.findByZipcodeAndNumber(cep, numero)).isPresent()){
            response.getErrors().add("Nenhum endereço encontrado!");
            return ResponseEntity.badRequest().body(response);
        }
        
        response.setData(this.converterEnderecoParaDTO(endereco.get()));
        return ResponseEntity.ok(response);
    }

    private EnderecoDTO converterEnderecoParaDTO(Endereco endereco) {
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

}
