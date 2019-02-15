import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from '../core/services/authentication.service';
import { environment } from '../config/environments/environment';
import { ErrorDialogComponent } from '../core/components/error/error.dialog.component';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService, private modalService: NgbModal) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err) {

                // only handles non-login errors
                if (err.url !== environment.environmentHost.concat(environment.authenticationPath)) {
                    switch (err.status) {
                        case 401:
                            // Unauthorized
                            this.authenticationService.logout();
                            location.reload(true);
                            break;
                        default:
                            console.error(this.translateError(err));
                            break;
                    }
                }

                return throwError(err);
            }
        }));
    }

    private translateError({ error, message }): string {
        let errorMsg = '';
        if (!error && error.length > 0 && !error[0].userMessage) {
            errorMsg = error[0].userMessage;
        } else if (!message) {
            errorMsg = message;
        } else {
            errorMsg = 'Erro desconhecido no servidor.';
        }
        return errorMsg;
    }
}
