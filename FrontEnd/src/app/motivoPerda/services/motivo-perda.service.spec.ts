import { TestBed } from '@angular/core/testing';

import { MotivoPerdaService } from './motivo-perda.service';

describe('MotivoPerdaService', () => {
  let service: MotivoPerdaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MotivoPerdaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
