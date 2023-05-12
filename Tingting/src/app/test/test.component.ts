import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  userList = '';

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  // get user list from local api /getUsers/users
  getUsers(name?: string) {
    const url = "http://localhost:3000/getUser/users";
    const method = 'GET';
    const options = name ? { params: { 'name': name} } : {};
    this.http.request(method, url, options).subscribe((res: any) => {
      console.log(res);
      this.userList = JSON.stringify(res);
    }, err => {
      console.error(err);
    });
  }

}