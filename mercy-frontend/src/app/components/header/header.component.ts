import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { MessagesEnum } from 'src/app/util/messages.enum';
import { Views } from 'src/app/util/views.enum';

@Component({
  selector: 'mercy-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: string;

  constructor(
    private router: Router,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  openWipWarning(){
    this.snackbar.open(MessagesEnum.WipWarning, 'Fechar');

  }

  logout(){
    localStorage.clear()
    this.router.navigate([Views.login.url])
  }

  goToPatients(){
    this.router.navigate([Views.patients.url])
  }

}
