package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.repository.ColaboradorRepository;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.mapper.ColaboradorListMapper;

import com.br.sgt.sgtproject.service.mapper.ColaboradorMapper;
import com.br.sgt.sgtproject.service.util.MensagemColaboradorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ColaboradorService {

    private final ColaboradorRepository repository;
    private final ColaboradorListMapper listMapper;
    private final ColaboradorMapper mapper;

    public List<ColaboradorListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAll());
    }

    public ColaboradorDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    private Colaborador buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemColaboradorUtil.COLABORADOR_NAO_ENCONTRADO));
    }

    public ColaboradorDTO salvar(ColaboradorDTO dto){
        verificarNomeDuplicado(dto);
        Colaborador colaborador = mapper.toEntity(dto);
        return mapper.toDto(repository.save(colaborador));
    }

    private void verificarNomeDuplicado(ColaboradorDTO dto){
        Optional<Colaborador> colaborador = repository.findByNomeColaboradorAndId(dto.getNomeColaborador(), dto.getId());
        if(Boolean.FALSE.equals(colaborador.isPresent())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    MensagemColaboradorUtil.COLABORADOR_JA_CADASTRADO);
        }
    }

    public void deletar(Integer id){
        Colaborador colaborador = buscarPorId(id);
        colaborador.setAtivo(false);
        repository.save(colaborador);
    }

    public List<DropdownDTO> dropdonwColaborador(){
        return repository.dropdownColaborador();
    }
}
