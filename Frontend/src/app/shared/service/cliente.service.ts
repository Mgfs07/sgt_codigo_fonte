import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {ClienteModel} from '../../model/cliente.model';
import {SelectItem} from 'primeng';


@Injectable({
    providedIn: 'root'
})
export class ClienteService {

    private urlApi: string = environment.apiUrl + '/clientes';

    constructor(private httpClient: HttpClient) {

    }

    buscarTodos(): Observable<ClienteModel[]> {
        return this.httpClient.get<ClienteModel[]>(this.urlApi);
    }

    buscarPorId(idCliente: number): Observable<ClienteModel> {
        return this.httpClient.get<ClienteModel>(`${this.urlApi}/${idCliente}`);
    }

    salvar(cliente: ClienteModel): Observable<ClienteModel> {
        return this.httpClient.post<ClienteModel>(this.urlApi, cliente);
    }

    editar(id: number, cliente: ClienteModel): Observable<ClienteModel> {
        return this.httpClient.put<ClienteModel>(`${this.urlApi}/${cliente.id}`, cliente);
    }

    deletar(idCliente: number): Observable<ClienteModel> {
        return this.httpClient.delete<ClienteModel>(`${this.urlApi}/${idCliente}`);
    }

    getClientes(): Observable<SelectItem[]> {
        return this.httpClient.get<SelectItem[]>(this.urlApi + '/dropdown');
    }

}
