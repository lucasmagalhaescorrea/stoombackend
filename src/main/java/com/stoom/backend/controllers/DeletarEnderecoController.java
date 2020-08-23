package com.stoom.backend.controllers;

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
@RequestMapping("/api/endereco/deletar")
@CrossOrigin("*")
public class DeletarEnderecoController {
    
    private EnderecoService enderecoService;

    @Autowired
    public DeletarEnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Response<String>> deletar(@RequestBody EnderecoDTO enderecoDto,
            BindingResult result) throws NoSuchAlgorithmException {

        Response<String> response = new Response<String>();

        Optional<Endereco> endereco;
        
        if(!(endereco = this.converterDtoParaEndereco(enderecoDto)).isPresent()){
            response.getErrors().add("Endereço não existente");
            return ResponseEntity.badRequest().body(response);
        }

        this.enderecoService.deletar(endereco.get());

        response.setData("Endereço excluído com sucesso");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{nome}")
    public String getEndereco(@PathVariable("zipcode") String zipcode) {
        return "Ola " + zipcode;
    }

    private Optional<Endereco> converterDtoParaEndereco(EnderecoDTO enderecoDto) {
        Optional<Endereco> endereco = enderecoService.findById(enderecoDto.getId());
        return endereco;
    }
    
}
