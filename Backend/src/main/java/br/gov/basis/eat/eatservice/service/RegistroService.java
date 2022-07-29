package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.domain.Registro;
import br.gov.basis.eat.eatservice.repository.RegistroRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownRegistroDTO;
import br.gov.basis.eat.eatservice.service.dto.RegistroDTO;
import br.gov.basis.eat.eatservice.service.dto.RegistroListDTO;
import br.gov.basis.eat.eatservice.service.mapper.RegistroListMapper;
import br.gov.basis.eat.eatservice.service.mapper.RegistroMapper;
import br.gov.basis.eat.eatservice.service.util.MensagemRegistroUtil;

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
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final RegistroMapper registroMapper;
    private final RegistroListMapper registroListMapper;

    private final ImpactoService impactoService;
    private final StatusRegistroService statusRegistroService;
    private final PrioridadeService prioridadeService;
    private final ClienteService clienteService;
    private final TipoRegistroService tipoRegistroService;

    public List<RegistroListDTO> buscarTodas(){
        List<Registro> listaRegistros = registroRepository.findAll();
            return registroListMapper.toDto(listaRegistros);
    }

    public DropdownRegistroDTO buscarDropdownRegistro() {
        DropdownRegistroDTO dropdownRegistroDTO = new DropdownRegistroDTO();
        dropdownRegistroDTO.setImpacto(impactoService.listar());
        dropdownRegistroDTO.setStatusRegistro(statusRegistroService.listar());
        dropdownRegistroDTO.setPrioridade(prioridadeService.listar());
        dropdownRegistroDTO.setCliente(clienteService.obterDropdown());
        dropdownRegistroDTO.setTipoRegistro(tipoRegistroService.listar());
        return dropdownRegistroDTO;
    }

    public RegistroDTO buscarPorId(Long id){
        Registro registro = registroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemRegistroUtil.REGISTRO_NAO_ENCONTRADO));
            return registroMapper.toDto(registro);
    }

    public RegistroDTO inserir(RegistroDTO registroDTO){
        verificarNomeDuplicado(registroDTO);
        Registro registro = registroMapper.toEntity(registroDTO);
        registro = registroRepository.save(registro);
        registro.setIdNegociavel(geraIdNegociavel(registro.getId()));
        return registroMapper.toDto(registroRepository.save(registro));
    }

    public String geraIdNegociavel(Long id){
        return "EAT-" + id.toString();
    }

    public RegistroDTO atualizar(RegistroDTO registroDTO){
        if (Objects.nonNull(registroDTO.getId())){
            Registro registro = registroMapper.toEntity(registroDTO);
            return registroMapper.toDto(registroRepository.save(registro));
        }
        return registroDTO;
    }

    public void excluir(Long id){
        Registro registro = registroRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,MensagemRegistroUtil.REGISTRO_NAO_ENCONTRADO));
        registro.setAtivo(false);
        registroRepository.save(registro);
    }

    private void verificarNomeDuplicado(RegistroDTO registroDTO) {
        Optional<Registro> optionalRegistro = registroRepository.findByTitulo(registroDTO.getTitulo());
        if(isTituloDuplicado(registroDTO, optionalRegistro)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                MensagemRegistroUtil.REGISTRO_JA_CADASTRADO);
        }
    }

    private boolean isTituloDuplicado(RegistroDTO registroDTO, Optional<Registro> optionalRegistro) {
        return optionalRegistro.isPresent() && (!optionalRegistro.get().getId().equals(registroDTO.getId())
            || Objects.isNull(registroDTO.getId()));
    }


}
