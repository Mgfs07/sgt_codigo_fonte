import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {GastosService} from "../../../../shared/service/gastos.service";
import {GastoModel} from "../../../../model/gasto.model";
import {ValoresModel} from "../../../../model/valores.model";
import {FileUpload} from "primeng/fileupload";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";

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
    dataRegistro: Date = new Date();
    file: FileReader = new FileReader();
    formato = '';


    @Input() gastoList: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild('fileUpload') fileUpload: FileUpload;

  constructor(private fb: FormBuilder,
              private colaboradorService: ColaboradorService,
              private pagamentosService: PagamentosService,
              private gastoService: GastosService,
              private messageUtil: MensagensUtil) { }

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
        this.pagamentosService.buscarDropdown().subscribe(
            (data) => {
                this.pagamentoDrop = data;
                this.pagamentoRetirado = data;
                this.buscarValores();
            });
    }

    buscarColaborador(): void {
        this.colaboradorService.buscarDropdown().subscribe(
            (data) => {
                this.colaboradorDrop = data;
            });
    }

    buscarValores(): void {
      this.gastoService.valores().subscribe((data) => this.valoresGastos = data)
    }


    salvarFormulario(): void {
        this.novoPagamento = this.formGasto.getRawValue();
        this.gastoService.salvar(this.novoPagamento).pipe(finalize(() => {
            this.fecharForm();
            this.listarPagamento = true;
            this.buscarValores();
        })).subscribe(
            () => {
                if(this.novoPagamento.id){
                    this.messageUtil.mensagemSucesso('Gasto atualizado com sucesso', 'Sucesso')
                }else{
                    this.messageUtil.mensagemSucesso('Gasto cadastrado com sucesso', 'Sucesso')
                }
            }, error => this.messageUtil.mensagemErro(error.error.message, 'Falha ao salvar o gasto.\n'))
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
}
