export class MenuEnum {
    static readonly COLABORADORES = new MenuEnum(0, 'colaborador');
    static readonly PAGAMENTOS = new MenuEnum(1, 'pagamento');
    static readonly DOACOES = new MenuEnum(2, 'doacoes');
    static readonly GASTOS = new MenuEnum(3, 'gastos');
    static readonly META = new MenuEnum(4, 'pagamentos-metas');


    static values = [
        MenuEnum.COLABORADORES,
        MenuEnum.PAGAMENTOS,
        MenuEnum.DOACOES,
        MenuEnum.GASTOS,
        MenuEnum.META,

    ];

    constructor(
        public index: number,
        public titulo: string
    ) {
    }

    static obterPorIndex(index: number): MenuEnum | any {
        return MenuEnum.values.find(menu => menu.index === index);
    }

    static setClasse(id: number): MenuEnum {
        return MenuEnum.obterPorIndex(id);
    }
}
