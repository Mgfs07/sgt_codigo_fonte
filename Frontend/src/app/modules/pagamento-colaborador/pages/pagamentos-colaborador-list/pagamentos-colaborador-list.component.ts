import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {PagamentosColaboradoresService} from "../../../../shared/service/pagamentos-colaboradores.service";
import {ColunaModel} from "../../../../model/coluna.model";
import {PagamentoColaboradorModel} from "../../../../model/pagamento-colaborador.model";
import {PagamentoColaboradorComponent} from "../pagamento-colaborador/pagamento-colaborador.component";

@Component({
  selector: 'app-pagamentos-colaborador-list',
  templateUrl: './pagamentos-colaborador-list.component.html',
  styleUrls: ['./pagamentos-colaborador-list.component.scss']
})
export class PagamentosColaboradorListComponent implements OnInit {

    colunas: ColunaModel[] = [];
    pagamentoColaborador: PagamentoColaboradorModel[] = [];

    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(PagamentoColaboradorComponent) formPagamentoColaborador: PagamentoColaboradorComponent;



    constructor(
      private pagamentoColaboradorService: PagamentosColaboradoresService,
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

    isData(coluna: string): boolean {
        return coluna === 'dataPagamento';
    }

    isValor(coluna: string): boolean {
        return coluna == 'valorPago';
    }

    public obterPagamentoColaborador(): void {
        this.pagamentoColaboradorService.findAll().subscribe(
            (data) => {
                this.pagamentoColaborador = data;
            }
        );
    }

    editar(id: number): void {
        this.formPagamentoColaborador.editarPagamentoColaborador(id);
        this.display = true;
    }

    novoPagamento(): void {
        this.formPagamentoColaborador.formPagamentoColaborador.reset();
        this.display = true;
    }

    public fecharModal(): void {
        this.display = false;
        if (this.formPagamentoColaborador.listarPagamento) {
            this.obterPagamentoColaborador();
            this.formPagamentoColaborador.listarPagamento = false;
        }
    }

    resetarForm(): void {
        this.formPagamentoColaborador.fecharForm();
    }

    carregar(idPagamento: number): void {
        this.display = true;
        this.formPagamentoColaborador.editarPagamentoColaborador(idPagamento);
        this.formPagamentoColaborador.formPagamentoColaborador.enable();
    }

}
