import { Component, Input } from '@angular/core';
import { OpcaoMenuModel } from 'src/app/shared/models/opcao-menu.model';
import { SidemenuModel } from 'src/app/shared/models/sidemenu.model';
import {Router} from "@angular/router";

@Component({
    selector: 'app-sidemenu',
    templateUrl: './sidemenu.component.html',
    styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent {
    @Input() public configuracaoMenuLateral?: SidemenuModel;
    @Input() public opcoes: OpcaoMenuModel[] = [
        new OpcaoMenuModel('pi pi-home', 'Início', () => this.router.navigateByUrl('/home')),
        new OpcaoMenuModel('pi pi-user', 'Colaborador', () => this.router.navigateByUrl('/colaborador')),
        new OpcaoMenuModel('pi pi-money-bill', 'Pagamentos', () => this.router.navigateByUrl('/pagamento')),
        new OpcaoMenuModel('pi pi-eject', 'Doacoes', () => this.router.navigateByUrl('/doacoes')),
        new OpcaoMenuModel('pi pi-exclamation-triangle', 'Gastos', () => this.router.navigateByUrl('/gastos')),
        new OpcaoMenuModel('pi pi-chart-bar', 'Meta', () => this.router.navigateByUrl('/pagamentos-metas'))
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
