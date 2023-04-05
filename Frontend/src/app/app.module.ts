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
import {ConfirmationService, MessageService} from "primeng/api";
import {MensagensUtil} from "./shared/utils/mensagens-util";
import {InicioComponent} from "./pages/inicio/inicio.component";
import {DatePipe} from "@angular/common";
import {ChartModule} from "primeng/chart";
import {OrganizationChartModule} from "primeng/organizationchart";
import {CardModule} from "primeng/card";
import {BlockUIModule} from "ng-block-ui";


@NgModule({
    declarations: [
        AppComponent,
        InicioComponent,
        TopbarComponent,
        SidemenuComponent,
    ],
    imports: [
        BlockUIModule.forRoot(),
        BrowserModule,
        AppRoutingModule,
        SharedModule,
        HttpClientModule,
        BrowserAnimationsModule,
        DialogModule,
        ButtonModule,
        ToastModule,
        ChartModule,
        OrganizationChartModule,
        CardModule,
    ],
    providers: [MessageService, MensagensUtil, ConfirmationService, DatePipe],
    exports: [
        InicioComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
