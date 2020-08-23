package com.stoom.backend.repositories;

import com.stoom.backend.entities.Endereco;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository empresaRepository;

    private static final String ZIPCODE = "13484-465";
    private static final String ZIPCODE_UPDATE = "11111-111";

    @Test
    public void testReadByZipcode() {
        Endereco endereco = getEndereco();

        Endereco enderecoBusca = this.empresaRepository.findByZipcode(ZIPCODE).get(0);

        assertEquals(ZIPCODE, enderecoBusca.getZipcode());
        
        this.empresaRepository.deleteAll();
    }
    
    @Test
    public void testReadById() {
        Endereco endereco = getEndereco();

        Optional<Endereco> enderecoBusca = this.empresaRepository.findById(endereco.getId());

        assertEquals(endereco.getId(), enderecoBusca.get().getId());
        assertEquals(this.empresaRepository.count(), 1);
        
        this.empresaRepository.deleteAll();
    }
    
    @Test
    public void testCreateUpdate() {
        Endereco endereco = getEndereco();
        
        Long id = endereco.getId();

        Endereco enderecoBusca = this.empresaRepository.findById(id).get();
        
        assertEquals(endereco.getId(), enderecoBusca.getId());
        assertEquals(ZIPCODE, enderecoBusca.getZipcode());
        assertEquals(this.empresaRepository.count(), 1);
        
        endereco.setZipcode(ZIPCODE_UPDATE);
        
        endereco = this.empresaRepository.save(endereco);
        
        assertEquals(endereco.getId(), enderecoBusca.getId());
        assertNotEquals(endereco.getZipcode(), enderecoBusca.getZipcode());
        assertEquals(endereco.getZipcode(), ZIPCODE_UPDATE);
        assertEquals(this.empresaRepository.count(), 1);
        
        this.empresaRepository.deleteAll();
    }
    
    @Test
    public void testDelete() {
        Endereco endereco = getEndereco();
        
        assertEquals(this.empresaRepository.count(), 1);
        
        this.empresaRepository.delete(endereco);
        
        assertEquals(this.empresaRepository.count(), 0);
    }    
    
    private Endereco getEndereco(){
        Endereco endereco = new Endereco();
        endereco.setStreetName("Endereco de exemplo");
        endereco.setCity("Endereco de exemplo");
        endereco.setCountry("Endereco de exemplo");
        endereco.setNeighbourhood("Endereco de exemplo");
        endereco.setNumber("Endereco de exemplo");
        endereco.setState("Endereco de exemplo");
        endereco.setZipcode(ZIPCODE);
        endereco = this.empresaRepository.save(endereco);
        
        return endereco;
    }

}
