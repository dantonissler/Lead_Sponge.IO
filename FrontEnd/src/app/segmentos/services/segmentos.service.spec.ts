import { TestBed } from '@angular/core/testing';

import { SegmentosService } from './segmentos.service';

describe('SegmentosService', () => {
  let service: SegmentosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SegmentosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
