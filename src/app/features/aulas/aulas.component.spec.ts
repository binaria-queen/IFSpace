import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AulasComponent } from './aulas.component';

describe('AulasComponent', () => {
  let component: AulasComponent;
  let fixture: ComponentFixture<AulasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AulasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AulasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
