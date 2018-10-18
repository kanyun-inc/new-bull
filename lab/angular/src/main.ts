import 'bootstrap/dist/css/bootstrap.css';
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
    enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule).then(() => {
    window['pageLoading'].go(100);
    delete window['pageLoading'];
});
