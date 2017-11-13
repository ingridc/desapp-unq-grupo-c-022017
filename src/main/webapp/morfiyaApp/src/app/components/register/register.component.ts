import { Component, Input, OnInit, AfterViewChecked } from '@angular/core';
import { UserService } from './../../services/userServices/user.service';
import {MessageService} from './../../services/messageServices/message.service';
import {ViewChild, ElementRef} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { AlertService } from '../../alert/services/index';
import {User} from './../../model/user';
import {UserData} from './../../model/userData';
import {Router} from '@angular/router';
import {FormGroup,FormBuilder,Validators, FormControl} from '@angular/forms';
import {TypeRegisterService} from './../../services/typeRegisterService/typeRegister.service';
import { UtilsService} from './../../services/utilsServices/utils.service';
import { ProviderService} from './../../services/providerService/provider.service';
declare var $:any;

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user:User;
  idUser:string;
  userName : string;
  mensaje :String;
  typeRegister: String;
  localities:any;
  form: FormGroup;

  constructor(public userService: UserService, private router:Router,public messageService : MessageService,public alertService: AlertService,private translate: TranslateService,private typeRegisterService: TypeRegisterService,private utilsServices: UtilsService,private formBuilder: FormBuilder,private providerService: ProviderService){
  }

  isFieldValid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  isFieldHasError(field:string,condition:string){
    return this.form.get(field).hasError(condition) && this.form.get(field).touched;;
  }

  displayFieldCss(field: string) {
    return {
      'is-invalid': this.isFieldValid(field)
    };
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  reset(){
    this.form.reset();
  }
  
    ngOnInit() {
      this.utilsServices.localities().subscribe(data =>this.resultLocalities(data));
      $('#login').hide();
      $('#register').hide();
      $('#backToHome').show();
      this.typeRegisterService.currentMessage.subscribe(message => this.typeRegister = message);
      this.checkTypeRegister();
      this.form = this.formBuilder.group({
        name:[null,[Validators.required]],
        surname:[null,[Validators.required]],
        cuit:[null,[Validators.required]],
        email:[null,[Validators.required,Validators.email]],
        street:[null,[Validators.required]],
        number:[null,[Validators.required]],
        floor:['0',[Validators.required]],
        countryCode:['54',[Validators.required]],
        areaCode:['011',[Validators.required]],
        localNumber:[null,[Validators.required]],
        locality:[null,[Validators.required]],
        length:[null,[Validators.required]],
        latitude:[null,[Validators.required]],
        password:[null,[Validators.required]],
        repassword:[null,[Validators.required]]        
      },{validator: this.checkIfMatchingPasswords('password', 'repassword')})
    }

    checkIfMatchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
      return (group: FormGroup) => {
        let passwordInput = group.controls[passwordKey],
            passwordConfirmationInput = group.controls[passwordConfirmationKey];
        if (passwordInput.value !== passwordConfirmationInput.value) {
          return passwordConfirmationInput.setErrors({notEquivalent: true})
        }
      }
    }

    checkTypeRegister(){
      if("PROVIDER"==this.typeRegister){
        $('#newProvider').show();
      }
      if("CLIENT"==this.typeRegister){
        $('#newClient').show();
      }
    }
    resultLocalities(data){
      this.localities= JSON.parse(data._body);
    }

    newUser() {
      if(this.form.valid){
        this.registerAccordingToRole();
      }else{
        this.validateAllFormFields(this.form);
        this.showModal(this.translate.instant("1004"));
      }
    }

    registerAccordingToRole(){
      if("PROVIDER"==this.typeRegister){
        this.registerProvider()
      }
      if("CLIENT"==this.typeRegister){
        this.registerClient()
      }
    }
  
    //call this wherever you want to close modal
  
    private sendData(): void {
      this.messageService.changeMessage(this.user);
    }  
  
    search()  {
      this.router.navigate(['menus']);
    }
  
    showModal(msg){
      this.mensaje= msg;
      if(window.screen.width <=767){
        $('#modal-update').modal('show')
      }
    }

    showError(msgError){
      this.alertService.clear();
      this.alertService.error(msgError);
      this.showModal(msgError);
    }

    registerProvider(){
     this.providerService.registerProvider(this.form.value.password,this.form.value.name,this.form.value.surname,this.form.value.cuit,this.form.value.email,this.form.value.countryCode,this.form.value.areaCode,this.form.value.localNumber,this.form.value.locality,this.form.value.street, this.form.value.number,this.form.value.floor,this.form.value.latitude,this.form.value.length).subscribe(data => 
        {this.result(data)},
      err => {
        this.form.reset();
        this.showModal(this.translate.instant(JSON.parse(err._body).code.toString()));
      });
    
     
    }

    registerClient(){
     this.userService.register(this.form.value.password,this.form.value.name,this.form.value.surname,this.form.value.cuit,this.form.value.email,this.form.value.countryCode,this.form.value.areaCode,this.form.value.localNumber,this.form.value.locality,this.form.value.street, this.form.value.number,this.form.value.floor,this.form.value.latitude,this.form.value.length).subscribe(data => 
        {this.result(data)},
      err => {
        this.form.reset();
        this.showModal(this.translate.instant(JSON.parse(err._body).code.toString()));
      });
    }

    result(data){
      this.user= Object.assign(new User,JSON.parse(data._body));
      this.sendData();
      $('#modalLogin').modal('hide');
      $('#modalRegister').modal('hide');
      if(this.typeRegister=="PROVIDER"){
        this.typeRegisterService.changeMessage("PROVIDER");
      }else{
        this.typeRegisterService.changeMessage("CLIENT");
      }
      this.router.navigate(['users']);
    }
}