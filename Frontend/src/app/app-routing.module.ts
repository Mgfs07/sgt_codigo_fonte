import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ColaboradorComponent} from "./modules/colaborador/pages/colaborador/colaborador.component";
import {ColaboradorModule} from "./modules/colaborador/colaborador.module";
import {DoacaoModule} from "./modules/doacao/doacao.module";
import {PagamentoColaboradorModule} from "./modules/pagamento-colaborador/pagamento-colaborador.module";
import {GastoModule} from "./modules/gasto/gasto.module";

const routes: Routes = [
    { path: 'colaborador', loadChildren: () => ColaboradorModule},
    { path: 'pagamento', loadChildren: () => PagamentoColaboradorModule},
    { path: 'doacoes', loadChildren: () => DoacaoModule},
    { path: 'gastos', loadChildren: () => GastoModule},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


// () => import('./modules/colaborador/colaborador.module').then(arquivo => arquivo.ColaboradorModule)
