import { TestBed } from '@angular/core/testing';

import { CampanhasService } from './campanhas.service';

describe('CampanhasService', () => {
  let service: CampanhasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CampanhasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
