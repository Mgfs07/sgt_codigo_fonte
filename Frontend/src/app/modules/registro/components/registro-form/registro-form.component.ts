import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ClienteService} from '../../../../shared/service/cliente.service';
import {BlockUIService} from 'ng-block-ui';
import {ImpactoService} from '../../../../shared/service/impacto.service';
import {PrioridadeService} from '../../../../shared/service/prioridade.service';
import {StatusAcaoService} from '../../../../shared/service/status-acao.service';
import {MessageService, SelectItem} from 'primeng';
import {StatusRegistroService} from '../../../../shared/service/status-registro.service';
import {TiposRegistroService} from '../../../../shared/service/tipos-registro.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RegistroModel} from '../../../../model/registro.model';
import {RegistroService} from '../../../../shared/service/registro.service';
import {TabsModel} from '../../../../model/tabs.model';

@Component({
    selector: 'app-registro-form',
    templateUrl: './registro-form.component.html',
    styleUrls: ['./registro-form.component.css']
})
export class RegistroFormComponent implements OnInit {

    prioridade: SelectItem[];
    impacto: SelectItem[];
    tipoRegistro: SelectItem[];
    cliente: SelectItem[];
    statusRegistro: SelectItem[];
    formRegistro: FormGroup;
    registroNovo: RegistroModel = new RegistroModel;
    desabilitarDataLimite: Boolean = true;
    idCliente: number;


    @Output() aoRegistrar: EventEmitter<TabsModel> = new EventEmitter();
    @Input() idRegistro: number;
    @Input() editing;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;

    constructor(private clienteService: ClienteService,
                private blockUI: BlockUIService,
                private impactoService: ImpactoService,
                private prioridadeService: PrioridadeService,
                private statusAcaoService: StatusAcaoService,
                private tipoRegistroService: TiposRegistroService,
                private statusRegistroService: StatusRegistroService,
                private formBuilder: FormBuilder,
                private registroService: RegistroService,
                private messageService: MessageService) {
    }

    ngOnInit(): void {
        this.buscarDropDownRegistro();
        this.verificarNovo();
        this.novoFormulario();
    }

    novoFormulario(): void {
        this.formRegistro = this.formBuilder.group({
            id: [null],
            idTipoRegistro: [null, [Validators.required]],
            idImpacto: [null, [Validators.required]],
            idStatusRegistro: [null, [Validators.required]],
            idPrioridade: [null, [Validators.required]],
            idCliente: [null, [Validators.required]],
            titulo: [null, [Validators.required]],
            descricao: [null, [Validators.required]],
            dataInicio: [null, [Validators.required]],
            dataLimite: [null, [Validators.required]]
        });
    }

    salvarFormulario(): void {
        this.registroNovo = this.formRegistro.getRawValue();
        this.registroService.postRegistro(this.registroNovo).subscribe(
            (response: RegistroModel) => {
                this.showSuccess('Registro Criado com Sucesso');
                this.idCliente = response.idCliente;
                this.aoRegistrar.emit(new TabsModel(null, null, response.id, response.idCliente));
            },
            error => this.showError(this.pegarSubstring(error.error.detail)));
    }

    verificarNovo(): void {
        if (this.idRegistro) {
            return this.abrirRegistro();
        }
        this.novoFormulario();
    }

    abrirRegistro(): void {
        this.registroService.getRegitroId(this.idRegistro).subscribe(
            (response: RegistroModel) => {
                response.dataInicio = new Date(response.dataInicio);
                response.dataLimite = new Date(response.dataLimite);
                this.formRegistro.patchValue(response);
                this.aoRegistrar.emit(new TabsModel(null, null, response.id, response.idCliente));
                if (!this.editing) {
                    this.formRegistro.disable();
                }
            });
    }

    buscarDropDownRegistro() {
        this.registroService.getDropDownRegistro().subscribe(response => {
            this.cliente = response.cliente;
            this.tipoRegistro = response.tipoRegistro;
            this.impacto = response.impacto;
            this.statusRegistro = response.statusRegistro;
            this.prioridade = response.prioridade;
        });
    }

    pegarSubstring(message: string): string {
        return message.substr(message.indexOf(' "'));
    }

    showSuccess(message: string): void {
        this.messageService.add({severity: 'success', summary: 'Success Message', detail: message});
    }

    showError(message: string): void {
        this.messageService.add({severity: 'error', summary: 'Falha ao Salvar Cliente', detail: message});
    }

    habilitarDataLimite(): void {
        this.desabilitarDataLimite = false;
    }

}
