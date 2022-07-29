import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjetoRoutingModule } from './projeto-routing.module';
import { ProjetoListComponent } from './components/projeto-list/projeto-list.component';
import {SharedModule} from '../../shared/shared.module';
import { ProjetoFormComponent } from './components/projeto-form/projeto-form.component';


@NgModule({
  declarations: [ProjetoListComponent, ProjetoFormComponent],
  imports: [
    CommonModule,
    ProjetoRoutingModule,
    SharedModule
  ]
})
export class ProjetoModule { }
