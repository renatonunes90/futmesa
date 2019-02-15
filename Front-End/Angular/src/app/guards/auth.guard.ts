import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { environment } from '../config/environments/environment';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private oauthService: OAuthService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem(environment.localStorageAuth)) {
      return true;
    }

    this.router.navigate(['/' + environment.routes.login]);

    return false;
  }
}
