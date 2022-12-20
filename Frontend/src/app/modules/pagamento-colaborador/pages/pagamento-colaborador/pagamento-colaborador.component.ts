import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {PagamentosColaboradoresService} from "../../../../shared/service/pagamentos-colaboradores.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {PagamentoColaboradorModel} from "../../../../model/pagamento-colaborador.model";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {finalize} from "rxjs";

@Component({
    selector: 'app-pagamento-colaborador',
    templateUrl: './pagamento-colaborador.component.html',
    styleUrls: ['./pagamento-colaborador.component.scss']
})
export class PagamentoColaboradorComponent implements OnInit {

    formPagamentoColaborador: FormGroup;
    novoPagamento: PagamentoColaboradorModel;
    listarPagamento: boolean = false;
    colaboradorDrop: SelectItem[];
    pagamentoDrop: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date();


    @Input() colaboradorModel: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild(Number) valor: number;
    @ViewChild(Boolean) visualizar: boolean;


    constructor(
        private pagamentoColaboradorService: PagamentosColaboradoresService,
        private fb: FormBuilder,
        private colaboradorService: ColaboradorService,
        private pagamentosService: PagamentosService,
        private mensagemUtil: MensagensUtil
    ) {
    }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarColaborador();
        this.buscarPagamentos();
    }

    novoFormulario(): void {
        this.formPagamentoColaborador = this.fb.group({
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
        this.pagamentosService.buscarDropdown().subscribe(
            (data) => {
                this.pagamentoDrop = data;
                this.pagamentoRetirado = data;
            });
    }

    buscarColaborador(): void {
        this.colaboradorService.buscarDropdown().subscribe(
            (data) => {
                this.colaboradorDrop = data;
            });
    }

    limparForm(): void {
        this.formPagamentoColaborador.reset();
    }

    salvarFormulario(): void {
        this.novoPagamento = this.formPagamentoColaborador.getRawValue();
            this.pagamentoColaboradorService.salvar(this.novoPagamento).pipe(finalize(() => {
                this.fecharForm();
                this.listarPagamento = true;
            })).subscribe(
                () => {
                    if(this.novoPagamento.id){
                        this.mensagemUtil.mensagemSucesso('Sucesso ao atualizar o pagamento', 'Sucesso');
                    }else {
                        this.mensagemUtil.mensagemSucesso('Sucesso ao cadastrar um pagamento', 'Sucesso')
                    }
                }, error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao salvar o pagamento.\n'))
    }


    fecharForm(): void {
        this.formPagamentoColaborador.reset();
        this.respForm.emit();
    }

    editarPagamentoColaborador(id: number): void {
        this.pagamentoColaboradorService.findById(id)
            .subscribe(response => {
                this.formPagamentoColaborador.patchValue(response);
            });
    }

    abrirGrid(visualizar: boolean){
        if(visualizar){
            return this.visualizar = false;
        }
        return this.visualizar = true;
    }
}
