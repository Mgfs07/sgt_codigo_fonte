import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";
import {PagamentoColaboradorModel} from "../../model/pagamento-colaborador.model";
import {Observable} from "rxjs";
import {MetaModel} from "../../model/meta.model";

@Injectable({
  providedIn: 'root'
})
export class PagamentosColaboradoresService extends BaseEntityService<PagamentoColaboradorModel, PagamentoColaboradorModel> {

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'pagamentos-colaboradores';
    }

    buscarPagamentosDoColaborador(id: number): Observable<MetaModel[]> {
        return this.http.get<MetaModel[]>(this.resourceUrl + '/meta/'+id);
    }


}
