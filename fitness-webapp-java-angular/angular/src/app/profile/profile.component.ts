import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProfileService } from './profile.service';
import { Profile } from './profile.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {  

  constructor(private profileService: ProfileService,
          private route: ActivatedRoute,
          private router: Router) { }

  ngOnInit() {
  }

  onUpdateProfile(form: NgForm) {
    const value = form.value;
    const profile = new Profile(value.firstName, value.lastName, value.email, value.birthday, value.height, value.weight);
    this.profileService.editProfile(profile);
  }

  onDeleteProfile(form: NgForm) {
    console.log("Deleting... " + form.value.email);
    const profile = new Profile('name', 'surname', 'email@email.com', 23214, 32, 123);
    this.profileService.deleteProfile(profile);
    this.router.navigate(['/auth']);
  }
}
