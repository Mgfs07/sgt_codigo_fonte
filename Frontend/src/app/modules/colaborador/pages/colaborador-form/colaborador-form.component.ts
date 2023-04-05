import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ColaboradorModel} from "../../../../model/colaborador.model";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {UnidadeService} from "../../../../shared/service/unidade.service";
import {MessageService, SelectItem} from "primeng/api";
import {ColaboradoresListModel} from "../../../../model/colaboradores-list.model";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {MetaModel} from "../../../../model/meta.model";
import {PagamentosColaboradoresService} from "../../../../shared/service/pagamentos-colaboradores.service";
import {ColunaModel} from "../../../../model/coluna.model";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {Mensagens} from "../../../../shared/utils/mensagens";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
    selector: 'app-colaborador-form',
    templateUrl: './colaborador-form.component.html',
    styleUrls: ['./colaborador-form.component.scss']
})
export class ColaboradorFormComponent implements OnInit {

    formularioColaborador: FormGroup;
    colaborador: ColaboradorModel;
    listarColaborador: boolean = false;
    dropdownUnidade: SelectItem[];
    pagamentosColaborador: MetaModel[];
    coluna: ColunaModel[];
    mostrarGastosColaborador: boolean = false;

    @BlockUI() blockUI : NgBlockUI;
    @Input() colaboradorList: ColaboradoresListModel[];
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();


    constructor(
        private messageService: MessageService,
        private fb: FormBuilder,
        private colaboradorService: ColaboradorService,
        private unidadeService: UnidadeService,
        private mensagemUtil: MensagensUtil,
        private pagamentoColaboradorService: PagamentosColaboradoresService
    ) {
    }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarUnidade();
        this.columnsTable();
    }


    public columnsTable() {
        this.coluna = [
            new ColunaModel('nomePagamento', 'Pagamento'),
            new ColunaModel('valorPago', 'Valor Pago'),
            new ColunaModel('quantoFalta', 'Quanto Falta')
        ];
    }


    public novoFormulario() {
        this.formularioColaborador = this.fb.group({
            id: [null],
            nomeColaborador: ['', [Validators.required]],
            idUnidade: ['', [Validators.required]],
            telefone: ['', [Validators.required]],
            email: ['', [Validators.required, Validators.email]]
        })
    }


    public editar(idColaborador: number): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.mostrarGastosColaborador = true;
        this.colaboradorService.findById(idColaborador).pipe(finalize(() => {
            this.buscarUnidade();
        })).subscribe((response) => {
            this.buscarPagamentosColaborador(response.id);
            this.formularioColaborador.patchValue(response);
        })
    }

    public buscarPagamentosColaborador(id: number): void {
        this.pagamentoColaboradorService.buscarPagamentosDoColaborador(id).subscribe(
            (response) => this.pagamentosColaborador = response)
    }

    public salvar(): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.colaborador = this.formularioColaborador.getRawValue();
        this.colaboradorService.salvar(this.colaborador)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.fecharForm();
                this.listarColaborador = true;
            }))
            .subscribe(
                () => {
                    this.mensagemUtil.mensagemSucesso(this.colaborador.id, "", false, EntityEnum.COLABORADOR)
                }, (error) => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao salvar Colaborador.\n')
            );
    }


    fecharForm(): void {
        this.mostrarGastosColaborador = false;
        this.formularioColaborador.reset();
        this.respForm.emit();
    }

    buscarUnidade(): void {
        this.unidadeService.buscarDropdown().pipe(finalize(() => {
            this.blockUI.stop();
        })).subscribe((response) => {
            this.dropdownUnidade = response;
        })
    }
}
