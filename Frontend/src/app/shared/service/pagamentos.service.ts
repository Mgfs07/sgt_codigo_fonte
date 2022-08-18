import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";
import {PagamentoListModel} from "../../model/pagamento-list.model";

@Injectable({
  providedIn: 'root'
})
export class PagamentosService extends BaseEntityService<PagamentoListModel, PagamentoListModel> {

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'pagamentos';
    }
}
