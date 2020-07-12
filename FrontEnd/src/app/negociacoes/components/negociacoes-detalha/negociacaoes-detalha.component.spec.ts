import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociacaoesDetalhaComponent } from './negociacaoes-detalha.component';

describe('NegociacaoesDetalhaComponent', () => {
  let component: NegociacaoesDetalhaComponent;
  let fixture: ComponentFixture<NegociacaoesDetalhaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociacaoesDetalhaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociacaoesDetalhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
