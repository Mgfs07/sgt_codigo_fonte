import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColunaModel} from "../../../../model/coluna.model";
import {DoacaoListModel} from "../../../../model/doacao-list.model";
import {DoacoesService} from "../../../../shared/service/doacoes.service";
import {DoacaoComponent} from "../doacao/doacao.component";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {ConfirmationService} from "primeng/api";
import {Mensagens} from "../../../../shared/utils/mensagens";
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {finalize} from "rxjs";

@Component({
    selector: 'app-doacao-list',
    templateUrl: './doacao-list.component.html',
    styleUrls: ['./doacao-list.component.scss']
})
export class DoacaoListComponent implements OnInit {

    doacoes: DoacaoListModel[] = [];
    coluna: ColunaModel[] = [];
    novo: boolean;

    @BlockUI() blockUI : NgBlockUI;
    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(DoacaoComponent) formDoacao: DoacaoComponent;

    constructor(
        private doacoesService: DoacoesService,
        private mensagemUtil: MensagensUtil,
        private confirmationService: ConfirmationService
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
        this.doacoesService.buscarTodos().subscribe(
            (data) => {
                this.doacoes = data;
            }
        );
    }

    verificarTitulo(): string {
        return this.mensagemUtil.tituloModal(this.novo, EntityEnum.DOACAO);
    }

    novoPagamento(): void {
        this.formDoacao.formularioPagamento.reset();
        this.display = true;
        this.novo = true;
    }

    confirmarExclusao(id: number): void {
        this.confirmationService.confirm({
            header: 'Excluir Doação',
            message: 'Deseja excluir esse registro? ',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => this.deletar(id)
        })
    }

    public deletar(id: number): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.doacoesService.deletar(id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.obterGastos();
            }))
            .subscribe(() => {this.mensagemUtil.mensagemSucesso(id, "", true);},
                    error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao Excluir Doacao.\n') )
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
        this.novo = false;
        this.formDoacao.editarPagamento(idDoacao);
        this.formDoacao.formularioPagamento.enable();
    }

}
