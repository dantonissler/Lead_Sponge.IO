import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunilDeVendasComponent } from './funil-de-vendas.component';

describe('FunilDeVendasComponent', () => {
  let component: FunilDeVendasComponent;
  let fixture: ComponentFixture<FunilDeVendasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunilDeVendasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunilDeVendasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
