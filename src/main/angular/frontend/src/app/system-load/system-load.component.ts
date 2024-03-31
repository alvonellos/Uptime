import { Component, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-system-load',
  standalone: true,
  imports: [
    BaseChartDirective,
    MatMenuModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './system-load.component.html',
  styleUrl: './system-load.component.scss'
})
export class SystemLoadComponent {

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [0, 0.59, 0.80, 0.81, 0.56, 0.55, 0.40],
        label: 'Series A',
        backgroundColor: 'transparent',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      },
      {
        data: [0.28, 0.48, 0.40, 0.19, 0.86, 0.27, 0.90],
        label: 'Series B',
        backgroundColor: 'transparent',
        borderColor: '#406ba7',
        pointBackgroundColor: 'rgba(77,83,96,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(77,83,96,1)',
        fill: 'origin',
      },
      {
        data: [0.18, 0.48, 0.77, 0.90, 0.100, 0.27, 0.40],
        label: 'Series C',
        backgroundColor: 'transparent',
        borderColor: 'red',
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
        tension: 0.5,
      },
    },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      x: {

      },
      y: {

      },
    },

    plugins: {
      legend: {
        display: false
      },
      title: {
        display: true,
        text: 'System Load',
        font: {
          size: 20
        },
        padding: {
          bottom: 20
        }
      },
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
