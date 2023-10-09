import { Component, OnInit } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';


@Component({
  selector: 'app-vaccination-center',
  templateUrl: './vaccination-center.component.html',
  styleUrls: ['./vaccination-center.component.scss']
})

export class VaccinationCenterComponent implements OnInit {
  center: VaccinationCenter = {
    id:1,
    name:"Hôpital Central",
    address: "Rue des potiers",
    city: "Nancy"
  }

  airport: VaccinationCenter = {
    id:2,
    name: "Hôpital de l'aéroport",
    address: "Avenue (nom de d'avenue)",
    city: "Paris"
  }

  

  constructor() { }

  ngOnInit(): void { }

  clearName(){
    this.center.name="";
  }

  selected?:VaccinationCenter

  isSpecialCenter(center: VaccinationCenter){
    return center.city == "Nancy"
  }
}


