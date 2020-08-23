package com.stoom.backend.services;

import com.stoom.backend.entities.Endereco;
import java.util.List;
import java.util.Optional;

public interface EnderecoService {
    
    public Optional<List<Endereco>> findByZipcode(String zipcode);
    
    public Optional<Endereco> findByZipcodeAndNumberAndId(String zipcode, String number, Long id);
    
    public Optional<Endereco> findByZipcodeAndNumber(String zipcode, String number);
    
    public Optional<Endereco> findById(Long id);
    
    public Endereco persistir(Endereco endereco);
    
    public void deletar(Endereco endereco);
    
}
