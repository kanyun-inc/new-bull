import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-main-nav',
    templateUrl: './main-nav.component.html',
    styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent implements OnInit {

    public navLink: string;

    constructor(private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit() {
        this.router.events.subscribe(event => {
            let currentRoute = this.route.root;
            while (currentRoute.children[0] !== undefined) {
                currentRoute = currentRoute.children[0];
            }

            const data = (currentRoute.snapshot && currentRoute.snapshot.data) || {};
            this.navLink = data['navLink'];
        });
    }
}
