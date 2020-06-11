import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SegmentosPesquisaComponent } from './segmentos-pesquisa.component';

describe('SegmentosPesquisaComponent', () => {
  let component: SegmentosPesquisaComponent;
  let fixture: ComponentFixture<SegmentosPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SegmentosPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SegmentosPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
