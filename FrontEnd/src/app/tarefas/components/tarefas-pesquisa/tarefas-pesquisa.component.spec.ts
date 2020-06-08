import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefasPesquisaComponent } from './tarefas-pesquisa.component';

describe('TarefasPesquisaComponent', () => {
  let component: TarefasPesquisaComponent;
  let fixture: ComponentFixture<TarefasPesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarefasPesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TarefasPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
