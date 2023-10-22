package com.chaguala.integracao.sistemas.Controller;

import com.chaguala.integracao.sistemas.exception.ResourceNotFoundException;
import com.chaguala.integracao.sistemas.model.Aluno;
import com.chaguala.integracao.sistemas.repository.AlunoRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    // Adicionando um aluno
    @PostMapping("adicionar")
    public Aluno adicionaAluno(@RequestBody Aluno novoAluno){
        Aluno aluno = new Aluno();
        aluno.setNomeAluno(novoAluno.getNomeAluno());
        aluno.setEmail(novoAluno.getEmail());
        alunoRepository.save(aluno);
        return aluno;
    }

    // Listar todos
    @GetMapping("listar")
    public @ResponseBody Iterable<Aluno> getAllAluno(){
        return alunoRepository.findAll();
    }

    // Listar um determinado aluno
    @GetMapping("lista/{id}")
    public ResponseEntity<Aluno> getAlunoByID(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nao foi possivel encontrar o aluno com o id = " + id));

        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    // Atualizar um determinado aluno
    @PutMapping("edita/{id}")
    public String updateAluno( @RequestBody Aluno updateAluno, @PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNomeAluno(updateAluno.getNomeAluno());
                    aluno.setEmail(updateAluno.getEmail());
                    alunoRepository.save(aluno);
                    return "Os dados do aluno foram atualizados com sucesso!";
                }).orElseGet(() -> {
                    return "Nao existem um aluno com esse ID";
                });
    }

    // Eliminar um determinado aluno
    @DeleteMapping("delete/{id}")
    public String deleteAluno(@PathVariable("id")Long id) {
        alunoRepository.deleteById(id);
        return "Os dados do aluno foram eliminados com sucesso!";
    }
}
