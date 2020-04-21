import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UrlShortenerComponent } from './url-shortener/url-shortener.component'

const routes: Routes = [
  { path: 'shorten', component: UrlShortenerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
