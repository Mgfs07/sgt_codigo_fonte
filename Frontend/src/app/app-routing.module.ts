import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import {ClienteModule} from './modules/cliente/cliente.module';
import {ProjetoModule} from './modules/projeto/projeto.module';
import {UsuarioEATModule} from './modules/usuario-eat/usuario-eat.module';
import {RegistroModule} from './modules/registro/registro.module';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: '', loadChildren: () => RegistroModule, data: {breadcrumb: 'Registros'}},
    { path: 'projetos', loadChildren: () => ProjetoModule, data: {breadcrumb: 'Projetos'}},
    { path: 'clientes', loadChildren: () => ClienteModule, data: {breadcrumb: 'Clientes'}},
    { path: 'usuarios', loadChildren: () => UsuarioEATModule, data: {breadcrumb: 'Usuários'}},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
