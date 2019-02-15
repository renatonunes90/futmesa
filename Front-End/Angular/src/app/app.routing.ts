import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './core/components/login/login.component';
import { LogoutComponent } from './core/components/logout/logout.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { NotFoundComponent } from './core/components/not-found/components/not-fount.component';
import { environment } from './config/environments/environment';

const appRoutes: Routes = [
  { path: '', redirectTo: '/' + environment.routes.home, pathMatch: 'full', canActivate: [AuthGuard] },
  { path: environment.routes.login, component: LoginComponent },
  { path: environment.routes.logout, component: LogoutComponent },
  { path: environment.routes.home, component: HomeComponent, canActivate: [AuthGuard] },
  { path: environment.routes.notFound, component: NotFoundComponent },
  { path: '**', redirectTo: '/' + environment.routes.notFound }

];

export const routing = RouterModule.forRoot(appRoutes);

