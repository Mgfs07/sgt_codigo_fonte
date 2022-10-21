import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ColaboradorModel} from "../../../../model/colaborador.model";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {UnidadeService} from "../../../../shared/service/unidade.service";
import {MessageService, SelectItem} from "primeng/api";
import {ColaboradoresListModel} from "../../../../model/colaboradores-list.model";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";

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

    @Input() colaboradorList: ColaboradoresListModel[];
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();


    constructor(
        private messageService: MessageService,
        private fb: FormBuilder,
        private colaboradorService: ColaboradorService,
        private unidadeService: UnidadeService,
        private messageUtil: MensagensUtil
    ) {
    }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarUnidade();
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
            this.formularioColaborador.patchValue(response);
        })
    }

    public salvar(): void {
        this.novoColaborador = this.formularioColaborador.getRawValue();
        console.log(this.novoColaborador);
        this.colaboradorService.salvar(this.novoColaborador)
            .pipe(finalize(() => this.fecharForm()))
            .subscribe(
                () => {
                    if (this.novoColaborador.id) {
                        this.messageUtil.mensagemSucesso('Colaborador cadastrado com sucesso', 'Sucesso')
                    } else {
                        this.messageUtil.mensagemSucesso('Colaborador atualizado com sucesso', 'Sucesso')
                    }
                }, (error) => {
                    console.log(error);
                    // this.messageUtil.mensagemErro('Falha ao cadastrar.\n' + error.error, 'error')
                }
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


}
