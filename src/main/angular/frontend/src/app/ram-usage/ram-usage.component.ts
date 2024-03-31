import { Component, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';

@Component({
  selector: 'app-ram-usage',
  standalone: true,
  imports: [
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    BaseChartDirective
  ],
  templateUrl: './ram-usage.component.html',
  styleUrl: './ram-usage.component.scss'
})
export class RamUsageComponent {


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
        beginAtZero: true,
        ticks: {
          callback: function (value, index, values) {
            return value + ' GB';
          },
        },
        max: 44
      },
    },
    plugins: {
      legend: {
        display: false
      },
      title: {
        display: true,
        text: 'Memory Usage',
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
      {
        data: [4, 9, 15, 18, 25, 22, 15],
        backgroundColor: '#406ba7'
      },
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
