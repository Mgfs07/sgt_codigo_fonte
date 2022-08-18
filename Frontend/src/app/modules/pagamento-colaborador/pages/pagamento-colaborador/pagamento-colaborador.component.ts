import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {PagamentosColaboradoresService} from "../../../../shared/service/pagamentos-colaboradores.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {PagamentoColaboradorModel} from "../../../../model/pagamento-colaborador.model";

@Component({
    selector: 'app-pagamento-colaborador',
    templateUrl: './pagamento-colaborador.component.html',
    styleUrls: ['./pagamento-colaborador.component.scss']
})
export class PagamentoColaboradorComponent implements OnInit {

    formPagamento: FormGroup;
    novoPagamento: PagamentoColaboradorModel;
    listarPagamento: boolean = false;
    colaboradorDrop: SelectItem[];
    pagamentoDrop: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date()


    @Input() colaboradorModel: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild("valor") valor: number;
    @ViewChild('vizualizar') vizualizar: boolean;


    constructor(
        private pagamentoColaboradorService: PagamentosColaboradoresService,
        private fb: FormBuilder,
        private colaboradorService: ColaboradorService,
        private pagamentosService: PagamentosService,
    ) {
    }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarColaborador();
        this.buscarPagamentos();
    }

    novoFormulario(): void {
        this.formPagamento = this.fb.group({
            id: [null],
            idColaborador: ['', [Validators.required]],
            idPagamento: ['', [Validators.required]],
            observacao: ['', [Validators.required]],
            retiradoLugar: ['', [Validators.required]],
            pagamentosRetirado: ['', [Validators.required]],
            dataPagamento: [null, [Validators.required]],
            valorPago: ['', [Validators.required]],
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

    saveteste(): void {
        console.log(this.novoPagamento = this.formPagamento.getRawValue())

    }

    salvarFormulario(): void {
        this.novoPagamento = this.formPagamento.getRawValue();
        this.pagamentoColaboradorService.salvar(this.novoPagamento).subscribe(
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
        this.pagamentoColaboradorService.findById(id)
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
