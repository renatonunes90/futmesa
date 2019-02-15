import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';
import { environment } from '../../../config/environments/environment';
import { Login } from '../../models/login.model';

@Component({
    selector: 'app-login',
    templateUrl: './login.view.component.html'
})
export class LoginComponent {

    public usernameControl = new FormControl('');
    public passwordControl = new FormControl('');
    public loginObj = new Login();
    public errorMessage = '';

    constructor(private authService: AuthenticationService) {
    }

    public doLogin(): void {

        this.loginObj.username = this.usernameControl.value;
        this.loginObj.password = this.passwordControl.value;

        localStorage.setItem(environment.localStorageAuth, `{ "key":"token", "value":"true"}`);

        window.location.href = environment.routes.home;

        /**this.authService.login(this.loginObj)
            .subscribe(
                (resp) => {
                    this.token = resp.body;
                    localStorage.setItem(environment.localStorageAuth, `{ "key":"token", "value":"${this.token.access_token}"}`);

                    window.location.href = environment.routes.home;
                },
                (error) => {
                    console.log(error);
                    if (error.error !== undefined && error.error.error_description !== undefined) {
                        this.errorMessage = error.error.error_description;
                    } else {
                        this.errorMessage = 'Não foi possível conectar-se ao servidor de login, tente novamente mais tarde.';
                    }
                });*/
    }
}
