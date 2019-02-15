import { Component, Input, } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({ selector: 'app-modal-content', templateUrl: './error.dialog.component.html' })
export class ErrorDialogComponent {

    @Input() public error;

    constructor(public activeModal: NgbActiveModal) { }

}
