package com.stoom.backend.services.impl;

import com.stoom.backend.entities.Endereco;
import com.stoom.backend.repositories.EnderecoRepository;
import com.stoom.backend.services.EnderecoService;
import com.stoom.backend.services.EnderecoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    
    @Autowired
    private EnderecoRepository repository;

    public EnderecoServiceImpl() {
    }

    @Override
    public Optional<List<Endereco>> findByZipcode(String zipcode) {
        return Optional.ofNullable(this.repository.findByZipcode(zipcode));
    }
    
    @Override
    public Optional<Endereco> findByZipcodeAndNumberAndId(String zipcode, String number, Long id) {
      return Optional.ofNullable(this.repository.findByZipcodeAndNumberAndId(zipcode, number, id));
    }

    @Override
    public Endereco persistir(Endereco endereco) {
        return this.repository.save(endereco);
    }

    @Override
    public void deletar(Endereco endereco) {
        this.repository.delete(endereco);
    }

    @Override
    public Optional<Endereco> findById(Long id) {
       return this.repository.findById(id);
    }

    @Override
    public Optional<Endereco> findByZipcodeAndNumber(String zipcode, String number) {
      return Optional.ofNullable(this.repository.findByZipcodeAndNumber(zipcode, number));
    }
    
}
