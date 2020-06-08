import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OportunidadesCadastroComponent } from './oportunidades-cadastro.component';

describe('OportunidadesCadastroComponent', () => {
  let component: OportunidadesCadastroComponent;
  let fixture: ComponentFixture<OportunidadesCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OportunidadesCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OportunidadesCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
