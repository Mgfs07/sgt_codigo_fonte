package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-18T00:33:21-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Ubuntu)"
)
@Component
public class DoacaoListMapperImpl implements DoacaoListMapper {

    @Override
    public List<Doacao> toEntity(List<DoacaoListDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Doacao> list = new ArrayList<Doacao>( dtoList.size() );
        for ( DoacaoListDTO doacaoListDTO : dtoList ) {
            list.add( toEntity( doacaoListDTO ) );
        }

        return list;
    }

    @Override
    public List<DoacaoListDTO> toDto(List<Doacao> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DoacaoListDTO> list = new ArrayList<DoacaoListDTO>( entityList.size() );
        for ( Doacao doacao : entityList ) {
            list.add( toDto( doacao ) );
        }

        return list;
    }

    @Override
    public DoacaoListDTO toDto(Doacao doacao) {
        if ( doacao == null ) {
            return null;
        }

        DoacaoListDTO doacaoListDTO = new DoacaoListDTO();

        doacaoListDTO.setDoadoParaPagamento( doacaoDoadoParaNomePagamento( doacao ) );
        doacaoListDTO.setId( doacao.getId() );
        doacaoListDTO.setNomeDoador( doacao.getNomeDoador() );
        doacaoListDTO.setObservacao( doacao.getObservacao() );
        doacaoListDTO.setValorDoado( doacao.getValorDoado() );
        doacaoListDTO.setDataDoacao( doacao.getDataDoacao() );

        return doacaoListDTO;
    }

    @Override
    public Doacao toEntity(DoacaoListDTO doacaoListDTO) {
        if ( doacaoListDTO == null ) {
            return null;
        }

        Doacao doacao = new Doacao();

        doacao.setDoadoPara( doacaoListDTOToPagamento( doacaoListDTO ) );
        doacao.setId( doacaoListDTO.getId() );
        doacao.setNomeDoador( doacaoListDTO.getNomeDoador() );
        doacao.setObservacao( doacaoListDTO.getObservacao() );
        doacao.setValorDoado( doacaoListDTO.getValorDoado() );
        doacao.setDataDoacao( doacaoListDTO.getDataDoacao() );

        return doacao;
    }

    private String doacaoDoadoParaNomePagamento(Doacao doacao) {
        if ( doacao == null ) {
            return null;
        }
        Pagamento doadoPara = doacao.getDoadoPara();
        if ( doadoPara == null ) {
            return null;
        }
        String nomePagamento = doadoPara.getNomePagamento();
        if ( nomePagamento == null ) {
            return null;
        }
        return nomePagamento;
    }

    protected Pagamento doacaoListDTOToPagamento(DoacaoListDTO doacaoListDTO) {
        if ( doacaoListDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setNomePagamento( doacaoListDTO.getDoadoParaPagamento() );

        return pagamento;
    }
}
