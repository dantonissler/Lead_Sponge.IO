import { MessageComponent } from '../components/message.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [MessageComponent],
  exports: [MessageComponent]
})
export class SharedModule { }
