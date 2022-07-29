import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UsuarioEATRoutingModule} from './usuario-eat-routing.module';
import {SharedModule} from '../../shared/shared.module';
import { UsuarioEatListComponent } from './components/usuario-eat-list/usuario-eat-list.component';


@NgModule({
    declarations: [UsuarioEatListComponent],
    imports: [
        CommonModule,
        UsuarioEATRoutingModule,
        SharedModule
    ]
})
export class UsuarioEATModule {
}
