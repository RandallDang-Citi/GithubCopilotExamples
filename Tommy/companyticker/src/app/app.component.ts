import { Component } from '@angular/core';
import { TickerService } from './ticker.service';
import {shuffle} from 'lodash';

export interface ButtonStatus {
  key: string;
  selected: boolean;
  isCorrect: boolean;
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'companyticker';
  //inject ticker service 
  constructor(public tickerService: TickerService) {
  }

  
}
