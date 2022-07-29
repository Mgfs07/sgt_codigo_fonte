import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MessageService, SelectItem} from 'primeng';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProjetoService} from '../../../../shared/service/projeto.service';
import {ProjetoModel} from '../../../../model/projeto.model';
import {Observable} from 'rxjs';
import {ClienteService} from '../../../../shared/service/cliente.service';

@Component({
    selector: 'app-projeto-form',
    templateUrl: './projeto-form.component.html',
    styleUrls: ['./projeto-form.component.css']
})
export class ProjetoFormComponent implements OnInit {

    projetos: SelectItem[];
    public formProjeto: FormGroup;
    read: boolean;


    @Output() alterarDisplay: EventEmitter<boolean> = new EventEmitter;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;

    constructor(
        private fbProjeto: FormBuilder,
        private projetoService: ProjetoService,
        private clienteService: ClienteService,
        private messageService: MessageService
    ) {
    }

    ngOnInit(): void {
        this.buscarClientes();
        this.formProjeto = this.fbProjeto.group({
            id: [null],
            nome: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(55)]],
            descricao: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
            idCliente: ['', [Validators.required]]
        });
    }

    carregarProjeto(id: number): void {
        this.buscarClientes();
        this.projetoService.getProjetoById(id).subscribe(res => this.formProjeto.patchValue(res));
    }

    verificaId(): void {
        if (!this.formProjeto.get('id').value) {
            this.salvarProjeto();
            return;
        }
        this.atualizarProjeto();
    }

    salvarProjeto(): void {
        this.finalizarRequisicao(this.projetoService.postProjetos(this.formProjeto.getRawValue()));
        this.msgSucessoAoCriar();
    }

    atualizarProjeto(): void {
        this.finalizarRequisicao(this.projetoService.putProjetos(this.formProjeto.getRawValue()));
        this.msgSucessoAoEditar();
    }

    finalizarRequisicao(observable: Observable<ProjetoModel>): void {
        observable.subscribe(() => {
            this.alterarDisplay.emit(false);
            this.recarregarListagem.emit();
        });
    }

    cancelarCadastro(): void {
        this.alterarDisplay.emit(false);
    }

    buscarClientes() {
        this.clienteService.getClientes().subscribe(
            (data) => this.projetos = data);
    }

    msgSucessoAoEditar(): void {
        this.messageService.add({severity: 'success', summary: 'Projeto editado com sucesso!', detail: ''});
    }
    msgSucessoAoCriar(): void {
        this.messageService.add({severity: 'success', summary: 'Projeto criado com sucesso!', detail: ''});
    }

}
