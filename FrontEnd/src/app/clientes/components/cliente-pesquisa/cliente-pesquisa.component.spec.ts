import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientePesquisaComponent } from './cliente-pesquisa.component';

describe('ClientePesquisaComponent', () => {
  let component: ClientePesquisaComponent;
  let fixture: ComponentFixture<ClientePesquisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientePesquisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientePesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
