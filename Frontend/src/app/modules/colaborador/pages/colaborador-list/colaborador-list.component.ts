import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColunaModel} from '../../../../model/coluna.model';
import {ColaboradoresListModel} from "../../../../model/colaboradores-list.model";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {ColaboradorFormComponent} from "../colaborador-form/colaborador-form.component";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {ConfirmationService} from "primeng/api";
import {Mensagens} from "../../../../shared/utils/mensagens";
import {finalize} from "rxjs";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
    selector: 'app-colaborador',
    templateUrl: './colaborador-list.component.html',
    styleUrls: ['./colaborador-list.component.scss']
})
export class ColaboradorListComponent implements OnInit {

    colaboradores: ColaboradoresListModel[] = [];
    coluna: ColunaModel[] = [];
    novo: boolean;

    @BlockUI() blockUI : NgBlockUI;
    @Input() display = false;
    @Output() recarregarListagem: EventEmitter<any> = new EventEmitter;
    @ViewChild(ColaboradorFormComponent) formColaborador: ColaboradorFormComponent;

    constructor(private colaboradorService: ColaboradorService,
                private mensagemUtil: MensagensUtil,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.columnsTable();
        this.obterColaborador();
    }

    public columnsTable() {
        this.coluna = [
            new ColunaModel('nomeColaborador', 'Nome'),
            new ColunaModel('nomeUnidade', 'Unidade'),
            new ColunaModel('telefone', 'Telefone'),
            new ColunaModel('acoes', 'Ações', '10%' )
        ];
    }

    public obterColaborador(): void {
        this.blockUI.start();
        this.colaboradorService.buscarTodos().pipe(finalize(() => {
            this.blockUI.stop();
        })).subscribe(
            (data) => {
                    this.colaboradores = data;
            }
        );
    }

    carregar(idColaborador: number): void {
        this.display = true;
        this.novo = false;
        this.formColaborador.editar(idColaborador);
        this.formColaborador.formularioColaborador.enable();
    }

    confirmarExclusao(id: number): void {
        this.confirmationService.confirm({
            header: 'Excluir Doação',
            message: 'Deseja excluir esse registro? ',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => this.deletar(id)
        })
    }

    public deletar(id: number): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.colaboradorService.deletar(id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.obterColaborador();
            }))
            .subscribe(() => {this.mensagemUtil.mensagemSucesso(id, " ", true);
            }, error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao Excluir Colaborador.\n') )
    }

    verificarTitulo(): string {
        return this.mensagemUtil.tituloModal(this.novo, EntityEnum.COLABORADOR);
    }


    public fecharModal(): void {
        this.display = false;
        if (this.formColaborador.listarColaborador) {
            this.obterColaborador();
            this.formColaborador.listarColaborador = false;
        }
    }

    resetarForm(): void {
        this.formColaborador.fecharForm();
    }

    novoColaborador(): void {
        this.formColaborador.formularioColaborador.reset();
        this.display = true;
        this.novo = true;
    }
}
