import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './imports/primeng.imports';
import { ANGULAR_IMPORTS } from './imports/angular.imports';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TabelaGenericaComponent } from './components/tabela-generica/tabela-generica.component';
import {MensagensUtil} from "./utils/mensagens-util";
import {ConversoesUtil} from "./utils/conversoes.util";
import {ConfirmDialogModule} from "primeng/confirmdialog";

@NgModule({
    declarations: [
    TabelaGenericaComponent,

  ],
    imports: [
        PRIMENG_IMPORTS,
        ANGULAR_IMPORTS,
        FormsModule,
        ReactiveFormsModule,
        ConfirmDialogModule,
    ],
    exports: [
        PRIMENG_IMPORTS,
        ANGULAR_IMPORTS,
        FormsModule,
        ReactiveFormsModule,
        TabelaGenericaComponent,
    ],
    providers: [
        MensagensUtil,
        ConversoesUtil
    ]
})
export class SharedModule { }
