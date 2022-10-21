import {Injectable} from '@angular/core';
import {MessageService} from "primeng/api";

@Injectable()
export class MensagensUtil {

    constructor(private messageService: MessageService) {}

    mensagemErro(message: string, titulo: string): void {
        this.messageService.add({
            severity: "error",
            summary: titulo,
            detail: message
        });
    }

    mensagemSucesso(message: string, titulo: string): void {
        this.messageService.add({
            severity: "success",
            summary: titulo,
            detail: message
        });
    }

}
