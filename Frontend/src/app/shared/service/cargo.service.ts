import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {SelectItem} from 'primeng';

@Injectable({
    providedIn: 'root'
})
export class CargoService {

    private urlApi: string = environment.apiUrl + '/cargos';

    constructor(private httpClient: HttpClient) {
    }

    buscarCargo(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi);
    }
}
