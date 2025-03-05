import { TestBed } from '@angular/core/testing';

import { AulasService } from './aulas.service';

describe('AulasService', () => {
  let service: AulasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AulasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
