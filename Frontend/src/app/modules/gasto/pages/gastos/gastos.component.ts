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

    formGasto: FormGroup;
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
  }

    novoFormulario(): void {
        this.formGasto = this.fb.group({
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
                this.buscarValores();
            });
    }

    buscarColaborador(): void {
        this.colaboradorService.findAllDropDown().subscribe(
            (data) => {
                this.colaboradorDrop = data;
            });
    }

    limparForm(): void {
        this.formGasto.reset();
    }

    buscarValores(): void {
      this.gastoService.valores().subscribe((data) => this.valoresGastos = data)
    }

    saveteste(): void {
        console.log(this.novoPagamento = this.formGasto.getRawValue())

    }

    salvarFormulario(): void {
        this.novoPagamento = this.formGasto.getRawValue();
        this.gastoService.salvar(this.novoPagamento).subscribe(
            () => {
                this.fecharForm();
                this.listarPagamento = true
                this.buscarValores();
            }, error => console.log(error))
    }

    fecharForm(): void {
        this.formGasto.reset();
        this.buscarValores();
        this.respForm.emit();
    }

    editarGasto(id: number): void {
        this.gastoService.findById(id)
            .subscribe(response => {
                response.dataDispesa = new Date(response.dataDispesa + 'T00:00');
                this.formGasto.patchValue(response);
            });
    }

    abrirGrid(visualizar: boolean){
        if(visualizar){
            return this.vizualizar = false;
        }
        return this.vizualizar = true;
    }

}
