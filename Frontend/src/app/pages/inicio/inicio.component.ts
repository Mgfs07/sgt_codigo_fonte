import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MenuEnum} from "../app/menu-enum";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {

  cardComponent: string;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public mudarRota(idTipoAtendimento: number): void {
        this.cardComponent = MenuEnum.setClasse(idTipoAtendimento).titulo;
        this.router.navigate(['/' + this.cardComponent]);
    }

}
