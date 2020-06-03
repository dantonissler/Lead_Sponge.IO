import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from '../../components/dashboard/dashboard.component';

import { SharedModule } from '../shared.module';
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
