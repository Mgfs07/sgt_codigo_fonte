import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColunaModel} from "../../../../model/coluna.model";
import {GastosService} from "../../../../shared/service/gastos.service";
import {GastoListModel} from "../../../../model/gasto-list.model";
import {GastosComponent} from "../gastos/gastos.component";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {ConfirmationService} from "primeng/api";
import {Mensagens} from "../../../../shared/utils/mensagens";
import {finalize} from "rxjs";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
    selector: 'app-gastos-list',
    templateUrl: './gastos-list.component.html',
    styleUrls: ['./gastos-list.component.scss']
})
export class GastosListComponent implements OnInit {


    colunas: ColunaModel[] = [];
    gastoList: GastoListModel[] = [];
    novo: boolean
    entidade: string = 'Gasto';

    @BlockUI() blockUI : NgBlockUI;
    @Input() display = false;
    @ViewChild(GastosComponent) formGasto: GastosComponent;

    constructor(private gastoService: GastosService,
                private mensagemUtil: MensagensUtil,
                private confirmationService: ConfirmationService
    ) {
    }

    ngOnInit(): void {
        this.columnsTable();
        this.obterPagamentoColaborador();
    }

    public columnsTable() {
        this.colunas = [
            new ColunaModel('motivo', 'Motivo'),
            new ColunaModel('nomeColaborador', 'Solicitante'),
            new ColunaModel('dataDispesa', 'Data'),
            new ColunaModel('valorRetirado', 'Valor'),
            new ColunaModel('retiradoDoPagamento', 'Retirado De'),
            new ColunaModel('acoes', 'Ações', '10%')
        ];
    }

    public obterPagamentoColaborador(): void {
        this.gastoService.buscarTodos().subscribe(
            (data) => {
                this.gastoList = data;
            }
        );
    }

    novoPagamento(): void {
        this.formGasto.formGasto.reset();
        this.display = true;
        this.novo = true;
    }

    public fecharModal(): void {
        this.display = false;
        if (this.formGasto.listarPagamento) {
            this.obterPagamentoColaborador();
            this.formGasto.listarPagamento = false;
        }
    }

    resetarForm(): void {
        this.formGasto.fecharForm();
    }

    verificarTitulo(): string {
        return this.mensagemUtil.tituloModal(this.novo, EntityEnum.GASTO);
    }

    carregar(idPagamento: number): void {
        this.display = true;
        this.novo = false;
        this.formGasto.editarGasto(idPagamento);
        this.formGasto.formGasto.enable();
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
        this.gastoService.deletar(id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.obterPagamentoColaborador();
            }))
            .subscribe(() => {this.mensagemUtil.mensagemSucesso(id, "", true)},
                    error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao Excluir Gasto.\n') )
    }

}
