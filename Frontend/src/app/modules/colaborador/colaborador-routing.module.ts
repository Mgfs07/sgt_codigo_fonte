import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';

const routes: Routes = [
    { path: '', component: ColaboradorComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ColaboradorRoutingModule { }
