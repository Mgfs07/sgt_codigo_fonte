import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {GastosService} from "../../../../shared/service/gastos.service";
import {GastoModel} from "../../../../model/gasto.model";
import {ValoresModel} from "../../../../model/valores.model";

@Component({
  selector: 'app-gastos',
  templateUrl: './gastos.component.html',
  styleUrls: ['./gastos.component.scss']
})
export class GastosComponent implements OnInit {

    formPagamento: FormGroup;
    novoPagamento: GastoModel;
    valoresGastos: ValoresModel;
    listarPagamento: boolean = false;
    colaboradorDrop: SelectItem[];
    pagamentoDrop: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date()


    @Input() gastoList: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild("valor") valor: number;
    @ViewChild('vizualizar') vizualizar: boolean;

  constructor(private fb: FormBuilder,
              private colaboradorService: ColaboradorService,
              private pagamentosService: PagamentosService,
              private gastoService: GastosService) { }

  ngOnInit(): void {
      this.novoFormulario();
      this.buscarColaborador();
      this.buscarPagamentos();
      this.buscarValores();
  }

    novoFormulario(): void {
        this.formPagamento = this.fb.group({
            id: [null],
            motivo: ['', [Validators.required]],
            descricao: ['', [Validators.required]],
            idColaborador: ['', [Validators.required]],
            dataDispesa: ['', [Validators.required]],
            valorRetirado: ['', [Validators.required]],
            comprovante: [null, [Validators.required]],
            retiradoDoPagamento: ['', [Validators.required]],
        });
    }

    buscarPagamentos(): void {
        this.pagamentosService.findAllDropDown().subscribe(
            (data) => {
                this.pagamentoDrop = data;
                this.pagamentoRetirado = data;
            });
    }

    buscarColaborador(): void {
        this.colaboradorService.findAllDropDown().subscribe(
            (data) => {
                this.colaboradorDrop = data;
            });
    }

    limparForm(): void {
        this.formPagamento.reset();
    }

    buscarValores(): void {
      this.gastoService.valores().subscribe((data) => this.valoresGastos = data)
    }

    saveteste(): void {
        console.log(this.novoPagamento = this.formPagamento.getRawValue())

    }

    salvarFormulario(): void {
        this.novoPagamento = this.formPagamento.getRawValue();
        this.gastoService.salvar(this.novoPagamento).subscribe(
            () => {
                this.fecharForm();
                this.listarPagamento = true
            }, error => console.log(error))
    }

    fecharForm(): void {
        this.formPagamento.reset();
        this.respForm.emit();
    }

    editarPagamento(id: number): void {
        this.gastoService.findById(id)
            .subscribe(response => {
                this.formPagamento.patchValue(response);
            });
    }

    abrirGrid(visualizar: boolean){
        if(visualizar){
            return this.vizualizar = false;
        }
        return this.vizualizar = true;
    }

}
