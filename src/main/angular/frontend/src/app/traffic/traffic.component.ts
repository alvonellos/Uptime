import { Component, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-traffic',
  standalone: true,
  imports: [
    BaseChartDirective,
    MatIconModule,
    MatMenuModule,
    MatButtonModule
  ],
  templateUrl: './traffic.component.html',
  styleUrl: './traffic.component.scss'
})
export class TrafficComponent {

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [2.3, 0, 1.3, 2.7, 0, 3, 0],
        label: 'Series A',
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      },
    ],
    labels: ['04:00', '08:00', '12:00', '16:00', '20:00', '30 Apr'],
  };

  public lineChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.2,
      },
    },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      x: {

      },
      y: {
        beginAtZero: true,
        ticks: {
          callback: function(value, index, values) {
            return value + ' Mbit/s';
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
        text: 'Traffic Usage',
        font: {
          size: 20
        },
        padding: {
          bottom: 20
        }
      }
    },
  };

  public lineChartType: ChartType = 'line';

  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

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

}
