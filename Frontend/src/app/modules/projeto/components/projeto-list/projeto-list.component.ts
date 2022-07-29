import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ProjetoService} from '../../../../shared/service/projeto.service';
import {ConfirmationService, MessageService} from 'primeng';
import {ProjetoFormComponent} from '../projeto-form/projeto-form.component';
import {ColunaModel} from '../../../../model/coluna.model';
import {BlockUIService} from 'ng-block-ui';
import {ProjetoModel} from '../../../../model/projeto.model';
import {finalize} from 'rxjs/operators';
import {ProjetoListModel} from '../../../../model/projeto-list.model';
import {TituloModalEnum} from '../../../../model/titulo-modal-enum.model';

@Component({
    selector: 'app-projeto-list',
    templateUrl: './projeto-list.component.html',
})

export class ProjetoListComponent implements OnInit {

    projetos: ProjetoListModel[];
    projetoEdit: ProjetoModel;
    cols: ColunaModel[];
    tituloModal: string;

    @Input() display = false;
    @ViewChild(ProjetoFormComponent) formularioProjeto: ProjetoFormComponent;


    constructor(private projetoService: ProjetoService,
                private confirmationService: ConfirmationService,
                private messageService: MessageService,
                private restProjeto: ProjetoService,
                private blockUI: BlockUIService) {
    }

    ngOnInit(): void {
        this.columns();
        this.getProjetos();
    }

    public fecharModal(displayFalse: boolean): void {
        this.display = displayFalse;
    }

    public carregarFormulario(projetoRecebido: ProjetoModel): void {
        this.display = true;
        this.tituloModal = this.setTitulos(2).header + ' Projeto';
        this.projetoEdit = projetoRecebido;
        this.formularioProjeto.carregarProjeto(this.projetoEdit.id);
        this.formularioProjeto.formProjeto.enable();
        this.formularioProjeto.read = false;
    }

    public visualizarProjeto(projetoRecebido: ProjetoModel): void {
        this.carregarFormulario(projetoRecebido);
        this.tituloModal = this.setTitulos(1).header + ' ' + this.projetoEdit.nome;
        this.formularioProjeto.formProjeto.disable();
        this.formularioProjeto.read = true;
    }

    public columns(): void {
        this.cols = [
            new ColunaModel('nome', 'Nome'),
            new ColunaModel('nomeCliente', 'Cliente'),
            new ColunaModel('quantidadeResistros', 'Quantidade de Registros'),
            new ColunaModel('acoes', 'Ações')
        ];
    }

    public getProjetos(): void {
        this.projetoService.getProjetos().subscribe(
            (data) => {
                this.projetos = data;
            });
    }

    confirm(id: number): void {
        this.confirmationService.confirm({
            header: 'Confirmação',
            message: 'Deseja excluir o projeto?',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.excluirProjeto(id);
            }
        });
    }

    mensagemSucesso(): void {
        this.messageService.add({severity: 'success', summary: 'Projeto excluído com sucesso'});
    }

    mensagemErro(message: string): void {
        this.messageService.add({
            severity: 'error',
            summary: 'Falha ao excluir projeto',
            detail: message
        });
    }

    public excluirProjeto(id: number): void {
        this.blockUI.start('Deletando Projeto');
        this.projetoService.deleteProjetos(id)
            .pipe(finalize(() => this.blockUI.stop('Projeto Deletado')))
            .subscribe(() => {
                this.mensagemSucesso();
                this.getProjetos();
        }, error => this.mensagemErro(error));
    }

    public novoProjeto(): void {
        this.formularioProjeto.formProjeto.reset();
        this.formularioProjeto.formProjeto.enable();
        this.tituloModal = this.setTitulos(0).header;
        this.display = true;
        this.formularioProjeto.read = false;
    }

    setTitulos(id: number): TituloModalEnum {
        return TituloModalEnum.obterPorIndex(id);
    }

    atualizarProjetos(): void {
        this.getProjetos();
    }

}
