import {Injectable} from '@angular/core';
import {ColaboradoresListModel} from "../../model/colaboradores-list.model";
import {ColaboradorModel} from "../../model/colaborador.model";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService{

  constructor(private http: HttpClient) {

  }
    resourceUrl = environment.apiUrl + '/colaboradores';

    insert(entity: ColaboradorModel): Observable<ColaboradorModel> {
        return this.http.post<ColaboradorModel>(this.resourceUrl, entity);
    }

    findById(id: number): Observable<ColaboradorModel> {
        return this.http.get<ColaboradorModel>(this.resourceUrl + '/' + id);
    }

    findAll(): Observable<ColaboradoresListModel[]> {
        return this.http.get<ColaboradoresListModel[]>(this.resourceUrl);
    }

    update(entity: ColaboradorModel): Observable<ColaboradorModel> {
        return this.http.put<ColaboradorModel>(this.resourceUrl, entity);
    }

    findAllDropDown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/select');
    }
}
