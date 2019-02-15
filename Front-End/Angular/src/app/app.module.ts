// Route
import { routing } from './app.routing';
import { AuthGuard } from './guards/auth.guard';

// Modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';
import { FormsModule } from '@angular/forms';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { TreeModule } from 'angular-tree-component';

// Components
import { HeaderComponent } from './core/components/header/components/header.component';
import { NotFoundComponent } from './core/components/not-found/components/not-fount.component';
import { LoginComponent } from './core/components/login/login.component';
import { LogoutComponent } from './core/components/logout/logout.component';
import { HomeComponent } from './components/home/home.component';
import { AppComponent } from './app.component';

// Services
import { AuthenticationService } from './core/services/authentication.service';

// Interceptors
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { OAuthInterceptor } from './interceptors/oauth.interceptor';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ModalDialogModule } from 'ngx-modal-dialog';
import { ErrorDialogComponent } from './core/components/error/error.dialog.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HomeComponent,
    HeaderComponent,
    NotFoundComponent,
    ErrorDialogComponent
  ],
  entryComponents: [
    ErrorDialogComponent
  ],
  imports: [
    BrowserModule,
    ModalDialogModule.forRoot(),
    ReactiveFormsModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    routing,
    FormsModule,
    NgHttpLoaderModule.forRoot(),
    NgbModule.forRoot(),
    TreeModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  exports: [
    HeaderComponent
  ],
  providers: [
    AuthenticationService,
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: OAuthInterceptor, multi: true },
    Validators

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
