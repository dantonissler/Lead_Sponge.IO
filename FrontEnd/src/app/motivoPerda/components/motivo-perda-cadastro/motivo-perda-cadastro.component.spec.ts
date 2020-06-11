import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MotivoPerdaCadastroComponent } from './motivo-perda-cadastro.component';

describe('MotivoPerdaCadastroComponent', () => {
  let component: MotivoPerdaCadastroComponent;
  let fixture: ComponentFixture<MotivoPerdaCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MotivoPerdaCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MotivoPerdaCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
