import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  showLoginForm:boolean=true;

  constructor() { }

  ngOnInit(): void {
  }

  toggleAuthForm=()=>
  {
      this.showLoginForm=!this.showLoginForm;
  }

  onLogin=(loginForm:NgForm)=>
  {
      console.log(loginForm.controls["email"].value);
      console.log(loginForm.controls["password"].value);
  }

  onRegister=(registerForm:NgForm)=>
  {
      console.log(registerForm.controls["name"].value);
      console.log(registerForm.controls["email"].value);
      console.log(registerForm.controls["password"].value);
  }

}
