import { TestBed } from '@angular/core/testing';

import { LixeiraService } from './lixeira.service';

describe('LixeiraService', () => {
  let service: LixeiraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LixeiraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
