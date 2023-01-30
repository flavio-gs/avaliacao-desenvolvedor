package com.java.avaliacao.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.avaliacao.model.Pessoa;
import com.java.avaliacao.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoaEndereco")
public class PessoaController {
	
	
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@GetMapping
	public ModelAndView pesquisar(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoaEndereco/PesquisaPessoa");
		
			
		return mv;
	}
	
	

	 @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
	    {
	        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
	        if(pessoa.isPresent())
	            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
	    public Pessoa Post(@Validated @RequestBody Pessoa pessoa)
	    {
	        return pessoaRepository.save(pessoa);
	    }

	    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
	    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Pessoa newPessoa)
	    {
	        Optional<Pessoa> oldPessoa = pessoaRepository.findById(id);
	        if(oldPessoa.isPresent()){
	            Pessoa pessoa = oldPessoa.get();
	            pessoa.setNome(newPessoa.getNome());
	            pessoaRepository.save(pessoa);
	            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	    {
	        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
	        if(pessoa.isPresent()){
	            pessoaRepository.delete(pessoa.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	
}
