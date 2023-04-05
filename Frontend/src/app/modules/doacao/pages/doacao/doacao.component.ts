import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {DoacoesService} from "../../../../shared/service/doacoes.service";
import {DoacaoModel} from "../../../../model/doacao.model";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Mensagens} from "../../../../shared/utils/mensagens";

@Component({
  selector: 'app-doacao',
  templateUrl: './doacao.component.html',
  styleUrls: ['./doacao.component.scss']
})
export class DoacaoComponent implements OnInit {

    formularioPagamento: FormGroup;
    doacao: DoacaoModel;
    listarPagamento: boolean = false;
    pagamentoDropdown: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date()

    @BlockUI() blockUI : NgBlockUI;
    @Input() doacaoList: any;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();

    constructor(private fb: FormBuilder,
                private pagamentosService: PagamentosService,
                private doacoesService: DoacoesService,
                private mensagemUtil: MensagensUtil) { }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarPagamentos();
    }

    novoFormulario(): void {
        this.formularioPagamento = this.fb.group({
            id: [null],
            nomeDoador: ['', [Validators.required]],
            observacao: ['', [Validators.required]],
            doadoParaPagamento: ['', [Validators.required]],
            valorDoado: ['', [Validators.required]],
            dataDoacao: ['', [Validators.required]],
        });
    }

    buscarPagamentos(): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.pagamentosService.buscarDropdown().pipe(finalize(() => {
            this.blockUI.stop();
        })).subscribe(
            (data) => {
                this.pagamentoDropdown = data;
                this.pagamentoRetirado = data;
            });
    }

    salvarFormulario(): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.doacao = this.formularioPagamento.getRawValue();
        this.doacoesService.salvar(this.doacao).pipe(finalize(() => {
            this.blockUI.stop();
            this.fecharForm();
            this.listarPagamento = true;
        }))
            .subscribe(
            () => {
                this.mensagemUtil.mensagemSucesso(this.doacao.id, "", false, EntityEnum.DOACAO)
            }, error => this.mensagemUtil.mensagemErro(error.error.message, "Falha ao salvar a Doacao.\n")
            );
    }


    fecharForm(): void {
        this.formularioPagamento.reset();
        this.respForm.emit();
    }

    editarPagamento(id: number): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.doacoesService.findById(id).pipe(finalize(() => {
            this.blockUI.stop();
        })).subscribe(response => {
                response.dataDoacao = new Date(response.dataDoacao + 'T00:00');
                this.formularioPagamento.patchValue(response);
            });
    }

}
