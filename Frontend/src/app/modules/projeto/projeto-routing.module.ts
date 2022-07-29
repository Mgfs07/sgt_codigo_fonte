import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProjetoListComponent} from './components/projeto-list/projeto-list.component';
import {ProjetoFormComponent} from './components/projeto-form/projeto-form.component';


const routes: Routes = [
    {   path: '', component: ProjetoListComponent},
    {   path: '/novo-projeto', component: ProjetoFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjetoRoutingModule { }
