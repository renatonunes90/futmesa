import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../config/environments/environment';
import { Login } from '../models/login.model';
import { Token } from '../models/interfaces/token.model';
import { User } from '../models/interfaces/user.model';

@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    public login(login: Login): void {
        /**return this.http.post<Token>(
            environment.environmentHost.concat(environment.authenticationPath),
            `username=${login.username}&password=${login.password}`,
            {
                headers: new HttpHeaders({
                    'Content-Type': 'application/x-www-form-urlencoded'
                }),
                observe: 'response'
            }
        );*/
    }

    public logout() {
        localStorage.removeItem(environment.localStorageAuth);
        window.location.href = environment.routes.login;
    }

    get isLoggedIn() {
        const ls = localStorage.getItem(environment.localStorageAuth);
        const loggedIn = new BehaviorSubject<boolean>(ls != null);
        return loggedIn.asObservable();
    }


}
