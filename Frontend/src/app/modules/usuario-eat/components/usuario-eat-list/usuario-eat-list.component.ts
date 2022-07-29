import {Component, OnInit, ViewChild} from '@angular/core';
import {UsuarioEatModel} from '../../../../model/usuario-eat.model';
import {UsuarioEATService} from '../../../../shared/service/usuario-eat.service';
import {ColunaModel} from '../../../../model/coluna.model';

@Component({
    selector: 'app-usuario-eat-list',
    templateUrl: './usuario-eat-list.component.html',
})
export class UsuarioEatListComponent implements OnInit {

    @ViewChild('dtColaborador') table: UsuarioEatModel;

    usuarios: UsuarioEatModel[];
    colunas: ColunaModel[];

    constructor(private usuarioEatService: UsuarioEATService) {
    }

    ngOnInit(): void {
        this.columnsTable();
        this.obterUsuarios();
    }


    public columnsTable() {
        this.colunas = [
            new ColunaModel('nome', 'Usuários'),
            new ColunaModel('cargo', 'Cargo'),
            new ColunaModel('login', 'Login'),
        ];
    }

    public obterUsuarios(): void {
        this.usuarioEatService.getUsuarios().subscribe(
            (data) => {
                this.usuarios = data;
            }
        );
    }

    public isNome(coluna: string): boolean{
        return coluna === 'nome';
    }
}
