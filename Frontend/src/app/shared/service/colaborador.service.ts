import {Injectable} from '@angular/core';
import {ColaboradoresListModel} from "../../model/colaboradores-list.model";
import {ColaboradorModel} from "../../model/colaborador.model";
import {HttpClient} from "@angular/common/http";
import {BaseEntityService} from "./base-entity-service";

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService extends BaseEntityService<ColaboradorModel, ColaboradoresListModel>{

  constructor(protected override http: HttpClient) {
      super(http);

  }
   override getEntity(): string {
        return "colaboradores";
    }

}
