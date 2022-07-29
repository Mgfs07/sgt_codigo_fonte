import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService, SelectItem} from 'primeng';
import {ProjetoService} from '../../../../shared/service/projeto.service';
import {UsuarioEATService} from '../../../../shared/service/usuario-eat.service';
import {StatusAcaoService} from '../../../../shared/service/status-acao.service';
import {Observable} from 'rxjs';
import {ItemModel} from '../../../../model/item.model';
import {ItemService} from '../../../../shared/service/item.service';
import {ItemListComponent} from '../item-list/item-list.component';

@Component({
    selector: 'app-item-form',
    templateUrl: './item-form.component.html',
    styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit {

    public formItem: FormGroup;
    projetos: SelectItem[];
    usuarios: SelectItem[];
    statusAcao: SelectItem[];

    @Input() recebeIdRegistro: number;
    @Input() receberIdCliente: number;
    @ViewChild(ItemListComponent) itemList: ItemListComponent;

    constructor(
        private fbItem: FormBuilder,
        private itemService: ItemService,
        private projetoService: ProjetoService,
        private usuarioService: UsuarioEATService,
        private statusAcaoService: StatusAcaoService,
        private messageService: MessageService
    ) {
    }

    ngOnInit(): void {
        this.buscarDropDownItem();
        this.formItem = this.fbItem.group({
            id: [null],
            acao: [null, [Validators.required]],
            idProjeto: [null, [Validators.required]],
            idUsuario: [null, [Validators.required]],
            idStatusAcao: [null, [Validators.required]],
            idRegistro: [null, [Validators.required]]
        });
    }

    buscarDropDownItem(): void {
        this.itemService.getDropDownItem(this.receberIdCliente).subscribe(response => {
            this.projetos = response.projeto;
            this.usuarios = response.usuario;
            this.statusAcao = response.statusAcao;
        });
    }

    verificaId(): void {
        if (!this.formItem.get('id').value) {
            this.salvarItem();
            return;
        }
        this.atualizarItem();
    }

    salvarItem(): void {
        this.formItem.controls['idRegistro'].patchValue(this.recebeIdRegistro);
        if (this.formItem.valid) {
            this.finalizarReq(this.itemService.postItem(this.formItem.getRawValue()));
        }
    }

    atualizarItem() {
        this.finalizarReq(this.itemService.putItem(this.formItem.getRawValue()));
    }

    finalizarReq(observable: Observable<ItemModel>): void {
        observable.subscribe(() => {
            this.msgSucessoAoCriar();
            this.formItem.reset();
            this.itemList.listarItens();
        },
            error => this.showError(this.pegarSubstring(error.error.detail)));
    }

    pegarSubstring(message: string): string {
        return message.substr(message.indexOf(' "'));
    }

    showError(message: string): void {
        this.messageService.add({severity: 'error', summary: 'Falha ao Salvar Cliente', detail: message});
    }

    msgSucessoAoCriar(): void {
        this.messageService.add({severity: 'success', summary: 'Item criado com sucesso!', detail: ''});
    }

}
