import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProfileService } from './profile.service';
import { Router } from '@angular/router';
import { Profile } from './profile.model';
import { AuthService } from '../auth/auth.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {  

  @Input() firstName;
  @Input() lastName;
  @Input() email;
  @Input() birthDate;
  @Input() height;
  @Input() weight;

  constructor(private profileService: ProfileService,
              private authService: AuthService,
              private router: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.onLoadProfile();
  }

  onLoadProfile() {
    this.profileService.loadProfile()
                .subscribe(profile => {
                  this.firstName = profile.firstName;
                  this.lastName = profile.lastName;
                  this.email = profile.email;
                  this.birthDate = profile.birthDate;
                  this.height = profile.height;
                  this.weight = profile.weight;
                }, error => {
                  this.openSnackBar(error.error.detail);
                });
  }

  onUpdateProfile(form: NgForm) {
    const value = form.value;
    const profile = new Profile(value.firstName, value.lastName, form.controls['email'].value, value.birthDate, value.height, value.weight);
    this.profileService.editProfile(profile)
                    .subscribe(response => {
                      this.openSnackBar('User was successfully updated');
                    }, error => {
                      this.openSnackBar(error.error.detail);
                    });
  }

  onDeleteProfile() {
    this.profileService.deleteProfile()
                    .subscribe(response => {
                      this.openSnackBar('User was successfully deleted');
                    }, error => {
                      this.openSnackBar(error.error.detail);
                    });
    this.authService.logout();
    this.router.navigate(['/home']);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, null, {
      duration: 5000,
    });
  }
}
