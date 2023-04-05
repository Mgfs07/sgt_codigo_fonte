import {Injectable} from '@angular/core';
import {MessageService} from "primeng/api";
import {EntityEnum} from "./entity-enum";

@Injectable()
export class MensagensUtil {

    public static SUCESSO_AO_EXCLUIR = 'Excluido com Sucesso';
    public static SUCESSO_AO_SALVAR = 'Salvo com Sucesso';
    public static SUCESSO_AO_ATUALIZAR = 'Atualizado com Sucesso';

    constructor(private messageService: MessageService) {}

    mensagemErro(message: string, titulo: string): void {
        this.messageService.add({
            severity: "error",
            summary: titulo,
            detail: message
        });
    }

    mensagemSucesso(id?: number, message?: string, excluir?: boolean, entidade?: EntityEnum): void {
        if (id && excluir == false) {
            this.messageService.add({
                severity: 'success',
                summary: MensagensUtil.SUCESSO_AO_ATUALIZAR,
                detail: message ? message : entidade?.entity + ' ' + MensagensUtil.SUCESSO_AO_ATUALIZAR
            });
        }
        else if (excluir === false) {
            this.messageService.add({
                severity: 'success',
                summary: MensagensUtil.SUCESSO_AO_SALVAR,
                detail: message ? message : entidade?.entity + ' ' +  MensagensUtil.SUCESSO_AO_SALVAR
            })
        }
        else {
            this.messageService.add({
                severity: 'success',
                summary: MensagensUtil.SUCESSO_AO_EXCLUIR,
                detail: message ? message : entidade?.entity + ' ' + MensagensUtil.SUCESSO_AO_EXCLUIR
            });
        }
    }


    tituloModal(novo: boolean, entity: EntityEnum): string {
        if(novo){
            return "Novo Registro de " + entity.entity
        }else{
            return 'Atualizar Registro de ' + entity.entity
        }
    }

}
