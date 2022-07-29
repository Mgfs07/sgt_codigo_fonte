import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsuarioEatListComponent} from './components/usuario-eat-list/usuario-eat-list.component';


const routes: Routes = [
    {path: '', component: UsuarioEatListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioEATRoutingModule { }
