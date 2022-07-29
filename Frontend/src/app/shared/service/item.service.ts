import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ItemModel} from '../../model/item.model';
import {DropdownItemModel} from '../../model/dropdown-item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

    constructor(private http: HttpClient) {
    }

    protected UrlService: string = environment.apiUrl;

    public getItens(id: number): Observable<ItemModel[]> {
        return this.http.get<ItemModel[]>(this.UrlService + `/itens-registro/registro/${id}`);
    }
    public getItemById(id: number): Observable<ItemModel[]> {
        const url = `${this.UrlService}/itens-registro/${id}`;
        return this.http.get<ItemModel[]>(url);
    }

    public postItem(item: ItemModel): Observable<ItemModel> {
        return this.http.post<ItemModel>(this.UrlService + '/itens-registro', item);
    }

    public deleteItem(id: number): Observable<ItemModel> {
        const url = `${this.UrlService}/itens-registro/${id}`;
        return this.http.delete<ItemModel>(url);
    }

    public putItem(item: ItemModel): Observable<ItemModel> {
        const url = `${this.UrlService}/itens-registro`;
        return this.http.put<ItemModel>(url, item);
    }

    public getDropDownItem(idCliente: number): Observable<DropdownItemModel> {
        return this.http.get<DropdownItemModel>(`${this.UrlService}/itens-registro/dropdown-item/${idCliente}`);
    }
}
