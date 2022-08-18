import {Component, OnInit} from '@angular/core';
import {ColunaModel} from '../../../../model/coluna.model';
import {ColaboradoresListModel} from "../../../../model/colaboradores-list.model";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";

@Component({
    selector: 'app-colaborador',
    templateUrl: './colaborador.component.html',
    styleUrls: ['./colaborador.component.scss']
})
export class ColaboradorComponent implements OnInit {

    colaboradores: ColaboradoresListModel[] = [];
    coluna: ColunaModel[] = [];

    constructor(private colaborador: ColaboradorService) {
    }

    ngOnInit(): void {
        this.columnsTable();
        this.obterColaborador();
    }

    public columnsTable() {
        this.coluna = [
            new ColunaModel('nomeColaborador', 'Nome'),
            new ColunaModel('nomeUnidade', 'Unidade'),
            new ColunaModel('telefone', 'Telefone'),
        ];
    }

    public obterColaborador(): void {
        this.colaborador.findAll().subscribe(
            (data) => {
                    this.colaboradores = data;
            }
        );
    }


}
