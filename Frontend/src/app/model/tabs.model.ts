export class TabsModel {
    constructor(
        public id: number,
        public header: string,
        public idRegistro ?: number,
        public idCliente ?: number,
        public etapa: number = 1) {
    }
}
