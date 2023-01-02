import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";
import {PagamentoModel} from "../../model/pagamento.model";

@Injectable({
  providedIn: 'root'
})
export class PagamentosService extends BaseEntityService<PagamentoModel, PagamentoModel> {

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'pagamentos';
    }
}
