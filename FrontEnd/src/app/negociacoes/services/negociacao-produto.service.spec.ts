import { TestBed } from '@angular/core/testing';

import { NegociacaoProdutoService } from './negociacao-produto.service';

describe('NegociacaoProdutoService', () => {
  let service: NegociacaoProdutoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NegociacaoProdutoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
