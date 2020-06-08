import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CampanhasCadastroComponent } from './campanhas-cadastro.component';

describe('CampanhasCadastroComponent', () => {
  let component: CampanhasCadastroComponent;
  let fixture: ComponentFixture<CampanhasCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CampanhasCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CampanhasCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
