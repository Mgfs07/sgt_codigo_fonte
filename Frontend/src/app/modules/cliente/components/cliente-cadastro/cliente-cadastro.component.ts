import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ClienteService} from '../../../../shared/service/cliente.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from 'primeng';
import {ClienteModel} from '../../../../model/cliente.model';
import {DomSanitizer} from '@angular/platform-browser';
import {finalize} from 'rxjs/operators';
import {BlockUIService} from 'ng-block-ui';

@Component({
    selector: 'app-cliente-cadastro',
    templateUrl: './cliente-cadastro.component.html',
    styleUrls: ['./cliente-cadastro.component.css']
})
export class ClienteCadastroComponent implements OnInit {

    formulario: FormGroup;
    clienteNovo: ClienteModel = new ClienteModel;
    file: FileReader = new FileReader();
    formato = '';
    exibirCadastroImagem: boolean;

    @Input() cliente;
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();
    @ViewChild('fileUpload') fileUpload: any;

    constructor(
        private clienteService: ClienteService,
        private fb: FormBuilder,
        private domSanitizer: DomSanitizer,
        private messageService: MessageService,
        private blockUI: BlockUIService) {

    }

    ngOnInit(): void {
        this.novoFormulario();
    }

    novoFormulario(): void {
        this.exibirCadastroImagem = true;
        this.formulario = this.fb.group({
            id: [null],
            nome: [null, [Validators.required]],
            descricao: [null, [Validators.required]],
            icone: [null, [Validators.required]],
        });
    }

    salvarCliente(): void {
        if (this.formulario.valid) {
            this.clienteNovo = this.formulario.getRawValue();
            this.clienteService.salvar(this.clienteNovo).subscribe(
                () => this.showSuccess('Cliente criado com sucesso'),
                error => this.showError(this.pegarSubstring(error.error.detail)));
        }
    }

    pegarSubstring(message: string): string {
        return message.substr(message.indexOf(' "'));
    }

    showSuccess(message: string): void {
        this.messageService.add({severity: 'success', summary: 'Success Message', detail: message});
        this.fecharForm();
    }

    showError(message: string): void {
        this.messageService.add({severity: 'error', summary: 'Falha ao Salvar Cliente', detail: message});
    }

    fecharForm(): void {
        this.formulario.reset();
        this.respForm.emit();
        this.fileUpload.clear();
    }

    converterFotoParaString(): void {
        this.formulario.get('icone').setValue(this.formato + btoa(this.file.result.toString()));
    }

    subirImagem(event: Record<string, any> ): void {
        this.formulario.get('icone').setValue(event.currentFiles[0].objectURL);
        this.file.readAsBinaryString(event.currentFiles[0]);
        this.file.onload = () => this.converterFotoParaString();
        this.formato = 'data:' + event.files[0].type + ';base64,';
    }

    editarCliente(id: number): void {
        this.blockUI.start('Buscando Cliente');
        this.clienteService.buscarPorId(id).pipe(finalize(() => this.blockUI.stop('Cliente Carregado')))
            .subscribe(response => {
                this.formulario.patchValue(response);
            });
    }

    mostraImagem(): string {
        return this.formulario.get('icone').value;
    }
}
