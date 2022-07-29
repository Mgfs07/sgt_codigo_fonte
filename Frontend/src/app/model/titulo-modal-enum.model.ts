export class TituloModalEnum {
    static readonly NOVO = new TituloModalEnum(0, 'Novo');
    static readonly VISUALIZAR = new TituloModalEnum(1, 'Visualizar');
    static readonly EDITAR = new TituloModalEnum(2, 'Editar');

    static values = [
        TituloModalEnum.NOVO,
        TituloModalEnum.VISUALIZAR,
        TituloModalEnum.EDITAR,
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalEnum {
        return TituloModalEnum.values.find(titulo => titulo.index === index);
    }
}
