import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UrlShortenerService } from './url-shortener.service';

@Component({
  selector: 'url-shortener',
  templateUrl: './url-shortener.component.html',
  styleUrls: ['./url-shortener.component.css'],
})
export class UrlShortenerComponent implements OnInit {

  urlShortenerForm : FormGroup
  url : string
  alias : string

  constructor( private formBuilder: FormBuilder, private urlShortenerService: UrlShortenerService ) { }

  ngOnInit() {
    this.urlShortenerForm = this.formBuilder.group({
      url: ['null' , [Validators.required,
                    Validators.minLength(10),
                    Validators.minLength(10),
                    Validators.maxLength(256),
                    (this.urlShortenerService.validateUrl(this.url) == true) ?  null : { 'invalid': true } ]],
      shortenedUrl: [null , [Validators.required,
                   Validators.minLength(5),
                   Validators.maxLength(20),
                   Validators.pattern('^[A-Za-z][A-Za-z0-9]*')]]
    })
  }
  onSubmit() {
    this.urlShortenerService.createUrl(this.url, this.alias);
  }
}
