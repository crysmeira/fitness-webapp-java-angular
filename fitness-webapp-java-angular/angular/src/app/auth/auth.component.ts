import { Component } from '@angular/core';

import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { MatSnackBar } from '@angular/material';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {

  credentials = {email: '', password: ''};
  isLoginMode = true;

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private authService: AuthService, 
              private _snackBar: MatSnackBar,
              private router: Router) { }

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit(form: NgForm) {
    this.credentials.email = form.controls['email'].value;
    this.credentials.password = form.controls['password'].value;
    form.reset();

    if (this.isLoginMode) {
      this.login();
    } else {
      this.signup();
    }
  }

  login() {
    this.authService.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/home');
    });

    return false;
  }

  signup() {
    this.authService.signup(this.credentials)
                  .subscribe(response => {
                    this.openSnackBar('Successfully registered email: ' + response['email']);
                  }, error => {
                    this.openSnackBar(error.error.detail);
                  });
    this.isLoginMode = true;
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/home');
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, null, {
      duration: 5000,
    });
  }
}
