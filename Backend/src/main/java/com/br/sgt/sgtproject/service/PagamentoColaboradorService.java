package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.repository.PagamentoColaboradorRepository;
import com.br.sgt.sgtproject.service.dto.EmailPagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.MetaDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.PagantesDTO;
import com.br.sgt.sgtproject.service.mapper.PagamentoColaboradorListMapper;
import com.br.sgt.sgtproject.service.mapper.PagamentoColaboradorMapper;
import com.br.sgt.sgtproject.service.util.MensagemPagamentoColaboradorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoColaboradorService {

    private final PagamentoColaboradorRepository repository;
    private final PagamentoColaboradorListMapper listMapper;
    private final PagamentoColaboradorMapper mapper;
    private final EnviarEmailService enviarEmailService;

    public List<PagamentoColaboradorListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAllByOrderByColaboradorId());
    }

    private PagamentoColaborador buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemPagamentoColaboradorUtil.PAGAMENTO_COLABORADOR_NAO_ENCONTRADO));
    }

    public PagamentoColaboradorDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    public PagamentoColaboradorDTO salvar(PagamentoColaboradorDTO dto){
        if(dto.getRetiradoLugar() == null){
            dto.setRetiradoLugar(false);
        }
        PagamentoColaborador pagamentoColaborador = repository.save(mapper.toEntity(dto));
        EmailPagamentoColaboradorDTO emailPagamentoColaboradorDTO = repository.buscarPagamentoColaborador(pagamentoColaborador.getId());
        enviarEmailService.pagamento(emailPagamentoColaboradorDTO);
        return mapper.toDto(pagamentoColaborador);
    }

    public void deletar (Integer id){
        repository.deleteById(id);
    }

    public List<MetaDTO> metas(Integer id) {
        return repository.buscarPagamentosColaborador(id);
    }

    public List<PagantesDTO> quemPagou(Integer id) {
        return repository.pagantes(id);
    }

    public List<String> quemNaoPagou(Integer id) {
        return repository.quemNaoPagou(id);
    }

}
