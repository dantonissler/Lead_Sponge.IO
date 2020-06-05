import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContatoCadastroEmailComponent } from './contato-cadastro-email.component';

describe('ContatoCadastroEmailComponent', () => {
  let component: ContatoCadastroEmailComponent;
  let fixture: ComponentFixture<ContatoCadastroEmailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContatoCadastroEmailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContatoCadastroEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
