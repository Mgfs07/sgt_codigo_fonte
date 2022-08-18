import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";

@Injectable({
  providedIn: 'root'
})
export class UnidadeServicee extends BaseEntityService<any, any>{

    constructor(protected override http: HttpClient) { super(http);}

    override getEntity(): string {
        return 'pagamentos-colaboradores';
    }
}
