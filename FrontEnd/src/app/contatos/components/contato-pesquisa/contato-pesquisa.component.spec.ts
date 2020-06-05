import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContatoPesquisaComponent } from './contato-pesquisa.component';

describe('ContatoPesquisaComponent', () => {
  let component: ContatoPesquisaComponent;
  let fixture: ComponentFixture<ContatoPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContatoPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContatoPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
