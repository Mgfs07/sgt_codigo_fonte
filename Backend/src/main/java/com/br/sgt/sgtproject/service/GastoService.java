package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.repository.GastoRepository;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import com.br.sgt.sgtproject.service.dto.ValoresDTO;
import com.br.sgt.sgtproject.service.mapper.GastoListMapper;
import com.br.sgt.sgtproject.service.mapper.GastoMapper;
import com.br.sgt.sgtproject.service.util.MensagemGastosUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GastoService {

    private final GastoRepository repository;
    private final GastoListMapper listMapper;
    private final GastoMapper mapper;

    public List<GastoListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAll());
    }

    private Gasto buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemGastosUtil.GASTO_NAO_ENCONTRADO));
    }

    public GastoDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    public GastoDTO salvar(GastoDTO dto){
        Gasto gasto = mapper.toEntity(dto);
        return mapper.toDto(repository.save(gasto));
    }

    public ValoresDTO valorGasto(){
        ValoresDTO valoresDTO = new ValoresDTO();
        valoresDTO.setMensalidade(recuperarTotal(1));
        valoresDTO.setCampori(recuperarTotal(2));
        valoresDTO.setCamisa(recuperarTotal(3));
        valoresDTO.setAcampEdessa(recuperarTotal(4));
        valoresDTO.setCaderno(recuperarTotal(5));
        valoresDTO.setLivre(recuperarTotal(6));
        valoresDTO.setDoacoes(recuperarTotal(7));
        return valoresDTO;
    }

    private Double recuperarTotal(Integer idPagamento){
        Double totalCredito = 0D;
        Double totalDebito = 0D;
        totalCredito += repository.valorPagado(idPagamento);
        totalCredito += repository.valorDoado(idPagamento);
        totalDebito += repository.valorGastoDoPagamento(idPagamento);
        totalDebito += repository.valorGasto(idPagamento);
        return totalCredito - totalDebito;
    }
}
