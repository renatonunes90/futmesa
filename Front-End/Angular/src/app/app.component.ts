import { Component, OnInit } from '@angular/core';
import { Spinkit } from 'ng-http-loader';
import { AuthenticationService } from './core/services/authentication.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent implements OnInit {

  public spinkit = Spinkit;

  isLoggedIn$: Observable<boolean>;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.authenticationService.isLoggedIn;
  }
}
