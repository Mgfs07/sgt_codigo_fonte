package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.domain.Usuario;
import br.gov.basis.eat.eatservice.repository.UsuarioRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import br.gov.basis.eat.eatservice.service.dto.UsuarioDTO;
import br.gov.basis.eat.eatservice.service.dto.UsuarioListDTO;
import br.gov.basis.eat.eatservice.service.mapper.UsuarioListMapper;
import br.gov.basis.eat.eatservice.service.mapper.UsuarioMapper;
import br.gov.basis.eat.eatservice.service.util.MensagemUsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioListMapper usuarioListMapper;

    public List<UsuarioListDTO> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioListMapper.toDto(usuarios);
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemUsuarioUtil.USUARIO_NAO_ENCONTRADO)));
    }

    public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
        if (Objects.nonNull(usuarioDTO.getId())) {
            Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
            return usuarioMapper.toDto(usuarioRepository.save(usuario));
        }
        return usuarioDTO;
    }

    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemUsuarioUtil.DELETAR_ERRO));
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }
    public List<DropdownBigDTO> obterDropdown(){
        return usuarioRepository.buscarUsuarioDropdown();
    }

}
