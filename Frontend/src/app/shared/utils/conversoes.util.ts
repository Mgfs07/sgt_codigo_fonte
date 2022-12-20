export class ConversoesUtil {

    public static numberToCurrency(num: number): string {
        return (!!num ? num : 0).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    }

    public static convertToReal(num: number): string{
        return Number.isInteger(num) ? 'R$ ' + num + ',00' : 'R$ ' + num;
    }

    public static currencyToNumber(valor: string): number {
        const tempString = valor.replace(/[^0-9,-]+/g, '');
        return parseFloat(tempString.replace(',', '.'));
    }
}
