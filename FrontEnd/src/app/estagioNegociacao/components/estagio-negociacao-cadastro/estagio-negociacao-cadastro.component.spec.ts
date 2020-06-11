import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstagioNegociacaoCadastroComponent } from './estagio-negociacao-cadastro.component';

describe('EstagioNegociacaoCadastroComponent', () => {
  let component: EstagioNegociacaoCadastroComponent;
  let fixture: ComponentFixture<EstagioNegociacaoCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstagioNegociacaoCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstagioNegociacaoCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
