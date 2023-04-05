import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ColaboradorRoutingModule} from './colaborador-routing.module';
import {ColaboradorListComponent} from './pages/colaborador-list/colaborador-list.component';
import {SharedModule} from 'src/app/shared/shared.module';
import { ColaboradorFormComponent } from './pages/colaborador-form/colaborador-form.component';
import {DropdownModule} from "primeng/dropdown";
import {CardModule} from "primeng/card";


@NgModule({
    declarations: [
        ColaboradorListComponent,
        ColaboradorFormComponent,
    ],
    imports: [
        CommonModule,
        ColaboradorRoutingModule,
        SharedModule,
        DropdownModule,
        CardModule
    ]
})
export class ColaboradorModule { }
