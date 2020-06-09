import { TestBed } from '@angular/core/testing';

import { NegociacoesService } from './negociacoes.service';

describe('NegociacoesService', () => {
  let service: NegociacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NegociacoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
