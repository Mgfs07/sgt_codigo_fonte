import { Component, Input } from '@angular/core';
import { OpcaoMenuModel } from 'src/app/shared/models/opcao-menu.model';
import { SidemenuModel } from 'src/app/shared/models/sidemenu.model';
import {ColaboradorModule} from "../../modules/colaborador/colaborador.module";
import {Router, RouterModule} from "@angular/router";

@Component({
    selector: 'app-sidemenu',
    templateUrl: './sidemenu.component.html',
    styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent {
    @Input() public configuracaoMenuLateral?: SidemenuModel;
    @Input() public opcoes: OpcaoMenuModel[] = [
        new OpcaoMenuModel('pi pi-home', 'Início', () => window.alert('Casa!!!')),
        new OpcaoMenuModel('pi pi-user', 'Colaborador', () => this.router.navigateByUrl('/colaborador'))
    ];

    constructor(
        private router: Router
    ) {
    }

    public estaVisivel(): boolean {
        if(this.configuracaoMenuLateral) {
            return this.configuracaoMenuLateral.visivel;
        }
        return false;
    }
}
