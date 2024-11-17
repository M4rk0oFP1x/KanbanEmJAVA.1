package org.example.controller;

import org.example.model.Tarefa;
import org.example.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTarefasPorStatus();
    }

    @PutMapping("/atualizar/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaService.atualizarTarefa(id, tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public String deletarTarefa(@PathVariable Long id) {
        return tarefaService.deletarTarefa(id);
    }

    @PutMapping ("/prioridade/{id}")
    public Tarefa MoverPrioridade(@PathVariable Long id) {
        return tarefaService.MoverPrioridade(id);
    }

    @PutMapping ("/status/{id}")
    public Tarefa MoverStatus(@PathVariable Long id) {
        return tarefaService.MoverStatus(id);
    }
}
