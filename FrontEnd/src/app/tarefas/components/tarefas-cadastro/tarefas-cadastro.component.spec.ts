import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefasCadastroComponent } from './tarefas-cadastro.component';

describe('TarefasCadastroComponent', () => {
  let component: TarefasCadastroComponent;
  let fixture: ComponentFixture<TarefasCadastroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarefasCadastroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TarefasCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
