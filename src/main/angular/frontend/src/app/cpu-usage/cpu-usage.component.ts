import { Component, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-cpu-usage',
  standalone: true,
  imports: [
    BaseChartDirective,
    MatIconModule,
    MatButtonModule,
    MatMenuModule
  ],
  templateUrl: './cpu-usage.component.html',
  styleUrl: './cpu-usage.component.scss'
})
export class CpuUsageComponent {

  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  public barChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.4,
      },
    },
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      x: {},
      y: {
        min: 10,
        ticks: {
          callback: function(value, index, values) {
            return value + ' %';
          }
        }
      },
    },
    plugins: {
      legend: {
        display: false
      },
      title: {
        display: true,
        text: 'CPU Usage',
        font: {
          size: 20
        },
        padding: {
          bottom: 20
        }
      },
    
    },
  };

  public barChartLabels: string[] = [
    '04:00',
    '08:00',
    '12:00',
    '16:00',
    '20:00',
    '30 Apr',
  ];
  public barChartType: ChartType = 'bar';

  public barChartData: ChartData<'bar'> = {
    labels: this.barChartLabels,
    datasets: [
      { data: [65, 59, 80, 81, 56, 55, 40] },
    ],
  };

  // events
  public chartClicked({
    event,
    active,
  }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }

  public chartHovered({
    event,
    active,
  }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }

  public randomize(): void {
    this.barChartType = this.barChartType === 'bar' ? 'line' : 'bar';
  }

}
