import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";
import {GastoModel} from "../../model/gasto.model";
import {GastoListModel} from "../../model/gasto-list.model";
import {Observable} from "rxjs";
import {ValoresModel} from "../../model/valores.model";

@Injectable({
  providedIn: 'root'
})
export class GastosService extends BaseEntityService<GastoListModel, GastoModel> {

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'gastos';
    }

    salvar(entity: GastoModel): Observable<GastoModel> {
        return this.http.post<GastoModel>(this.resourceUrl, entity);
    }

    valores(): Observable<ValoresModel>{
        return this.http.get<ValoresModel>(`${this.resourceUrl}/valores`)
    }
}
