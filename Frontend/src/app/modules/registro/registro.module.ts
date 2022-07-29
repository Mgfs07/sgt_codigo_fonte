import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RegistroRoutingModule} from './registro-routing.module';
import { RegistroFormComponent } from './components/registro-form/registro-form.component';
import { RegistroListComponent } from './components/registro-list/registro-list.component';
import {SharedModule} from '../../shared/shared.module';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {TabViewModule} from "primeng/tabview";
import {ItemListComponent} from "./components/item-list/item-list.component";
import {ItemFormComponent} from "./components/item-form/item-form.component";


@NgModule({
    declarations: [RegistroFormComponent, RegistroListComponent, ItemListComponent, ItemFormComponent],
    imports: [
        CommonModule,
        RegistroRoutingModule,
        SharedModule,
        Ng2SearchPipeModule,
        TabViewModule,
    ]
})
export class RegistroModule {
}
