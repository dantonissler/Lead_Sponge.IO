import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioDetalharComponent } from './usuario-detalhar.component';

describe('UsuarioDetalharComponent', () => {
  let component: UsuarioDetalharComponent;
  let fixture: ComponentFixture<UsuarioDetalharComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioDetalharComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioDetalharComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
