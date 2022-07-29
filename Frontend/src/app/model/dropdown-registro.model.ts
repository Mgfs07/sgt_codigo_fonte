import {SelectItem} from 'primeng';

export class DropdownRegistroModel {

    constructor(
        public impacto: SelectItem[],
        public tipoRegistro: SelectItem[],
        public statusRegistro: SelectItem[],
        public prioridade: SelectItem[],
        public cliente: SelectItem[]
    ) {
    }
}
