import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CampanhasPesquisaComponent } from './campanhas-pesquisa.component';

describe('CampanhasPesquisaComponent', () => {
  let component: CampanhasPesquisaComponent;
  let fixture: ComponentFixture<CampanhasPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CampanhasPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CampanhasPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
