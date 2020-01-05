import { Injectable } from '@angular/core';
import { Profile } from './profile.model';

@Injectable()
export class ProfileService {

    private profile: Profile = new Profile('name', 'surname', 'email', 23214, 32, 123);

    constructor() { }

    editProfile(p: Profile) {
        console.log("Editing profile " + p.email);
    }

    deleteProfile(p: Profile) {
        console.log("Deleting profile " + p.email);
    }
}