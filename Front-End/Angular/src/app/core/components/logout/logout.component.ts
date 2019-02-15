import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';

/**
 * Only calls logout operation.
 */
@Component({
    selector: 'app-logout',
    template: ''
})
export class LogoutComponent implements OnInit {

    constructor(private authService: AuthenticationService) {
    }

    ngOnInit() {
        this.authService.logout();
    }

}
