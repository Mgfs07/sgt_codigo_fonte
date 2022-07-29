import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Ng2SearchPipeModule} from 'ng2-search-filter';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule,
        Ng2SearchPipeModule

    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class SharedModule { }
