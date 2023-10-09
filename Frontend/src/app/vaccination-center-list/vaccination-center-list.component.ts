import { Component } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.scss']
})
export class VaccinationCenterListComponent {
  centers: VaccinationCenter[] = [
    {id: 1, name: 'Hôpital du vélodrome', address: '123 Main Street', city: 'Nancy'},
    {id: 2, name: 'Clinique du 93', address: '456 Elm Street', city: 'Paris'},
  ]

  selected?: VaccinationCenter;

  isSpecialCenter(center: VaccinationCenter){
    return center.city == "Nancy"
  }

  selectCenter(center: VaccinationCenter){
    this.selected=center;
  }
}
