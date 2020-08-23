package com.stoom.backend.repositories;

import com.stoom.backend.entities.Endereco;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NamedQueries({
		@NamedQuery(name = "EnderecoRepository.findByZipcode", 
				query = "SELECT e FROM Endereco e WHERE e.zipcode = ':zipcode'"),
                @NamedQuery(name = "EnderecoRepository.findByZipcodeAndNumberAndId", 
				query = "SELECT e FROM Endereco e WHERE e.zipcode = ':zipcode' AND e.number = ':number' AND e.id <> :id" ),
                @NamedQuery(name = "EnderecoRepository.findByZipcodeAndNumber", 
				query = "SELECT e FROM Endereco e WHERE e.zipcode = ':zipcode' AND e.number = ':number'")})
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    @Transactional(readOnly = true)
    public List<Endereco> findByZipcode(@Param("zipcode")String zipcode);
    
    public Endereco findByZipcodeAndNumberAndId(@Param("zipcode") String zipcode,
            @Param("number") String number, @Param("id") long id);
    
    public Endereco findByZipcodeAndNumber(@Param("zipcode") String zipcode,
            @Param("number") String number);
    
}
