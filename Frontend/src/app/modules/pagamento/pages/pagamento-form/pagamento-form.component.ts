import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {finalize} from "rxjs";
import {PagamentoModel} from "../../../../model/pagamento.model";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
    selector: 'app-pagamento-form',
    templateUrl: './pagamento-form.component.html',
    styleUrls: ['./pagamento-form.component.scss']
})
export class PagamentoFormComponent implements OnInit {

    formPagamento: FormGroup;
    pagamento: PagamentoModel;
    listarPagamento: boolean = false;


    @BlockUI() blockUI : NgBlockUI;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();

    constructor(private fb: FormBuilder,
                private pagamentosService: PagamentosService,
                private mensagemUtil: MensagensUtil) {
    }

    ngOnInit(): void {
        this.novoFormulario();
    }


    novoFormulario(): void {
        this.formPagamento = this.fb.group({
            id: [null],
            nomePagamento: ['', [Validators.required]],
            valorMeta: ['', [Validators.required]],
        });
    }


    salvarFormulario(): void {
        this.pagamento = this.formPagamento.getRawValue();
        this.pagamentosService.salvar(this.pagamento).pipe(finalize(() => {
            this.fecharForm();
            this.listarPagamento = true;
        })).subscribe(
            () => {
                this.mensagemUtil.mensagemSucesso(this.pagamento.id, "", false, EntityEnum.PAGAMENTO)
            }, error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao salvar o Pagamento.\n'))
    }

    fecharForm(): void {
        this.formPagamento.reset();
        this.respForm.emit();
    }

    editarGasto(id: number): void {
        this.pagamentosService.findById(id)
            .subscribe(response => {
                this.formPagamento.patchValue(response);
            });
    }

}
