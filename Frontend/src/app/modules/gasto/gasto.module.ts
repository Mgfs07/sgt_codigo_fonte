import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GastoRoutingModule} from './gasto-routing.module';
import {GastosListComponent} from './pages/gastos-list/gastos-list.component';
import {GastosComponent} from './pages/gastos/gastos.component';
import {SharedModule} from "../../shared/shared.module";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {CardModule} from "primeng/card";


@NgModule({
    declarations: [
        GastosListComponent,
        GastosComponent
    ],
    imports: [
        CommonModule,
        GastoRoutingModule,
        SharedModule,
        DropdownModule,
        CalendarModule,
        CardModule
    ]
})
export class GastoModule {
}
