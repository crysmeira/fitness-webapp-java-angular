import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class StatisticsService {
    
    constructor(private http: HttpClient,
                private authService: AuthService) { }

    loadStatistics(numDays: number) {
        const headers = new HttpHeaders({ authorization : sessionStorage.getItem(this.authService.AUTH_TOKEN_SESSION_ATTRIBUTE_NAME) });
        const email = sessionStorage.getItem(this.authService.EMAIL_SESSION_ATTRIBUTE_NAME);

        return this.http.get('http://localhost:8080/statistics/' + email + '/' + numDays, {headers});
    }

}