import { SegurancaModule } from './usuarios/modules/seguranca.module';
import { CoreModule } from './core/modules/core.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { ProdutosPesquisaComponent } from './produtos/components/produtos-pesquisa/produtos-pesquisa.component';
import { ProdutosCadastroComponent } from './produtos/components/produtos-cadastro/produtos-cadastro.component';
import { TarefasCadastroComponent } from './tarefas/components/tarefas-cadastro/tarefas-cadastro.component';
import { TarefasPesquisaComponent } from './tarefas/components/tarefas-pesquisa/tarefas-pesquisa.component';

@NgModule({
  declarations: [
    AppComponent,
    ProdutosPesquisaComponent,
    ProdutosCadastroComponent,
    TarefasCadastroComponent,
    TarefasPesquisaComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CoreModule,
    AppRoutingModule,
    SegurancaModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
