import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ClienteService} from '../../../../shared/service/cliente.service';
import {BlockUIService} from 'ng-block-ui';
import {ConfirmationService, MessageService} from 'primeng';
import {finalize} from 'rxjs/operators';
import {ClienteModel} from '../../../../model/cliente.model';
import {ClienteCadastroComponent} from '../cliente-cadastro/cliente-cadastro.component';
import {StatusRegistroService} from '../../../../shared/service/status-registro.service';
import {TituloModalEnum} from '../../../../model/titulo-modal-enum.model';

@Component({
    selector: 'app-cliente-lista',
    templateUrl: './cliente-lista.component.html',
    styleUrls: ['./cliente-lista.component.css']
})
export class ClienteListaComponent implements OnInit {

    clientes: ClienteModel[] = [];
    tituloModal: string;

    @Input() display = false;
    @ViewChild(ClienteCadastroComponent) formularioCliente: ClienteCadastroComponent;

    constructor(private clienteService: ClienteService,
                private statusRegistroService: StatusRegistroService,
                private blockUI: BlockUIService,
                private confirmationService: ConfirmationService,
                private messageService: MessageService) {
    }

    ngOnInit(): void {
        this.listarCliente();
    }

    listarCliente(): void {
        this.blockUI.start('Carregando os Clientes');
        this.clienteService.buscarTodos().pipe(finalize(() => this.blockUI.stop('Concluído')))
            .subscribe((response) => {
                    if (response) {
                        this.clientes = response;
                    }
                }, () => this.messageService.add({severity: 'error', summary: 'Erro', detail: 'Erro ao listar os Clientes'})
            );
    }

    deletarCliente(id: number): void {
        this.blockUI.start('Deletando Cliente');
        this.clienteService.deletar(id).pipe(finalize(() => this.blockUI.stop('Deletado')))
            .subscribe(() => {
                this.listarCliente();
            });
    }

    editar(id: number): void {
        this.tituloModal = this.setTitulos(2).header + ' ' + 'Cliente';
        this.formularioCliente.editarCliente(id);
        this.formularioCliente.formulario.enable();
        this.display = true;
    }

    vizualizarCliente(id: number): void {
        this.tituloModal = this.setTitulos(1).header + ' ' + 'Cliente';
        this.formularioCliente.editarCliente(id);
        this.formularioCliente.formulario.disable();
        this.display = true;
    }

    ativoInativo(ativo: boolean): string {
        return ativo ? 'ATIVO' : 'INATIVO';
    }

    public fecharModal(): void {
        this.display = false;
    }

    public novoCliente(): void {
        this.display = true;
        this.tituloModal = this.setTitulos(0).header + ' ' + 'Cliente';
    }

    confirm(id: number): void {
        this.confirmationService.confirm({
            header: 'Confirmação',
            message: 'Deseja excluir o cliente?',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.deletarCliente(id);
            }
        });
    }

    resetarForm(): void {
        this.formularioCliente.fecharForm();
        this.listarCliente();
    }

    setTitulos(id: number): TituloModalEnum {
        return TituloModalEnum.obterPorIndex(id);
    }
}
