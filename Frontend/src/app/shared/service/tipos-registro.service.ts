import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SelectItem} from 'primeng';

@Injectable({
  providedIn: 'root'
})
export class TiposRegistroService {

    private urlApi: string = environment.apiUrl + '/tipos-registro';

    constructor(private httpClient: HttpClient) {
    }

    buscarTipoRegistro(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi);
    }
}
