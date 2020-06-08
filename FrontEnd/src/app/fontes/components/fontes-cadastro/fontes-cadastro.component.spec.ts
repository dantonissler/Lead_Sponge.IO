import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FontesCadastroComponent } from './fontes-cadastro.component';

describe('FontesCadastroComponent', () => {
  let component: FontesCadastroComponent;
  let fixture: ComponentFixture<FontesCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FontesCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FontesCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
