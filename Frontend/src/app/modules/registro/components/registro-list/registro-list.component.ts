import {Component, OnInit, ViewChild} from '@angular/core';
import {finalize} from 'rxjs/operators';
import {BlockUIService} from 'ng-block-ui';
import {ConfirmationService, MessageService, TabPanel} from 'primeng';
import {RegistroService} from '../../../../shared/service/registro.service';
import {RegistroListModel} from '../../../../model/registro-list.model';
import {TabsModel} from '../../../../model/tabs.model';
import {ColunaModel} from '../../../../model/coluna.model';

@Component({
    selector: 'app-registro-list',
    templateUrl: './registro-list.component.html',
    styleUrls: ['./registro-list.component.css']
})
export class RegistroListComponent implements OnInit {

    private sequenceIdAba = 1;
    public indiceAbaSelecionada = 0;

    @ViewChild('novaAba') novaAba: TabPanel;

    registros: RegistroListModel[] = [];
    tabs: TabsModel[] = [];
    cols: ColunaModel[];
    editing: Boolean = false;

    constructor(private blockUI: BlockUIService,
                private messageService: MessageService,
                private registroService: RegistroService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.listarRegistro();
        this.definirColunas();
    }

    public definirColunas(): void {
        this.cols = [
            new ColunaModel('idNegociavel', 'Codigo'),
            new ColunaModel('titulo', 'Registro'),
            new ColunaModel('nomeCliente', 'Cliente'),
            new ColunaModel('dataLimite', 'Data Limite'),
            new ColunaModel('tempoVida', 'Tempo de vida'),
            new ColunaModel('nomeStatus', 'Status'),
            new ColunaModel('acoes', 'Ações')
        ];
    }

    botaoClick(): void {
        this.tabs.push(new TabsModel(this.gerarIdAba(), 'Novo Registro'));
    }

    visualizar(id: number): void {
        this.tabs.push(new TabsModel(this.gerarIdAba(), 'EAT-' + [id], id));
        this.editing = false;
    }

    listarRegistro(): void {
        this.blockUI.start('Carregando os Registros');
        this.registroService.getRegistro().pipe(finalize(
            () => this.blockUI.stop('Concluído')))
            .subscribe((response) => {
                    if (response) {
                        this.registros = response;
                    }
                }, () => this.messageService.add({severity: 'error', summary: 'Erro', detail: 'Erro ao listar os Registros'})
            );
    }

    deletarRegistro(id: number): void {
        this.blockUI.start('Deletando o Registro');
        this.registroService.deleteRegistro(id).pipe(finalize(
            () => this.blockUI.stop('Deletado')))
            .subscribe(() => {
                this.listarRegistro();
            });
    }

    confirm(id: number): void {
        this.confirmationService.confirm({
            header: 'Confirmação',
            message: 'Deseja excluir esse Registro?',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.deletarRegistro(id);
            }
        });
    }

    setarIdRegistroTab(tab: TabsModel, especificacao: TabsModel): void {
        tab.idRegistro = especificacao.idRegistro;
        tab.idCliente = especificacao.idCliente;
        tab.etapa = 2;
    }

    editar(id: number): void {
        this.tabs.push(new TabsModel(this.gerarIdAba(), 'EAT-' + [id], id));
        this.editing = true;
    }

    isDataLimite(coluna: string): boolean {
        return coluna === 'dataLimite';
    }

    isAcoes(coluna: string): boolean {
        return coluna === 'acoes';
    }

    public mudarAba(evento: Record<string, any>): void {
        this.novaAba.selected = false;
        if (evento.index === this.tabs.length + 1) {
            this.botaoClick();
            this.indiceAbaSelecionada = this.tabs.length;
            return;
        }
        this.indiceAbaSelecionada = evento.index;
        if (this.indiceAbaSelecionada === 0) {
            this.listarRegistro();
            this.editing = false;
        }
    }

    private gerarIdAba(): number {
        return this.sequenceIdAba++;
    }

    selectAba(index: number): boolean {
        return index + 1 === this.indiceAbaSelecionada;
    }
}
