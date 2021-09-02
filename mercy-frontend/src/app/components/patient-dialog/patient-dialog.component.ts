import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Patient } from 'src/app/models/patient.model';

@Component({
  selector: 'mercy-patient-dialog',
  templateUrl: './patient-dialog.component.html',
  styleUrls: ['./patient-dialog.component.scss']
})
export class PatientDialogComponent implements OnInit {

  patientForm: FormGroup;
  patient: Patient = new Patient()

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<PatientDialogComponent>,
  ) { }

  ngOnInit(): void {
    this.initForm();
  }
 
  initForm() {
    this.patientForm = this.formBuilder.group({
      name: [null , Validators.required],
      cpf: [null , Validators.required],
      phoneNumber: [null , Validators.required],
      doctor: [null , Validators.required],
      zipCode: [null , Validators.required],
      streetName: [null , Validators.required],
      number: [null , Validators.required],
      additionalInfo: [null , Validators.required],
      neighborhood: [null , Validators.required],
      city: [null , Validators.required],
      state: [null , Validators.required],
      observation: [null , Validators.required]
    });
  }

  onSubmit() {
    if (this.patientForm.valid){
      this.setPatientData()
      
    }
  }

  setPatientData(){
      let formData = this.patientForm.getRawValue()
  }

  getUserId(){
    const sessionData = JSON.parse(localStorage.getItem('currentUser'));
    return sessionData.user.id
  }

  close(){
    this.dialogRef.close()
  }
}
