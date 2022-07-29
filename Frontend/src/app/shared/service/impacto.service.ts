import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {SelectItem} from 'primeng';

@Injectable({
    providedIn: 'root'
})
export class ImpactoService {

    private urlApi: string = environment.apiUrl + '/impactos';

    constructor(private httpClient: HttpClient) {
    }

    buscarStatusOcorrencia(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi);
    }
}
