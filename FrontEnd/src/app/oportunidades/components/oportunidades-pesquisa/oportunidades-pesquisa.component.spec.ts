import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OportunidadesPesquisaComponent } from './oportunidades-pesquisa.component';

describe('OportunidadesPesquisaComponent', () => {
  let component: OportunidadesPesquisaComponent;
  let fixture: ComponentFixture<OportunidadesPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OportunidadesPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OportunidadesPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
