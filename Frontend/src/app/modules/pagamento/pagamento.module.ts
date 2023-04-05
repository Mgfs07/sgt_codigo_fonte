import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagamentoRoutingModule } from './pagamento-routing.module';
import { PagamentoListComponent } from './pages/pagamento-list/pagamento-list.component';
import { PagamentoFormComponent } from './pages/pagamento-form/pagamento-form.component';
import {InputNumberModule} from "primeng/inputnumber";
import {ReactiveFormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {InputTextModule} from "primeng/inputtext";
import {SharedModule} from "../../shared/shared.module";
import {GastoModule} from "../gasto/gasto.module";


@NgModule({
  declarations: [
    PagamentoListComponent,
    PagamentoFormComponent
  ],
    imports: [
        CommonModule,
        PagamentoRoutingModule,
        InputNumberModule,
        ReactiveFormsModule,
        ButtonModule,
        DropdownModule,
        InputTextModule,
        SharedModule,
        GastoModule
    ]
})
export class PagamentoModule { }
