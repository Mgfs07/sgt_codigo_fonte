import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './imports/primeng.imports';
import { ANGULAR_IMPORTS } from './imports/angular.imports';

@NgModule({
    declarations: [],
    imports: [
        PRIMENG_IMPORTS,
        ANGULAR_IMPORTS
    ],
    exports: [
        PRIMENG_IMPORTS,
        ANGULAR_IMPORTS
    ]
})
export class SharedModule { }
