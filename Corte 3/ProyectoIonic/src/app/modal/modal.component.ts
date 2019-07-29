import { Component, OnInit } from '@angular/core';
import {ModalController, NavParams } from '@ionic/angular'
import { HomePage } from '../home/home.page';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {

  public generarForm : FormGroup; 
/*   name = new FormControl('');
  email = new FormControl('');
  age = new FormControl(''); */

  constructor(private modalCtrl: ModalController, public formBuilder: FormBuilder) {
    this.generarForm = formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      age: ['', Validators.required]
    });
  }

  ngOnInit() {}

  dismissMOdal(){
    this.modalCtrl.dismiss({
      'dismissed': true,
    });
  }

  pushModalCtrl(){
    console.log("Datos creado: ");
    console.log("Nombre: ",this.generarForm.value.name);
    console.log("Correo: ",this.generarForm.value.email);
    console.log("Edad: ", this.generarForm.value.age);
    //console.log(this.generarForm.value);

    this.modalCtrl.dismiss( this.generarForm.value);
  }

/*
  datosModal(){
    const modal = await this.HomePage.create({
      component: ModalComponent
  });
  return await HomePage.present();
  }
*/
}
