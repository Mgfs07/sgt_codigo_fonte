import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {ProjetoModel} from '../../model/projeto.model';
import {ProjetoListModel} from '../../model/projeto-list.model';

@Injectable({
    providedIn: 'root'
})
export class ProjetoService {

    constructor(private http: HttpClient) {
    }

    protected UrlService: string = environment.apiUrl;

    public getProjetos(): Observable<ProjetoListModel[]> {
        return this.http.get<ProjetoListModel[]>(this.UrlService + '/projetos');
    }
    public getProjetoById(id: number): Observable<ProjetoModel> {
        const url = `${this.UrlService}/projetos/${id}`;
        return this.http.get<ProjetoModel>(url);
    }

    public postProjetos(projeto: ProjetoModel): Observable<ProjetoModel> {
        return this.http.post<ProjetoModel>(this.UrlService + '/projetos', projeto);
    }

    public deleteProjetos(id: number): Observable<ProjetoModel> {
        const url = `${this.UrlService}/projetos/${id}`;
        return this.http.delete<ProjetoModel>(url);
    }

    public putProjetos(projetos: ProjetoModel): Observable<ProjetoModel> {
        const url = `${this.UrlService}/projetos`;
        return this.http.put<ProjetoModel>(url, projetos);
    }
}
