import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {DoacoesService} from "../../../../shared/service/doacoes.service";
import {DoacaoModel} from "../../../../model/doacao.model";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";

@Component({
  selector: 'app-doacao',
  templateUrl: './doacao.component.html',
  styleUrls: ['./doacao.component.scss']
})
export class DoacaoComponent implements OnInit {

    formPagamento: FormGroup;
    novoPagamento: DoacaoModel;
    listarPagamento: boolean = false;
    pagamentoDrop: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date()


    @Input() doacaoList: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild('valor') valor: number;

    constructor(private fb: FormBuilder,
                private pagamentosService: PagamentosService,
                private doacoesService: DoacoesService,
                private messagemService: MensagensUtil) { }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarPagamentos();
    }

    novoFormulario(): void {
        this.formPagamento = this.fb.group({
            id: [null],
            nomeDoador: ['', [Validators.required]],
            observacao: ['', [Validators.required]],
            doadoParaPagamento: ['', [Validators.required]],
            valorDoado: ['', [Validators.required]],
            dataDoacao: ['', [Validators.required]],
        });
    }

    buscarPagamentos(): void {
        this.pagamentosService.buscarDropdown().subscribe(
            (data) => {
                this.pagamentoDrop = data;
                this.pagamentoRetirado = data;
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
        this.doacoesService.salvar(this.novoPagamento).pipe(finalize(() => {
            this.fecharForm();
            this.listarPagamento = true;
        }))
            .subscribe(
            () => {
                if(this.novoPagamento.id){
                    this.messagemService.mensagemSucesso('Doacao atualizado com sucesso', 'Sucesso');
                }else {
                    this.messagemService.mensagemSucesso('Doacao cadastrada com sucesso','Sucesso' );
                }
            }, error => this.messagemService.mensagemErro(error.error.message, "Falha ao salvar a Doacao.\n")
            );
    }


    fecharForm(): void {
        this.formPagamento.reset();
        this.respForm.emit();
    }

    editarPagamento(id: number): void {
        this.doacoesService.findById(id)
            .subscribe(response => {
                response.dataDoacao = new Date(response.dataDoacao + 'T00:00');
                this.formPagamento.patchValue(response);
            });
    }

}
