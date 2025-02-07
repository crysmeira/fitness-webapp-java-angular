import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  collapsed = true;

  constructor(private router: Router,
              private authService: AuthService) { }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }

}
