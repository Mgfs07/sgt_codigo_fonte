import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";
import {DoacaoModel} from "../../model/doacao.model";
import {DoacaoListModel} from "../../model/doacao-list.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DoacoesService extends BaseEntityService<DoacaoListModel, DoacaoModel>{

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'doacoes';
    }

    salvar(entity: DoacaoModel): Observable<DoacaoModel> {
        return this.http.post<DoacaoModel>(this.resourceUrl, entity);
    }
}
