import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColunaModel} from "../../../../model/coluna.model";
import {DoacaoListModel} from "../../../../model/doacao-list.model";
import {DoacoesService} from "../../../../shared/service/doacoes.service";
import {DoacaoComponent} from "../doacao/doacao.component";

@Component({
    selector: 'app-doacao-list',
    templateUrl: './doacao-list.component.html',
    styleUrls: ['./doacao-list.component.scss']
})
export class DoacaoListComponent implements OnInit {

    doacoes: DoacaoListModel[] = [];
    coluna: ColunaModel[] = [];


    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(DoacaoComponent) formDoacao: DoacaoComponent;

    constructor(
        private doacoesService: DoacoesService,
    ) {
    }

    ngOnInit(): void {
        this.columnsTable();
        this.obterGastos();
    }

    public columnsTable() {
        this.coluna = [
            new ColunaModel('nomeDoador', 'Nome Doador'),
            new ColunaModel('observacao', 'Observacao'),
            new ColunaModel('doadoParaPagamento', 'Doado Para'),
            new ColunaModel('valorDoado', 'Valor'),
            new ColunaModel('dataDoacao', 'Data'),
            new ColunaModel('acoes', 'Ações', '10%' )
        ];
    }

    public obterGastos(): void {
        this.doacoesService.findAll().subscribe(
            (data) => {
                this.doacoes = data;
            }
        );
    }

    isData(coluna: string): boolean {
        return coluna === 'dataDoacao';
    }

    isValor(coluna: string): boolean {
        return coluna == 'valorDoado';
    }

    editar(id: number): void {
        this.formDoacao.editarPagamento(id);
        this.display = true;
    }

    novoPagamento(): void {
        this.formDoacao.formPagamento.reset();
        this.display = true;
    }

    public fecharModal(): void {
        this.display = false;
        if (this.formDoacao.listarPagamento) {
            this.obterGastos();
            this.formDoacao.listarPagamento = false;
        }
    }

    resetarForm(): void {
        this.formDoacao.fecharForm();
    }

    carregar(idDoacao: number): void {
        this.display = true;
        this.formDoacao.editarPagamento(idDoacao);
        this.formDoacao.formPagamento.enable();
    }

}
