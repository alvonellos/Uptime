import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-name',
  standalone: true,
  imports: [
    MatCardModule
  ],
  templateUrl: './name.component.html',
  styleUrl: './name.component.scss'
})
export class NameComponent {

}
