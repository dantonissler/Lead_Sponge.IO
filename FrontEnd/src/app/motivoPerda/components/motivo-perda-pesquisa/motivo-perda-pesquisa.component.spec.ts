import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MotivoPerdaPesquisaComponent } from './motivo-perda-pesquisa.component';

describe('MotivoPerdaPesquisaComponent', () => {
  let component: MotivoPerdaPesquisaComponent;
  let fixture: ComponentFixture<MotivoPerdaPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MotivoPerdaPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MotivoPerdaPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
