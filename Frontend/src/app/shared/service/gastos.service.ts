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
export class GastosService extends BaseEntityService<GastoModel, GastoListModel> {

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'gastos';
    }

    valores(): Observable<ValoresModel>{
        return this.http.get<ValoresModel>(`${this.resourceUrl}/valores`)
    }
}
