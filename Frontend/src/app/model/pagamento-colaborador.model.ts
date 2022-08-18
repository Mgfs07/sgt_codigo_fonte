export class PagamentoColaboradorModel{

    public  id !: number;
    public  idColaborador !: number;
    public  idPagamento !: number;
    public  observacao !: string;
    public  retiradoLugar !: boolean;
    public  pagamentosRetirado !: number;
    public  dataPagamento !: Date;
    private valorPago !: number;
}
