import {HttpClient} from '@angular/common/http';
import {SelectItem} from 'primeng/api';
import {environment} from '../../../environments/environment';
import {Observable} from "rxjs";

export abstract class BaseEntityService<T, Y> {

    protected constructor(protected http: HttpClient) { }

    resourceUrl = environment.apiUrl + '/' + this.getEntity();

    abstract getEntity(): string;

    salvar(entity: T): Observable<T> {
        return this.http.post<T>(this.resourceUrl, entity);
    }

    findById(id: number): Observable<T> {
        return this.http.get<T>(this.resourceUrl + '/' + id);
    }

    buscarTodos(): Observable<Y[]> {
        return this.http.get<Y[]>(this.resourceUrl);
    }

    atualizar(entity: T): Observable<T> {
        return this.http.put<T>(this.resourceUrl, entity);
    }

    deletar(id: number): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + id);
    }

    buscarDropdown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/select');
    }

}
