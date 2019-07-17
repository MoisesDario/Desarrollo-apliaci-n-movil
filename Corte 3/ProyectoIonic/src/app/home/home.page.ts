import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  myArray : any = [
    {
      name: 'Juan',
      email: 'juan@outlook.es',
      age: 25,
      //color: 'success'
    },
    {
      name: 'Moises',
      email: 'figuero@hotmail.com',
      age: 22,
      //color: 'success'
    },
    {
      name: 'Jazmin',
      email: 'jaz@example.com',
      age: 17,
      //color: 'tertiary'
    }
  ];


  constructor(private modal: ModalController) {
    let data = {
      name: 'Rosi',
      email: 'rosi@hotmail.com',
      age: 45,
      //color: 'danger'
    };

    this.myArray.push(data);
  }

  
  calculateAgeColor(age: number){
    console.log('Age', age);
    if(age < 18)
      return 'danger';
    else if(age >=18 && age <= 30)
    return 'success';
    else if(age >30)
    return 'tertiary'
  }

  async presentModal(){
    const modal = await this.modal.create({
        component: ModalComponent
    });
    return await modal.present();
  }

}
