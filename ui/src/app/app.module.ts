import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ReactiveFormsModule } from '@angular/forms';
import { AppHeaderComponent } from './app-header/app-header.component'
import { UrlShortenerComponent } from './url-shortener/url-shortener.component';
import { HttpClientModule } from '@angular/common/http';
import { UrlShortenerService } from './url-shortener/url-shortener.service';

@NgModule({
  declarations: [
    AppComponent,
    UrlShortenerComponent,
    AppHeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [UrlShortenerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
