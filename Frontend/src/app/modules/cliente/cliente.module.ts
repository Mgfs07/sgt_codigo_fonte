import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ClienteRoutingModule} from './cliente-routing.module';
import {ClienteListaComponent} from './components/cliente-lista/cliente-lista.component';
import {ClienteCadastroComponent} from './components/cliente-cadastro/cliente-cadastro.component';
import {SharedModule} from '../../shared/shared.module';
import {ConfirmationService} from 'primeng';
import {Ng2SearchPipeModule} from 'ng2-search-filter';


@NgModule({
    declarations: [ClienteListaComponent, ClienteCadastroComponent],
    imports: [
        CommonModule,
        ClienteRoutingModule,
        SharedModule,
        Ng2SearchPipeModule
    ],
    providers: [ConfirmationService]
})
export class ClienteModule {
}
