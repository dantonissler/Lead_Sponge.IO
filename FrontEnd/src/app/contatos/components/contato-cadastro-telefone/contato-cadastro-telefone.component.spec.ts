import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContatoCadastroTelefoneComponent } from './contato-cadastro-telefone.component';

describe('ContatoCadastroTelefoneComponent', () => {
  let component: ContatoCadastroTelefoneComponent;
  let fixture: ComponentFixture<ContatoCadastroTelefoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContatoCadastroTelefoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContatoCadastroTelefoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
