import {Component, Input, OnInit} from '@angular/core';
import {ItemModel} from '../../../../model/item.model';
import {ItemService} from '../../../../shared/service/item.service';
import {BlockUIService} from 'ng-block-ui';
import {ConfirmationService, MessageService} from 'primeng';
import {finalize} from 'rxjs/operators';
import {ColunaModel} from '../../../../model/coluna.model';

@Component({
    selector: 'app-item-list',
    templateUrl: './item-list.component.html',
    styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
    item: ItemModel[];
    cols: ColunaModel[];

    @Input() recebeIdRegList: number;

    constructor(
        private itemService: ItemService,
        private blockUI: BlockUIService,
        private confirmationService: ConfirmationService,
        private messageService: MessageService
    ) {
    }

    ngOnInit(): void {
        this.columns();
        this.listarItens();
    }

    public listarItens(): void {
        this.itemService.getItens(this.recebeIdRegList).subscribe(
            (data) => {
                this.item = data;
            });
    }

    public columns(): void {
        this.cols = [
            new ColunaModel('acao', 'Ação', '35%'),
            new ColunaModel('nomeProjeto', 'Projeto', '15%'),
            new ColunaModel('nomeUsuario', 'Responsável'),
            new ColunaModel('descricaoStatusAcao', 'Status', '15%'),
            new ColunaModel('acoes', 'Ações', '10%')
        ];
    }

    public excluirItem(id: number): void {
        this.blockUI.start('Deletando o Item');
        this.itemService.deleteItem(id)
            .pipe(finalize(() => this.blockUI.stop('Item Deletado')))
            .subscribe(() => {
                this.mensagemSucesso();
                this.listarItens();
            }, error => this.mensagemErro(error));
    }

    confirm(id: number): void {
        this.confirmationService.confirm({
            header: 'Confirmação',
            message: 'Deseja excluir o item?',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.excluirItem(id);
            }
        });
    }

    public isNome(coluna: string): boolean {
        return coluna === 'nomeUsuario';
    }

    mensagemSucesso(): void {
        this.messageService.add({severity: 'success', summary: 'Item excluído com sucesso'});
    }

    mensagemErro(message: string): void {
        this.messageService.add({
            severity: 'error',
            summary: 'Falha ao excluir item',
            detail: message
        });
    }
}
