import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstagioNegociacaoPesquisaComponent } from './estagio-negociacao-pesquisa.component';

describe('EstagioNegociacaoPesquisaComponent', () => {
  let component: EstagioNegociacaoPesquisaComponent;
  let fixture: ComponentFixture<EstagioNegociacaoPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstagioNegociacaoPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstagioNegociacaoPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
