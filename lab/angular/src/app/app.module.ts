import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {
    UiModule,
    SiteModule,
    PagingModule,
    FormModule,
    SiteConfig,
    GalleryModule,
    GalleryConfig,
    AuthenticationService,
    AtmAuthenticationService
} from '@fenbi/ng-common';
import { AppRoutingModule } from 'app/app-routing.module';
import { AppComponent } from 'app/app.component';
import { HomeComponent } from 'app/page/home/home.component';
import { MainNavComponent } from 'app/lib/main-nav/main-nav.component';
import { APP_SITE_CONFIG } from './config/site.config';
import { APP_GALLERY_CONFIG } from './config/gallery.config';

@NgModule({
    declarations: [
        HomeComponent,
        AppComponent,
        MainNavComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        NgbModule.forRoot(),
        UiModule.forRoot(),
        SiteModule.forRoot(),
        PagingModule.forRoot(),
        FormModule.forRoot(),
        GalleryModule.forRoot(),
    ],
    providers: [{
        provide: SiteConfig,
        useValue: APP_SITE_CONFIG,
    }, {
        provide: GalleryConfig,
        useValue: APP_GALLERY_CONFIG,
    }, {
        provide: AuthenticationService,
        useClass: AtmAuthenticationService,
    }],
    bootstrap: [AppComponent]
})
export class AppModule {
}
