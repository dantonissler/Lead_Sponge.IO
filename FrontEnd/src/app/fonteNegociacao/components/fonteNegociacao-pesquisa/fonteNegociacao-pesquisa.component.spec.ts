import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FontesPesquisaComponent } from './fonteNegociacao-pesquisa.component';

describe('FontesPesquisaComponent', () => {
  let component: FontesPesquisaComponent;
  let fixture: ComponentFixture<FontesPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FontesPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FontesPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
