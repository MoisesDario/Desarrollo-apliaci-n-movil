import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ModalController, NavParams } from '@ionic/angular';

@Component({
  selector: 'app-edit-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.scss'],
})
export class EditModalComponent implements OnInit {

  public editForm : FormGroup; 
  /*   name = new FormControl('');
    email = new FormControl('');
    age = new FormControl(''); */
    modalName: string;
    modalEmail:string;
    modalAge: number;
  
  constructor(private modalCtrl: ModalController, public formBuilder: FormBuilder, private navParams: NavParams) {
    this.editForm = formBuilder.group({
      name: [this.navParams.get('name')],
      email: [this.navParams.get('email')],
      age: [this.navParams.get('age')]
    });
  }

  ngOnInit(){
    
    this.modalName = this.navParams.get('name');
    this.modalEmail = this.navParams.get('email');
    this.modalAge = this.navParams.get('age');
    console.log("Nombre: " + this.modalName);
    console.log("Correo: " + this.modalEmail);
    console.log("Edad: " + this.modalAge);
  }

  dismissMOdal(){
      this.modalCtrl.dismiss({
        'dismissed': true,
      });
  }

  editModalCtrl(value: any){
    console.log("Actualizado los datos");
    console.log("Nombre: " + this.editForm.value.name);
    console.log("Correo: " + this.editForm.value.email);
    console.log("Edad: " + this.editForm.value.age);
    this.modalCtrl.dismiss(value);
  }
}
