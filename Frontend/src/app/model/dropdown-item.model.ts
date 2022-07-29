import {SelectItem} from 'primeng';

export class DropdownItemModel {

    constructor(
        public projeto: SelectItem[],
        public usuario: SelectItem[],
        public statusAcao: SelectItem[],
    ) {
    }
}
