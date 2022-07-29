import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {SelectItem} from 'primeng';

@Injectable({
    providedIn: 'root'
})
export class PrioridadeService {

    private urlApi: string = environment.apiUrl + '/prioridades';

    constructor(private httpClient: HttpClient) {
    }

    buscarPrioridade(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi);
    }
}
