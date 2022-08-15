import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ColaboradorRoutingModule } from './colaborador-routing.module';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { ColaboradorListaComponent } from './components/colaborador-lista/colaborador-lista.component';
import { ColaboradorFormularioComponent } from './components/colaborador-formulario/colaborador-formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
    declarations: [
        ColaboradorComponent,
        ColaboradorListaComponent,
        ColaboradorFormularioComponent
    ],
    imports: [
        CommonModule,
        ColaboradorRoutingModule,
        SharedModule
    ]
})
export class ColaboradorModule { }
