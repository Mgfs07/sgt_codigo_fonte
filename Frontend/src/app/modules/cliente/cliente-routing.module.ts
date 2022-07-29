import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClienteListaComponent} from './components/cliente-lista/cliente-lista.component';


const routes: Routes = [
    {path: '', component: ClienteListaComponent, pathMatch: 'full'},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ClienteRoutingModule {
}
