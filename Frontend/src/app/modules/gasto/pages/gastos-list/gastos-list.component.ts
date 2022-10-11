import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColunaModel} from "../../../../model/coluna.model";
import {GastosService} from "../../../../shared/service/gastos.service";
import {GastoListModel} from "../../../../model/gasto-list.model";
import {GastosComponent} from "../gastos/gastos.component";

@Component({
  selector: 'app-gastos-list',
  templateUrl: './gastos-list.component.html',
  styleUrls: ['./gastos-list.component.scss']
})
export class GastosListComponent implements OnInit {


    colunas: ColunaModel[] = [];
    gastoList: GastoListModel[] = [];

    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(GastosComponent) formGasto: GastosComponent;


  constructor(private gastoService: GastosService) { }

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
            new ColunaModel('acoes', 'Ações', '10%' )
        ];
    }

    isData(coluna: string): boolean {
        return coluna === 'dataDispesa';
    }

    isValor(coluna: string): boolean {
      return coluna == 'valorRetirado';
    }

    public obterPagamentoColaborador(): void {
        this.gastoService.findAll().subscribe(
            (data) => {
                this.gastoList = data;
            }
        );
    }

    editar(id: number): void {
        this.formGasto.editarGasto(id);
        this.display = true;
    }

    novoPagamento(): void {
        this.formGasto.formGasto.reset();
        this.display = true;
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

    carregar(idPagamento: number): void {
        this.display = true;
        this.formGasto.editarGasto(idPagamento);
        this.formGasto.formGasto.enable();
    }

}
