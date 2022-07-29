import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {SelectItem} from 'primeng';

@Injectable({
    providedIn: 'root'
})
export class StatusAcaoService {

    private urlApi: string = environment.apiUrl + '/status-acao';

    constructor(private httpClient: HttpClient) {
    }

    buscarStatusAcao(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi);
    }
}
