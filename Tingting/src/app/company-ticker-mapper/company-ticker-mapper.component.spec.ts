import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyTickerMapperComponent } from './company-ticker-mapper.component';

describe('CompanyTickerMapperComponent', () => {
  let component: CompanyTickerMapperComponent;
  let fixture: ComponentFixture<CompanyTickerMapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyTickerMapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyTickerMapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
