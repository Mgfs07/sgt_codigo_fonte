import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ColunaModel} from "../../../model/coluna.model";
import {Table} from "primeng/table";
import {ConversoesUtil} from "../../utils/conversoes.util";

@Component({
  selector: 'app-tabela-generica',
  templateUrl: './tabela-generica.component.html',
  styleUrls: ['./tabela-generica.component.scss']
})
export class TabelaGenericaComponent implements OnInit {


  @Input() colunas: ColunaModel[] = [];
  @Input() dados: any;
  @Input() rows: number;
  @Input() paginator: boolean;
  @Input() titulo: string;


  @Output() public abrirModal: EventEmitter<number> = new EventEmitter<number>();
  @Output() public deletar: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }


    isDadosComuns(coluna: string): boolean {
      return !this.isData(coluna) && !this.isValor(coluna) && !this.isSituacao(coluna);
    }

    isData(coluna: string): boolean {
        return coluna === 'dataPagamento' || coluna === 'dataDispesa' || coluna == 'dataDoacao';
    }

    isValor(coluna: string): boolean {
        return coluna == 'valorPago' || coluna == 'valorRetirado'
            || coluna == 'valorDoado' || coluna == 'quantoFalta' || coluna == 'valorMeta';
    }

    isSituacao(coluna: string): boolean {
      return coluna == 'ativo';
    }

    alterarNomeSituacao(situacao: boolean): string {
      return situacao ? 'ATIVO' : 'INATIVO';
    }

    modificarValor(valor: number): string {
        return ConversoesUtil.numberToCurrency(valor);
    }

    nomesColunas(): string[] {
      let coluFitro: Array<string> = new Array<string>();
      this.colunas.forEach(co => coluFitro.push(co.field.toString()));
      return coluFitro;
    }

    filter($event: any): string {
      return $event.target.value;
    }

    clear(table: Table) {
        table.clear();
    }

}
