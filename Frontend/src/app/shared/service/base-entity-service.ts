import {HttpClient} from '@angular/common/http';
import {SelectItem} from 'primeng/api';
import {environment} from '../../../environments/environment';
import {Observable} from "rxjs";

export abstract class BaseEntityService<T, Y> {

    protected constructor(protected http: HttpClient) { }

    resourceUrl = environment.apiUrl + '/' + this.getEntity();

    abstract getEntity(): string;

    insert(entity: T): Observable<T> {
        return this.http.post<T>(this.resourceUrl, entity);
    }

    findById(id: number): Observable<T> {
        return this.http.get<T>(this.resourceUrl + '/' + id);
    }

    findAll(): Observable<T[]> {
        return this.http.get<T[]>(this.resourceUrl);
    }

    update(entity: T): Observable<T> {
        return this.http.put<T>(this.resourceUrl, entity);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + id);
    }

    findAllDropDown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/select');
    }

}
