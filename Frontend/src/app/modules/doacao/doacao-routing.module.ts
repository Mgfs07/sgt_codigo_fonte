import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DoacaoListComponent} from "./pages/doacao-list/doacao-list.component";

const routes: Routes = [
    { path: '', component: DoacaoListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoacaoRoutingModule { }
