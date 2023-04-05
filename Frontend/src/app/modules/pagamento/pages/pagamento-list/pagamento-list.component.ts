import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColunaModel} from "../../../../model/coluna.model";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {PagamentoFormComponent} from "../pagamento-form/pagamento-form.component";
import {PagamentoModel} from "../../../../model/pagamento.model";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {ConfirmationService} from "primeng/api";
import {Mensagens} from "../../../../shared/utils/mensagens";
import {finalize} from "rxjs";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'app-pagamento-list',
  templateUrl: './pagamento-list.component.html',
  styleUrls: ['./pagamento-list.component.scss']
})
export class PagamentoListComponent implements OnInit {

    colunas: ColunaModel[] = [];
    pagamentoList: PagamentoModel[] = [];
    novo: boolean

    @BlockUI() blockUI : NgBlockUI;
    @Input() display = false;
    @ViewChild(PagamentoFormComponent) formPagamento: PagamentoFormComponent;


    constructor(private pagamentoService: PagamentosService,
                private mensagemUtil: MensagensUtil,
                private confirmationService: ConfirmationService
                ) { }

    ngOnInit(): void {
        this.columnsTable();
        this.obterPagamentos();
    }

    public columnsTable() {
        this.colunas = [
            new ColunaModel('nomePagamento', 'Pagamento'),
            new ColunaModel('valorMeta', 'Valor da Meta'),
            new ColunaModel('ativo', 'Situacao Pagamento'),
            new ColunaModel('acoes', 'Ações', '5%' )
        ];
    }

    public obterPagamentos(): void {
        this.pagamentoService.buscarTodos().subscribe(
            (data) => {
                this.pagamentoList = data;
            }
        );
    }

    verificarTitulo(): string {
        return this.mensagemUtil.tituloModal(this.novo, EntityEnum.PAGAMENTO);
    }

    novoPagamento(): void {
        this.formPagamento.formPagamento.reset();
        this.display = true;
        this.novo = true;
    }

    public fecharModal(): void {
        this.display = false;
        if (this.formPagamento.listarPagamento) {
            this.obterPagamentos();
            this.formPagamento.listarPagamento = false;
        }
    }

    resetarForm(): void {
        this.formPagamento.fecharForm();
    }

    carregar(idPagamento: number): void {
        this.display = true;
        this.novo = false;
        this.formPagamento.editarGasto(idPagamento);
        this.formPagamento.formPagamento.enable();
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
        this.pagamentoService.deletar(id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.obterPagamentos();
            }))
            .subscribe(() => {this.mensagemUtil.mensagemSucesso(id, "", true)},
                    error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao Excluir Pagamento.\n') )
    }

}
