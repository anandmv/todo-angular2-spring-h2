import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpInterceptorService, RESTService } from '@covalent/http';
import { MOCK_API } from '../config/api.config';
import { environment } from '../environments/environment';

@Injectable()
export class TodoService extends RESTService<any> {

  constructor(private _http: HttpInterceptorService) {
    super(_http, {
      baseUrl: environment.api,
      path: '/todo',
    });
  }

}
