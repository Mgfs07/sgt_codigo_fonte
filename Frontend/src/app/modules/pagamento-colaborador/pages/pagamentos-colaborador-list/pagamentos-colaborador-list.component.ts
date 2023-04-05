import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {PagamentosColaboradoresService} from "../../../../shared/service/pagamentos-colaboradores.service";
import {ColunaModel} from "../../../../model/coluna.model";
import {PagamentoColaboradorModel} from "../../../../model/pagamento-colaborador.model";
import {PagamentoColaboradorComponent} from "../pagamento-colaborador/pagamento-colaborador.component";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {ConfirmationService} from "primeng/api";
import {Mensagens} from "../../../../shared/utils/mensagens";
import {finalize} from "rxjs";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'app-pagamentos-colaborador-list',
  templateUrl: './pagamentos-colaborador-list.component.html',
  styleUrls: ['./pagamentos-colaborador-list.component.scss']
})
export class PagamentosColaboradorListComponent implements OnInit {

    colunas: ColunaModel[] = [];
    pagamentoColaborador: PagamentoColaboradorModel[] = [];
    novo: boolean;

    @BlockUI() blockUI : NgBlockUI;
    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(PagamentoColaboradorComponent) formPagamentoColaborador: PagamentoColaboradorComponent;

    constructor(
      private pagamentoColaboradorService: PagamentosColaboradoresService,
      private mensagemUtil: MensagensUtil,
      private confirmationService: ConfirmationService
  ) { }

    ngOnInit(): void {
        this.columnsTable();
        this.obterPagamentoColaborador();
    }

    public columnsTable() {
        this.colunas = [
            new ColunaModel('nomeColaborador', 'Nome Membro'),
            new ColunaModel('nomePagamento', 'Pagou'),
            new ColunaModel('dataPagamento', 'Data'),
            new ColunaModel('valorPago', 'Valor'),
            new ColunaModel('acoes', 'Ações', '10%' )
        ];
    }

    public obterPagamentoColaborador(): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.pagamentoColaboradorService.buscarTodos().pipe(finalize(() => {
            this.blockUI.stop();
        })).subscribe(
            (data) => {
                this.pagamentoColaborador = data;
            }
        );
    }

    verificarTitulo(): string {
        return this.mensagemUtil.tituloModal(this.novo, EntityEnum.PAGAMENTO_COLABORADOR);
    }

    novoPagamento(): void {
        this.formPagamentoColaborador.formPagamentoColaborador.reset();
        this.display = true;
        this.novo = true;
    }

    public fecharModal(): void {
        this.display = false;
        if (this.formPagamentoColaborador.listarPagamento) {
            this.obterPagamentoColaborador();
            this.formPagamentoColaborador.listarPagamento = false;
        }
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
        this.pagamentoColaboradorService.deletar(id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.obterPagamentoColaborador();
            }))
            .subscribe(() => {this.mensagemUtil.mensagemSucesso(id, "", true)},
                    error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao Excluir Pagamento do Colaborador.\n') )
    }

    resetarForm(): void {
        this.formPagamentoColaborador.fecharForm();
    }

    carregar(idPagamento: number): void {
        this.display = true;
        this.novo = false;
        this.formPagamentoColaborador.editarPagamentoColaborador(idPagamento);
        this.formPagamentoColaborador.formPagamentoColaborador.enable();
    }

}
