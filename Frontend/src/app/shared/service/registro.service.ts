import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {RegistroModel} from '../../model/registro.model';
import {RegistroListModel} from '../../model/registro-list.model';
import {DropdownRegistroModel} from '../../model/dropdown-registro.model';

@Injectable({
    providedIn: 'root'
})
export class RegistroService {

    constructor(private httpClient: HttpClient) {
    }

    protected UrlService: string = environment.apiUrl;

    public getRegistro(): Observable<RegistroListModel[]> {
        return this.httpClient.get<RegistroListModel[]>(this.UrlService + '/registros');
    }

    public getRegitroId(id: number): Observable<RegistroModel> {
        return this.httpClient.get<RegistroModel>(`${this.UrlService}/registros/${id}`);
    }

    public postRegistro(registro: RegistroModel): Observable<RegistroModel> {
        return this.httpClient.post<RegistroModel>(this.UrlService + '/registros', registro);
    }

    public putRegistro(registro: RegistroModel): Observable<RegistroModel> {
        return this.httpClient.put<RegistroModel>(`${this.UrlService}/registros/`, registro);
    }

    public deleteRegistro(id: number): Observable<RegistroModel> {
        return this.httpClient.delete<RegistroModel>(`${this.UrlService}/registros/${id}`);
    }

    public getDropDownRegistro(): Observable<DropdownRegistroModel> {
        return this.httpClient.get<DropdownRegistroModel>(`${this.UrlService}/registros/dropdown-registro`);
    }
}
