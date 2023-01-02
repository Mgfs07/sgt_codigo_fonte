package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.repository.PagamentoColaboradorRepository;
import com.br.sgt.sgtproject.service.ColaboradorService;
import com.br.sgt.sgtproject.service.PagamentoColaboradorService;
import com.br.sgt.sgtproject.service.dto.MetaDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
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
public class PagamentoColaboradorServiceImpl implements PagamentoColaboradorService {

    private final PagamentoColaboradorRepository repository;
    private final PagamentoColaboradorListMapper listMapper;
    private final PagamentoColaboradorMapper mapper;
    private final EnviarEmailService enviarEmailService;
    private final ColaboradorService colaboradorService;

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
        PagamentoColaborador pagamentoColaborador = mapper.toEntity(dto);
//        PagamentoColaboradorListDTO listDTO = listMapper.toDto(pagamentoColaborador);
        PagamentoColaboradorDTO pagamentoColaboradorDTO = mapper.toDto(repository.save(pagamentoColaborador));
//        ColaboradorDTO colaboradorDTO = colaboradorService.buscar(pagamentoColaboradorDTO.getIdColaborador());
//        enviarEmailService.pagamento(listDTO, colaboradorDTO.getEmail());
        return pagamentoColaboradorDTO;
    }

    public void deletar (Integer id){
        repository.deleteById(id);
    }

    public List<MetaDTO> metas(Integer id) {
        return repository.buscarPagamentosColaborador(id);
    }

}
