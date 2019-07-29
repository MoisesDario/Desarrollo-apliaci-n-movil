import { Component } from '@angular/core';
import { ModalController, ActionSheetController, AlertController } from '@ionic/angular';
import { ModalComponent } from '../modal/modal.component';
import { Route } from '@angular/compiler/src/core';
import { EditModalComponent } from '../edit-modal/edit-modal.component';

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
    if(age < 18)
      return 'danger';
    else if(age >=18 && age <= 30)
    return 'success';
    else if(age >30)
    return 'tertiary'
  }

  async presentModal(){ //Crear
    const modal = await this.modal.create({
      component: ModalComponent,
    });

    modal.onDidDismiss().then((data) => { //Para generar  otro arreglo de tipo data
      if (data !== null) {
        this.myArray.push(data.data);
  
      }
      
    });

    return await modal.present();
  }
  
  Menu(posicion: any){
    console.log("Click en el arreglo:" + posicion);
    this.presentActionSheet(posicion);
  
  }

  async presentEditar(data, posicion){ //Editar
    console.log("estos son los valores" + data);
    const modal = await this.modal.create({
      component: EditModalComponent,
      componentProps: {
        "name": data.name,
        "email": data.email,
        "age": data.age
      }
    });

    modal.onDidDismiss().then((data) => { //Para generar  otro arreglo de tipo data
      this.myArray[posicion] = data.data;
    });
    return await modal.present();
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
          console.log("Se va eliminar en el arreglo: " + posicion);
          this.presentAlert(posicion);
        }
        },{
          text:'Edit',
          role:'destructive',
          icon:'trash',
          handler:() =>{
            console.log('Edit clicked');
            this.presentEditar(this.myArray[posicion], posicion);
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
