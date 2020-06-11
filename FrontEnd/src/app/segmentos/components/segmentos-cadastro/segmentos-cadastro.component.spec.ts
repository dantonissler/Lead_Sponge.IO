import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SegmentosCadastroComponent } from './segmentos-cadastro.component';

describe('SegmentosCadastroComponent', () => {
  let component: SegmentosCadastroComponent;
  let fixture: ComponentFixture<SegmentosCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SegmentosCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SegmentosCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
