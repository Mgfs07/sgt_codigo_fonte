import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PagamentoListComponent} from "./pages/pagamento-list/pagamento-list.component";

const routes: Routes = [
    {path: '', component: PagamentoListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagamentoRoutingModule { }
