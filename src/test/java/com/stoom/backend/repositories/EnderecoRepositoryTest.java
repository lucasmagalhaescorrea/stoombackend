package com.stoom.backend.repositories;

import com.stoom.backend.entities.Endereco;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
//import org.junit.runner.RunWith;

@SpringBootTest
@ActiveProfiles("test")
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository empresaRepository;

    private static final String ZIPCODE = "13484465";


    @Test
    public void testfindByZipcode() {
        Endereco endereco = new Endereco();
        endereco.setStreetName("Endereco de exemplo");
        endereco.setCity("Endereco de exemplo");
        endereco.setCountry("Endereco de exemplo");
        endereco.setNeighbourhood("Endereco de exemplo");
        endereco.setNumber("Endereco de exemplo");
        endereco.setState("Endereco de exemplo");
        endereco.setZipcode(ZIPCODE);
        endereco = this.empresaRepository.save(endereco);
        
        System.out.println(endereco.getId());
        endereco.setState("SP");
        endereco = this.empresaRepository.save(endereco);
        
        System.out.println(endereco.getId());

        Endereco enderecoBusca = this.empresaRepository.findByZipcode(ZIPCODE).get(0);

        assertEquals(ZIPCODE, enderecoBusca.getZipcode());
        
        this.empresaRepository.deleteAll();
    }

}
