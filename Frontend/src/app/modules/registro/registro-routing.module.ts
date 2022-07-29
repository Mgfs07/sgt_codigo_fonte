import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistroListComponent} from './components/registro-list/registro-list.component';


const routes: Routes = [
    {path: '', component: RegistroListComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegistroRoutingModule {
}
