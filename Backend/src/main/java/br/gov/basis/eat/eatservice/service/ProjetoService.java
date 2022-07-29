package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.domain.Projeto;
import br.gov.basis.eat.eatservice.repository.ProjetoRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import br.gov.basis.eat.eatservice.service.dto.ProjetoDTO;
import br.gov.basis.eat.eatservice.service.dto.ProjetoListDTO;
import br.gov.basis.eat.eatservice.service.mapper.ProjetoMapper;
import br.gov.basis.eat.eatservice.service.util.MensagemProjetoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    public List<ProjetoListDTO> listar(){
        return projetoRepository.listar();
    }

    public List<ProjetoListDTO> listarAtivoInativo(Boolean ativo){
        return projetoRepository.listarAtivoInativo(ativo);
    }

    public ProjetoDTO buscarPorId(Long idProjeto){
        return projetoMapper.toDto(projetoRepository.findById(idProjeto).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemProjetoUtil.PROJETO_NAO_ENCONTRADO)));
    }

    public ProjetoDTO salvar (ProjetoDTO projetoDTO){
        verificarNomeDuplicado(projetoDTO);
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        return projetoMapper.toDto(projetoRepository.save(projeto));
    }

    private void verificarNomeDuplicado(ProjetoDTO projetoDTO) {
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projetoDTO.getNome());
        if(isNomeDuplicado(projetoDTO, optionalProjeto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                MensagemProjetoUtil.PROJETO_JA_CADASTRADO);
        }
    }

    private boolean isNomeDuplicado(ProjetoDTO projetoDTO, Optional<Projeto> optionalProjeto) {
        return optionalProjeto.isPresent() && (Objects.isNull(projetoDTO.getId()) || !optionalProjeto.get().getId().equals(projetoDTO.getId()));
    }

    public void deletar(Long idProjeto){
        Projeto projeto = projetoRepository.findById(idProjeto).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemProjetoUtil.DELETAR_ERRO));
        projeto.setAtivo(false);
        projetoRepository.save(projeto);
    }

    public List<DropdownBigDTO> obterDropdown(Long idCliente){
        return projetoRepository.buscarProjetoDropdown(idCliente);
    }

}
