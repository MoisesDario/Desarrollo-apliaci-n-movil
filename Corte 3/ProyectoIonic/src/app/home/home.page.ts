import { Component } from '@angular/core';
import { ModalController, ActionSheetController, AlertController } from '@ionic/angular';
import { ModalComponent } from '../modal/modal.component';
import { Route } from '@angular/compiler/src/core';
import { EditModalComponent } from '../edit-modal/edit-modal.component';
import { dashCaseToCamelCase } from '@angular/compiler/src/util';

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
      age: 25
      //color: 'success'
    },
    {
      name: 'Moises',
      email: 'figuero@hotmail.com',
      age: 22
      //color: 'success'
    },
    {
      name: 'Jazmin',
      email: 'jaz@example.com',
      age: 17
      //color: 'tertiary'
    }
  ];


  constructor(private modal: ModalController,public alertController: AlertController, public actionSheetController: ActionSheetController) {
    let data = {
      name: 'Rosi',
      email: 'rosi@hotmail.com',
      age: 45
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

    modal.onDidDismiss().then((data) => { //Para generar  otro arreglo de tipo data
      console.log(data)
      console.log(data.data)

      this.myArray.push(data.data);
    });
    return await modal.present();
  }

  Menu(posicion: any){
    console.log(posicion);
    this.presentActionSheet(posicion);
  
  }

  async presentAlert(posicion: number){
    const alert = await this.alertController.create({
      header: ' ¡ ALERTA !',
      subHeader: '¡ Estas seguro de eliminar !',
      buttons:[{
        text: 'Delete',
        role: 'delete',
        cssClass:'secondary',
        handler:(blah) =>{
          console.log('Se elimino el elemento');
          this.myArray.splice(posicion,1);
        }
      },{
        text: 'Cancel',
        role: 'cancel',
        cssClass: 'secondary',
        handler: (blah) => {
          console.log('Se cancelo el elemento');
        }
      }]
    });
    await alert.present();
  }
  async presentActionSheet(posicion:number){
    const actionSheet = await this.actionSheetController.create({
      header: 'You choose a option',
      buttons: [{
        text:'Delete',
        role: 'destructive',
        icon: 'trash',
        handler:() =>{
          console.log('Delete clicked');
          this.presentAlert(posicion);
        }
        },{
          text:'Edit',
          role:'destructive',
          icon:'trash',
          handler:() =>{
            console.log('Edit clicked');
           // this.route.navigate(['/edit', posicion);
          }
        },{
          text:'Cancel',
          icon:'close',
          role:'cancel',
          handler:() =>{
            console.log('Cancel clicked');
          }
        }]
      });
    await actionSheet.present();
  }
}
