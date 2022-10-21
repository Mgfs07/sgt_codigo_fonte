import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './pages/app/app.component';
import {TopbarComponent} from './components/topbar/topbar.component';
import {SidemenuComponent} from './components/sidemenu/sidemenu.component';
import {SharedModule} from './shared/shared.module';
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DialogModule} from "primeng/dialog";
import {ButtonModule} from "primeng/button";
import {ToastModule} from 'primeng/toast';
import {MessageService} from "primeng/api";
import {MensagensUtil} from "./shared/utils/mensagens-util";
import {InicioComponent} from "./pages/inicio/inicio.component";


@NgModule({
    declarations: [
        AppComponent,
        InicioComponent,
        TopbarComponent,
        SidemenuComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        SharedModule,
        HttpClientModule,
        BrowserAnimationsModule,
        DialogModule,
        ButtonModule,
        ToastModule,
        ],
    providers: [MessageService, MensagensUtil],
    exports: [
        InicioComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
