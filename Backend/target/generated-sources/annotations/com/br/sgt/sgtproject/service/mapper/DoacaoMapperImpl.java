package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.DoacaoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-19T17:28:22-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class DoacaoMapperImpl implements DoacaoMapper {

    @Override
    public List<Doacao> toEntity(List<DoacaoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Doacao> list = new ArrayList<Doacao>( dtoList.size() );
        for ( DoacaoDTO doacaoDTO : dtoList ) {
            list.add( toEntity( doacaoDTO ) );
        }

        return list;
    }

    @Override
    public List<DoacaoDTO> toDto(List<Doacao> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DoacaoDTO> list = new ArrayList<DoacaoDTO>( entityList.size() );
        for ( Doacao doacao : entityList ) {
            list.add( toDto( doacao ) );
        }

        return list;
    }

    @Override
    public DoacaoDTO toDto(Doacao doacao) {
        if ( doacao == null ) {
            return null;
        }

        DoacaoDTO doacaoDTO = new DoacaoDTO();

        doacaoDTO.setDoadoParaPagamento( doacaoDoadoParaId( doacao ) );
        doacaoDTO.setId( doacao.getId() );
        doacaoDTO.setNomeDoador( doacao.getNomeDoador() );
        doacaoDTO.setObservacao( doacao.getObservacao() );
        doacaoDTO.setValorDoado( doacao.getValorDoado() );
        doacaoDTO.setDataDoacao( doacao.getDataDoacao() );

        return doacaoDTO;
    }

    @Override
    public Doacao toEntity(DoacaoDTO doacaoDTO) {
        if ( doacaoDTO == null ) {
            return null;
        }

        Doacao doacao = new Doacao();

        doacao.setDoadoPara( doacaoDTOToPagamento( doacaoDTO ) );
        doacao.setId( doacaoDTO.getId() );
        doacao.setNomeDoador( doacaoDTO.getNomeDoador() );
        doacao.setObservacao( doacaoDTO.getObservacao() );
        doacao.setValorDoado( doacaoDTO.getValorDoado() );
        doacao.setDataDoacao( doacaoDTO.getDataDoacao() );

        return doacao;
    }

    private Integer doacaoDoadoParaId(Doacao doacao) {
        if ( doacao == null ) {
            return null;
        }
        Pagamento doadoPara = doacao.getDoadoPara();
        if ( doadoPara == null ) {
            return null;
        }
        Integer id = doadoPara.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Pagamento doacaoDTOToPagamento(DoacaoDTO doacaoDTO) {
        if ( doacaoDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setId( doacaoDTO.getDoadoParaPagamento() );

        return pagamento;
    }
}
