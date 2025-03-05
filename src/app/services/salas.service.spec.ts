import { TestBed } from '@angular/core/testing';

import { SalasService } from './salas.service';

describe('SalasService', () => {
  let service: SalasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
