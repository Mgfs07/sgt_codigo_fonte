import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "primeng/api";
import {ColaboradorService} from "../../../../shared/service/colaborador.service";
import {PagamentosService} from "../../../../shared/service/pagamentos.service";
import {GastosService} from "../../../../shared/service/gastos.service";
import {GastoModel} from "../../../../model/gasto.model";
import {finalize} from "rxjs";
import {MensagensUtil} from "../../../../shared/utils/mensagens-util";
import {GastoListModel} from "../../../../model/gasto-list.model";
import {ValoresModel} from "../../../../model/valores.model";
import {ConversoesUtil} from "../../../../shared/utils/conversoes.util";
import {ColunaModel} from "../../../../model/coluna.model";
import {FileUpload} from "primeng/fileupload";
import {DatePipe} from "@angular/common";
import {EntityEnum} from "../../../../shared/utils/entity-enum";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Mensagens} from "../../../../shared/utils/mensagens";

@Component({
    selector: 'app-gastos',
    templateUrl: './gastos.component.html',
    styleUrls: ['./gastos.component.scss']
})
export class GastosComponent implements OnInit {

    formGasto: FormGroup;
    gasto: GastoModel;
    valoresGastos: ValoresModel;
    listarPagamento: boolean = false;
    colaboradorDrop: SelectItem[];
    pagamentoDrop: SelectItem[];
    pagamentoRetirado: SelectItem[];
    dataRegistro: Date = new Date();
    file: FileReader = new FileReader();

    colunas: ColunaModel[];

    @BlockUI() blockUI : NgBlockUI;
    @Input() gastoList: GastoListModel[] = [];
    @Output() respForm: EventEmitter<boolean> = new EventEmitter();

    @ViewChild(FileUpload) fileUpload: FileUpload;

    constructor(private fb: FormBuilder,
                private colaboradorService: ColaboradorService,
                private pagamentosService: PagamentosService,
                private gastoService: GastosService,
                private mensagemUtil: MensagensUtil,
                private dataPipe: DatePipe) {
    }

    ngOnInit(): void {
        this.novoFormulario();
        this.buscarColaborador();
        this.buscarPagamentos();
        this.buscarValores();
        this.columnsTable();
    }

    novoFormulario(): void {
        this.formGasto = this.fb.group({
            id: [null],
            motivo: ['', [Validators.required]],
            descricao: ['', [Validators.required]],
            idColaborador: ['', [Validators.required]],
            dataDispesa: ['', [Validators.required]],
            valorRetirado: ['', [Validators.required]],
            comprovante: ['', [Validators.required]],
            retiradoDoPagamento: ['', [Validators.required]],
        });
    }

    public columnsTable(): void {
        this.colunas = [
            new ColunaModel('nomePagamento', 'Pagamento'),
            new ColunaModel('valorPago', 'Valor Pago'),
        ];
    }


    buscarPagamentos(): void {
        this.pagamentosService.buscarDropdown().subscribe(
            (data) => {
                this.pagamentoDrop = data;
                this.pagamentoRetirado = data;
                this.buscarValores();
            });
    }

    buscarColaborador(): void {
        this.colaboradorService.buscarDropdown().subscribe(
            (data) => {
                this.colaboradorDrop = data;
            });
    }

    buscarValores(): void {
        this.gastoService.valores().pipe(finalize(() => {
            this.blockUI.stop()
        })).subscribe((data) => this.valoresGastos = data)
    }

    modificarValor(valor: number): string {
        return ConversoesUtil.numberToCurrency(valor);
    }


    salvarFormulario(): void {
        this.blockUI.start(Mensagens.CARREGANDO_DADOS);
        this.gasto = this.formGasto.getRawValue();
        this.gastoService.salvar(this.gasto).pipe(finalize(() => {
            this.fecharForm();
            this.listarPagamento = true;
            this.buscarValores();
        })).subscribe(
            () => {
                this.mensagemUtil.mensagemSucesso(this.gasto.id, "", false, EntityEnum.GASTO)
            }, error => this.mensagemUtil.mensagemErro(error.error.message, 'Falha ao salvar o gasto.\n'))
    }


    fecharForm(): void {
        this.formGasto.reset();
        this.limparFoto();
        this.buscarValores();
        this.respForm.emit();
    }

    editarGasto(id: number): void {
        this.gastoService.findById(id)
            .subscribe(response => {
                response.dataDispesa = new Date(response.dataDispesa + 'T00:00');
                this.formGasto.patchValue(response);
            });
    }

    limparFoto(): void {
        this.formGasto.get('comprovante')?.reset();
        this.mostrarBotao();
    }

    uploadFile(event: any): void {
        this.file.readAsDataURL(event.files[0]);
        this.file.onload = () => this.formGasto.get('comprovante')?.setValue(this.file.result);
    }

    downloadImagem(): void {
        const linkImg = this.formGasto.get('comprovante')?.value;
        const donwloadLink = document.createElement('a');
        donwloadLink.href = linkImg;
        donwloadLink.download = this.nomeComprovante();
        donwloadLink.click();
    }

    nomeComprovante(): string {
        let data = this.formGasto.get('dataDispesa')?.value;
        let dataString = this.dataPipe.transform(data, 'dd-MM-yyyy');
        return 'Comprovate dia ' + dataString;
    }

    mostrarBotao(): boolean {
        return this.formGasto.get('comprovante')?.value;
    }
}
