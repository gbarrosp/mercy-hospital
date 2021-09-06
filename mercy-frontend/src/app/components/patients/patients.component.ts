import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/views/patient.service';
import { PatientDialogComponent } from '../patient-dialog/patient-dialog.component';

@Component({
  selector: 'mercy-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.scss']
})
export class PatientsComponent implements OnInit, AfterViewInit{

  dialogRef: any;

  displayedColumns: string[] = ['name', 'cpf', 'doctor','phoneNumber', 'city'];
  dataSource = new MatTableDataSource<Patient>();
  patients: Patient[]

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private dialog: MatDialog,
    private patientService: PatientService
  ) { }

  ngOnInit(): void {
    this.loadPatients()
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  loadPatients(){
    this.patientService.getAllPatients().subscribe(response => {
      if (response){
        this.patients = response
        this.dataSource.data = this.patients
      }
    })
  }

  addPatient(){
    this.openDialog(null)
  }

  editPatient(patient: Patient){
    this.openDialog(patient)
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialog(data): void {
    this.dialogRef = this.dialog.open(PatientDialogComponent, {
      maxWidth: '1200px',
      maxHeight: '750px',
      height: '90vh',
      data: data
    });
    this.dialogRef.afterClosed().subscribe(response => {
      if (response){
        this.loadPatients()
      }
    })
  }
}