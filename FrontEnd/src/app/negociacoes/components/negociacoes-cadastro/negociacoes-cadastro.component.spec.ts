import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociacoesCadastroComponent } from './negociacoes-cadastro.component';

describe('NegociacoesCadastroComponent', () => {
  let component: NegociacoesCadastroComponent;
  let fixture: ComponentFixture<NegociacoesCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociacoesCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociacoesCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
