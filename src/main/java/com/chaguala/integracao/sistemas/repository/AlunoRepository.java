package com.chaguala.integracao.sistemas.repository;

import com.chaguala.integracao.sistemas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
