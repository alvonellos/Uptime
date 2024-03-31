import { Component } from '@angular/core';
import { ServerStatusComponent } from '../server-status/server-status.component';
import { ConnectionsComponent } from '../connections/connections.component';
import { CpuUsageComponent } from '../cpu-usage/cpu-usage.component';
import { RamUsageComponent } from '../ram-usage/ram-usage.component';
import { TrafficComponent } from '../traffic/traffic.component';
import { SystemLoadComponent } from '../system-load/system-load.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ServerStatusComponent,
    ConnectionsComponent,
    CpuUsageComponent,
    RamUsageComponent,
    TrafficComponent,
    SystemLoadComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
