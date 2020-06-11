import { TestBed } from '@angular/core/testing';

import { EstagioNegociacaoService } from './estagio-negociacao.service';

describe('EstagioNegociacaoService', () => {
  let service: EstagioNegociacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstagioNegociacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
