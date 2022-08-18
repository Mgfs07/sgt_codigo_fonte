import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ColaboradorRoutingModule} from './colaborador-routing.module';
import {ColaboradorComponent} from './pages/colaborador/colaborador.component';
import {SharedModule} from 'src/app/shared/shared.module';


@NgModule({
    declarations: [
        ColaboradorComponent,
    ],
    imports: [
        CommonModule,
        ColaboradorRoutingModule,
        SharedModule
    ]
})
export class ColaboradorModule { }
