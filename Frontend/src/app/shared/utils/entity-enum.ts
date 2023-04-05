export class EntityEnum {

    static readonly COLABORADOR = new EntityEnum(1, 'Colaborador');
    static readonly DOACAO = new EntityEnum(2, 'Doacao');
    static readonly GASTO = new EntityEnum(3, 'Gasto');
    static readonly PAGAMENTO = new EntityEnum(4, 'Meta');
    static readonly PAGAMENTO_COLABORADOR = new EntityEnum(5, 'Pagamento do Colaborador');

    static values = [
        EntityEnum.COLABORADOR,
        EntityEnum.DOACAO,
        EntityEnum.GASTO,
        EntityEnum.PAGAMENTO,
        EntityEnum.PAGAMENTO_COLABORADOR
    ]

    constructor(
        public id: number,
        public entity: string
    ) {
    }
}
