import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './page/home/home.component';
import { NgModule } from '@angular/core';
import { PageNotFoundComponent } from '@fenbi/ng-common';

const appRoutes: Routes = [{
    path: '',
    component: HomeComponent,
    data: {
        key: 'home',
    },
}, {
    path: '**',
    component: PageNotFoundComponent,
    data: {
        key: 'notFound',
    },
}];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes, {
            useHash: true
        })
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {
}
