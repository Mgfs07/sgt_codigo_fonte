import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ColunaModel} from "../../../model/coluna.model";

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

  @Output() public abrirModal: EventEmitter<number> = new EventEmitter<number>();
  @Output() public deletar: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

    isData(coluna: string): boolean {
        return coluna === 'dataPagamento';
    }

    isValor(coluna: string): boolean {
        return coluna == 'valorPago';
    }

}
