import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {UsuarioEatModel} from '../../model/usuario-eat.model';

@Injectable({
    providedIn: 'root'
})
export class UsuarioEATService {

    constructor(private http: HttpClient) {
    }

    protected UrlService: string = environment.apiUrl;

    getUsuarios(): Observable<UsuarioEatModel[]> {
        return this.http.get<UsuarioEatModel[]>(this.UrlService + '/usuarios');
    }

}
