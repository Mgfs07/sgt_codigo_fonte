import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColunaModel} from '../../../../model/coluna.model';
import {ColaboradoresListModel} from "../../../../model/colaboradores-list.model";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {ColaboradorFormComponent} from "../colaborador-form/colaborador-form.component";

@Component({
    selector: 'app-colaborador',
    templateUrl: './colaborador.component.html',
    styleUrls: ['./colaborador.component.scss']
})
export class ColaboradorComponent implements OnInit {

    colaboradores: ColaboradoresListModel[] = [];
    coluna: ColunaModel[] = [];

    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(ColaboradorFormComponent) formColaborador: ColaboradorFormComponent;

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
            new ColunaModel('acoes', 'Ações', '10%' )
        ];
    }

    public obterColaborador(): void {
        this.colaborador.buscarTodos().subscribe(
            (data) => {
                    this.colaboradores = data;
            }
        );
    }

    carregar(idColaborador: number): void {
        this.display = true;
        this.formColaborador.editar(idColaborador);
        this.formColaborador.formularioColaborador.enable();
    }

    deletar(id: number) : void {
        this.colaborador.deletar(id).subscribe(() => {
            this.obterColaborador();
        })
    }


    public fecharModal(): void {
        this.display = false;
        if (this.formColaborador.listarColaborador) {
            this.obterColaborador();
            this.formColaborador.listarColaborador = false;
        }
    }

    resetarForm(): void {
        this.formColaborador.fecharForm();
    }

    novoColaborador(): void {
        this.formColaborador.formularioColaborador.reset();
        this.display = true;
    }
}
