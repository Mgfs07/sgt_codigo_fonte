import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
    PagamentosColaboradorListComponent
} from "./pages/pagamentos-colaborador-list/pagamentos-colaborador-list.component";

const routes: Routes = [
    { path: '', component: PagamentosColaboradorListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagamentoColaboradorRoutingModule { }
