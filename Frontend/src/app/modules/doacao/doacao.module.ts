import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DoacaoRoutingModule} from './doacao-routing.module';
import {DoacaoListComponent} from './pages/doacao-list/doacao-list.component';
import {DoacaoComponent} from './pages/doacao/doacao.component';
import {SharedModule} from "../../shared/shared.module";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";


@NgModule({
    declarations: [
        DoacaoListComponent,
        DoacaoComponent,
    ],
    imports: [
        CommonModule,
        DoacaoRoutingModule,
        SharedModule,
        DropdownModule,
        CalendarModule
    ]
})
export class DoacaoModule {
}
