import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GastosListComponent} from "./pages/gastos-list/gastos-list.component";

const routes: Routes = [
    { path: '', component: GastosListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GastoRoutingModule { }
