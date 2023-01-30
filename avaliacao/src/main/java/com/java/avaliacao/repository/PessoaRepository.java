package com.java.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.avaliacao.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
	
	

}
