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
import {ConversoesUtil} from "../../../../shared/utils/conversoes.util";
import {ColunaModel} from "../../../../model/coluna.model";

@Component({
    selector: 'app-colaborador-form',
    templateUrl: './colaborador-form.component.html',
    styleUrls: ['./colaborador-form.component.scss']
})
export class ColaboradorFormComponent implements OnInit {

    formularioColaborador: FormGroup;
    novoColaborador: ColaboradorModel;
    listarColaborador: boolean = false;
    dropdownUnidade: SelectItem[];
    pagamentosColaborador: MetaModel[];
    coluna: ColunaModel[];

    @Input() colaboradorList: ColaboradoresListModel[];
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();


    constructor(
        private messageService: MessageService,
        private fb: FormBuilder,
        private colaboradorService: ColaboradorService,
        private unidadeService: UnidadeService,
        private messageUtil: MensagensUtil,
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
        this.colaboradorService.findById(idColaborador).subscribe((response) => {
            this.buscarUnidade();
            this.buscarPagamentosColaborador(response.id);
            this.formularioColaborador.patchValue(response);
            console.log(this.pagamentosColaborador);
        })
    }

    public buscarPagamentosColaborador(id: number): void {
        this.pagamentoColaboradorService.buscarPagamentosDoColaborador(id).subscribe(
            (response) => this.pagamentosColaborador = response)
    }

    public salvar(): void {
        this.novoColaborador = this.formularioColaborador.getRawValue();
        this.colaboradorService.salvar(this.novoColaborador)
            .pipe(finalize(() => {
                this.fecharForm();
                this.listarColaborador = true;
            }))
            .subscribe(
                () => {
                    if (this.novoColaborador.id) {
                        this.messageUtil.mensagemSucesso('Colaborador atualizado com sucesso', 'Sucesso')
                    } else {
                        this.messageUtil.mensagemSucesso('Colaborador cadastrado com sucesso', 'Sucesso')
                    }
                }, (error) => this.messageUtil.mensagemErro(error.error.message, 'Falha ao salvar Colaborador.\n')

            );
    }


    fecharForm(): void {
        this.formularioColaborador.reset();
        this.respForm.emit();
    }

    buscarUnidade(): void {
        this.unidadeService.buscarDropdown().subscribe((response) => {
            this.dropdownUnidade = response;
        })
    }

    modificarValor(valor: number): string {
        return ConversoesUtil.numberToCurrency(valor);
    }

}
