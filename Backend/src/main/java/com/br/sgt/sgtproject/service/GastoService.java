package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.repository.GastoRepository;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.dto.EmailGastoDTO;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import com.br.sgt.sgtproject.service.dto.TipoPagamentoDTO;
import com.br.sgt.sgtproject.service.dto.ValoresDTO;
import com.br.sgt.sgtproject.service.mapper.GastoListMapper;
import com.br.sgt.sgtproject.service.mapper.GastoMapper;
import com.br.sgt.sgtproject.service.util.MensagemGastosUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Transactional
public class GastoService {

    private final GastoRepository repository;
    private final GastoListMapper listMapper;
    private final GastoMapper mapper;
    private final EnviarEmailService enviarEmailService;

    private final PagamentoService pagamentoService;

    public List<GastoListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAllByOrderByColaboradorId());
    }

    private Gasto buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemGastosUtil.GASTO_NAO_ENCONTRADO));
    }

    public GastoDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    public GastoDTO salvar(GastoDTO dto){
        Gasto gasto = repository.save(mapper.toEntity(dto));
        EmailGastoDTO gastoDTO = repository.buscarGastoClube(gasto.getId());
        enviarEmailService.gasto(gastoDTO);
        return mapper.toDto(gasto);
    }

    public ValoresDTO valorGasto(){
        List<DropdownDTO> listaPagamento = pagamentoService.buscarPagamentos();
        ValoresDTO listaGastos = new ValoresDTO();
        List<TipoPagamentoDTO> listagem = new ArrayList<>();
        listaPagamento.forEach(item ->
                listagem.add(new TipoPagamentoDTO(item.getLabel(), recuperarTotal(item.getValue()))));
        listagem.removeIf(item -> item.getValorPago() == 0);
        listagem.removeIf(item -> item.getNomePagamento().equals("CAMISA - 2022"));
        listaGastos.setNomePagamento(listagem);
        listaGastos.setTotal(calcularTodosPagamentos(listagem));
        return listaGastos;
    }

    private Double recuperarTotal(Integer idPagamento){
        Double totalCredito = 0D;
        Double totalDebito = 0D;
        totalCredito += repository.valorPago(idPagamento);
        totalCredito += repository.valorDoado(idPagamento);
        totalDebito += repository.valorGastoDoPagamento(idPagamento);
        totalDebito += repository.valorGasto(idPagamento);
        return totalCredito - totalDebito;
    }


    private Double calcularTodosPagamentos(List<TipoPagamentoDTO> valoresDTO){
        AtomicReference<Double> total = new AtomicReference<>(0D);
        valoresDTO.forEach(item -> total.updateAndGet(v -> v + item.getValorPago()));
        return total.get();

    }

    public void deletar(Integer id){
        repository.deleteById(id);
    }
}
