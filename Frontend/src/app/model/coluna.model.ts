export class ColunaModel {
    public field: string;
    public header: string;

    constructor(field: string, header: string, public width ?: string) {
        this.field = field;
        this.header = header;
    }

}
