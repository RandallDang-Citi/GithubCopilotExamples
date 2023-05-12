import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-company-ticker-mapper',
  templateUrl: './company-ticker-mapper.component.html',
  styleUrls: ['./company-ticker-mapper.component.scss']
})
export class CompanyTickerMapperComponent implements OnInit {
  companyTickerMap = {
    'AAPL': 'Apple',
    'MSFT': 'Microsoft',
    'GOOG': 'Google',
    'Microsoft': 'MSFT',
    'Apple': 'AAPL',
    'Google': 'GOOG'
  };
  companyTikerList = this.getKeyName();
  firstClick = '';
  message = 'Congratulations';

  constructor() { }

  ngOnInit(): void {
    this.companyTikerList.sort(function(){
      return Math.random()-0.5
    })
  }

  // change order of list
  changeOrder() {
    this.companyTikerList.reverse();
  }
  
  // get key list from companyTickerMap
  getKeyName() {
    return Object.keys(this.companyTickerMap);
  }

  // btn click event
  btnClick(clickedItem: any) {
    if (this.firstClick === '') {
      this.resetBackgroundColor();
      this.setBackgroundColorRed(clickedItem, 'blue');
      this.firstClick = clickedItem;
    } else {
      if (this.isValue(this.firstClick, clickedItem)) {
        this.removeItem(clickedItem);
        this.removeItem(this.firstClick);
        this.firstClick = '';
      } else {
        this.setBackgroundColorRed(this.firstClick, 'red');
        this.setBackgroundColorRed(clickedItem, 'red');
        this.firstClick = '';
      }
    }
  }

  // reset background color to null
  resetBackgroundColor() {
    this.companyTikerList.forEach(element => {
      this.setBackgroundColorRed(element, null);
    });
  }

  // judge if param2 was param1's value
  isValue(key: string, value: string) {
    return this.companyTickerMap[key] === value;
  }

  // set background color
  setBackgroundColorRed(clickedItem: any, color: string) {
    const element = document.getElementById(clickedItem);
    element.style.backgroundColor = color;
  }

  // remove item according to clicked item
  removeItem(clickedItem: any) {
    const element = document.getElementById(clickedItem);
    element.remove();
    delete this.companyTickerMap[clickedItem];
    this.companyTikerList = this.getKeyName();
  }

}
