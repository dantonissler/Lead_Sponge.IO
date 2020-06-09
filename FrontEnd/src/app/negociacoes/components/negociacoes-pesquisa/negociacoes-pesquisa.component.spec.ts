import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociacoesPesquisaComponent } from './negociacoes-pesquisa.component';

describe('negociacoesPesquisaComponent', () => {
  let component: NegociacoesPesquisaComponent;
  let fixture: ComponentFixture<NegociacoesPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociacoesPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociacoesPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
