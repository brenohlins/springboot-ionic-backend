package com.brenolins.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenolins.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{


	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
