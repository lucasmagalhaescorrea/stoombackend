package com.stoom.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller/endereco")
public class EnderecoController {
    
    @GetMapping(value = "/{nome}")
    public String getNome(@PathVariable("nome") String nome){
        return "Ola " + nome;
    }
}
