import { Component, OnInit } from '@angular/core';
import { CONSTANTS } from './header.constants';
import { environment } from '../../../../config/environments/environment';

@Component({
    selector: 'app-header',
    templateUrl: './header.view.component.html',
    styleUrls: ['./header.style.component.css']
})
export class HeaderComponent implements OnInit {
    public constants = CONSTANTS;

    public costCenterName: string;
    public envName: string;

    constructor() {
    }

    ngOnInit() {
        this.envName = environment.environmentName;
    }
}
