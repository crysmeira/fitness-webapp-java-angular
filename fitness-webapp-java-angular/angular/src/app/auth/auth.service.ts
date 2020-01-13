import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class AuthService {

    EMAIL_SESSION_ATTRIBUTE_NAME = 'authenticatedEmail';
    AUTH_TOKEN_SESSION_ATTRIBUTE_NAME = 'authenticationToken';

    constructor(private http: HttpClient,
        private _snackBar: MatSnackBar) { }

    authenticate(credentials, callback) {
        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.email + ':' + credentials.password)
        } : {});

        this.http.get('http://localhost:8080/users/auth', {headers}).subscribe(response => {
            if (response['name']) {
                sessionStorage.setItem(this.EMAIL_SESSION_ATTRIBUTE_NAME, response['name']);
                sessionStorage.setItem(this.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME, 'Basic ' + btoa(credentials.email + ':' + credentials.password));
            }
            return callback && callback();
        }, error => {
            this.openSnackBar('Email or password is wrong');
        });
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(this.EMAIL_SESSION_ATTRIBUTE_NAME);
        return !(user === null);
    }

    logout() {
        sessionStorage.removeItem(this.EMAIL_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(this.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME);
    }

    signup(credentials) {
        return this.http.post('http://localhost:8080/users', credentials);
    }

    openSnackBar(message: string) {
        this._snackBar.open(message, null, {
          duration: 3000,
        });
      }
}