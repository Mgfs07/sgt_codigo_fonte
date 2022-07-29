export class RegistroModel {
    id: number;
    idTipoRegistro: number;
    idImpacto: number;
    idStatusRegistro: number;
    idPrioridade: number;
    idCliente: number;
    titulo: string;
    descricao: string;
    dataInicio: Date;
    dataLimite: Date;
    ativo: boolean;
}
