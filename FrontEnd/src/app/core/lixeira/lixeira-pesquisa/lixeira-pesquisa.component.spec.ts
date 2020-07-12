import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LixeiraPesquisaComponent } from './lixeira-pesquisa.component';

describe('LixeiraPesquisaComponent', () => {
  let component: LixeiraPesquisaComponent;
  let fixture: ComponentFixture<LixeiraPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LixeiraPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LixeiraPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
