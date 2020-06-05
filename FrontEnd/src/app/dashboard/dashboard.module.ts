import { SharedModule } from '../core/modules/shared.module';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { NgModule } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    /* PanelModule, */
    /* ChartModule, */
    SharedModule,
    DashboardRoutingModule
  ],
  declarations: [DashboardComponent],
  providers: [ DecimalPipe ]
})
export class DashboardModule { }
