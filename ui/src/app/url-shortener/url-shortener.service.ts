import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UrlShortenerService{
    constructor(private httpClient: HttpClient,) {}

    createUrl(url : string, alias : string) {
        this.httpClient.post('http://localhost:8080/shorten', { url, alias }).subscribe(response => {
               console.log(response)
        });
    }
    validateUrl(url : string) : boolean {
        let valid : boolean
        this.httpClient.post('http://localhost:8080/shorten', url).subscribe(response => {
            valid = (response['status'] === '200') ? true : false
        })
        return valid
    }
}
