import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PagamentoColaboradorRoutingModule} from './pagamento-colaborador-routing.module';
import {
    PagamentosColaboradorListComponent
} from './pages/pagamentos-colaborador-list/pagamentos-colaborador-list.component';
import {PagamentoColaboradorComponent} from './pages/pagamento-colaborador/pagamento-colaborador.component';
import {SharedModule} from "../../shared/shared.module";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";


@NgModule({
    declarations: [
        PagamentosColaboradorListComponent,
        PagamentoColaboradorComponent
    ],
    imports: [
        CommonModule,
        PagamentoColaboradorRoutingModule,
        SharedModule,
        DropdownModule,
        CalendarModule
    ]
})
export class PagamentoColaboradorModule {
}
