package org.example.service;

import org.example.model.Prioridade;
import org.example.model.Status;
import org.example.model.Tarefa;
import org.example.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setStatus(Status.A_FAZER);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasPorStatus() {
        return tarefaRepository.findAll();
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = tarefaRepository.findById(id).orElse(null);

        if (tarefaExistente != null) {
            if (tarefaAtualizada.getTitulo() != null) {
                tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            }
            if (tarefaAtualizada.getDescricao() != null) {
                tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            }
            if (tarefaAtualizada.getPrioridade() != null) {
                tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            }
            if (tarefaAtualizada.getDateLimite() != null) {
                tarefaExistente.setDateLimite(tarefaAtualizada.getDateLimite());
            }
            if (tarefaAtualizada.getStatus() != null) {
                tarefaExistente.setStatus(tarefaAtualizada.getStatus());
            }
            return tarefaRepository.save(tarefaExistente);
        }
        return null;
    }

    public String deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);

        return "Tarefa deletada com sucesso";
    }

    public Tarefa MoverPrioridade(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        if (tarefa != null) {
            switch (tarefa.getPrioridade()){
                case BAIXA:
                    tarefa.setPrioridade(Prioridade.MÉDIA);
                    break;
                case MÉDIA:
                    tarefa.setPrioridade(Prioridade.ALTA);
                    break;
                case ALTA:
                    tarefa.setPrioridade(Prioridade.BAIXA);
                    break;
            }
            return tarefaRepository.save(tarefa);
        } else {
            return null;
        }
    }

    public Tarefa MoverStatus(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);

        if (tarefa != null) {
            switch (tarefa.getStatus()){
                case A_FAZER:
                    tarefa.setStatus(Status.EM_PROGRESSO);
                    break;
                case EM_PROGRESSO:
                    tarefa.setStatus(Status.CONCLUIDO);
                    break;
                case CONCLUIDO:
                    tarefa.setStatus(Status.A_FAZER);
                    break;
            }
            return tarefaRepository.save(tarefa);
        } else {
            return null;
        }
    }
}
