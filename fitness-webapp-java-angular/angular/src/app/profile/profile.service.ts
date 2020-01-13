import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Profile } from './profile.model';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class ProfileService {

    constructor(private http: HttpClient,
                private authService: AuthService) { }

    loadProfile() {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.get<Profile>('http://localhost:8080/users/' + email, {headers});
    }

    editProfile(profile: Profile) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.patch<Profile>('http://localhost:8080/users/' + email, profile, {headers});
    }

    deleteProfile() {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.delete('http://localhost:8080/users/' + email, {headers});
    }
}