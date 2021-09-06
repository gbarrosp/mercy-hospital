import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Doctor } from 'src/app/models/doctor.model';
import { Patient } from 'src/app/models/patient.model';
import { DoctorService } from 'src/app/services/views/doctor.service';
import { PatientService } from 'src/app/services/views/patient.service';
import { ZipCodeService } from 'src/app/services/views/zipCode.service';
import { generalExceptionTreatment } from 'src/app/util/error-handler';
import { MessagesEnum } from 'src/app/util/messages.enum';

@Component({
  selector: 'mercy-patient-dialog',
  templateUrl: './patient-dialog.component.html',
  styleUrls: ['./patient-dialog.component.scss']
})
export class PatientDialogComponent implements OnInit {

  patientForm: FormGroup;
  patient: Patient = new Patient()
  loadingZipCode: boolean = false;
  invalidCpf: boolean = false;
  doctors: Doctor[];
  showSuffix: boolean;

  constructor(
    private zipCodeService: ZipCodeService,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private snackbar: MatSnackBar,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<PatientDialogComponent>,
  ) { }

  ngOnInit(): void {
    this.loadData()
    this.initForm();
  }

  loadData(){
    this.doctorService.getAllDoctors().subscribe(response => {
      this.doctors = response
    })
  }
 
  initForm() {
    this.patientForm = this.formBuilder.group({
      name: [null , Validators.required],
      cpf: [null , Validators.required],
      phoneNumber: [null , Validators.required],
      doctorCpf: [null , Validators.required],
      zipCode: [null , Validators.required],
      streetName: [null , Validators.required],
      number: [null , Validators.required],
      additionalInfo: [null],
      neighborhood: [null , Validators.required],
      city: [null , Validators.required],
      state: [null , Validators.required],
      observation: [null]
    });
    this.checkZipCodeOnChanges()
    this.validateCpf()
  }

  checkZipCodeOnChanges(){
    this.patientForm.controls['zipCode'].valueChanges.subscribe(zipCode => {
      if (zipCode.length === 8){
        this.loadingZipCode = true
        this.zipCodeService.getZipCodeInfo(zipCode).subscribe(response => {
          if (response){
            this.patientForm.patchValue({
              "city": response.city,
              "state": response.state,
              "streetName": response.streetName,
              "neighborhood": response.neighborhood
            })
          } else {
            this.snackbar.open(MessagesEnum.InvalidZipCode);
          }
          this.loadingZipCode = false
        })
      }
    })

  }

  onSubmit() {
    if (this.patientForm.valid){
      this.setPatientData()
      this.patientService.newPatient(this.patient).subscribe(response => {
        this.snackbar.open(MessagesEnum.PatientAdded);
        this.close(true)
      }, error => {
        this.snackbar.open(generalExceptionTreatment(error), 'Fechar');
      }
      )
    } else {
      this.snackbar.open(MessagesEnum.InvalidForm);
    }
  }

  validateCpf() {
    this.patientForm.controls['cpf'].valueChanges.subscribe(cpf => {
      if (cpf.length === 11){
        this.showSuffix = true
        this.invalidCpf = false
        let numbers = cpf.split("")
        var regExp = new RegExp(numbers[0], "g");
        if ((cpf.match(regExp) || []).length == 11) {
          this.raiseInvalidCpf()
        } else {
          let i = 10
          let firstValidatorSum = 0
          let secondValidatorSum = 0
          numbers.forEach((n, index) => {
            if (index < 9){
              firstValidatorSum += n * i
            } 
            if (index < 10){
              secondValidatorSum += n * (i +1)
            }
            i--
          })
          let validFirstValidor = firstValidatorSum * 10 % 11 == numbers[9] ?  true : false
          let validSecondValidor = secondValidatorSum * 10 % 11 == numbers[10] ?  true : false
          if (!validFirstValidor && !validSecondValidor){
            this.raiseInvalidCpf()
          }
        }
      } else {
        this.showSuffix = false
      }
    })
  }

  raiseInvalidCpf(){
    this.invalidCpf = true
    this.snackbar.open(MessagesEnum.InvalidCpf);
  }

  setPatientData(){
      let formData = this.patientForm.getRawValue()
      this.patient.name = formData.name
      this.patient.cpf = formData.cpf
      this.patient.phoneNumber = formData.phoneNumber
      this.patient.doctor = formData.doctorCpf
      this.patient.observation = formData.observation

      this.patient.address.city = formData.city
      this.patient.address.state = formData.state
      this.patient.address.streetName = formData.streetName
      this.patient.address.number = formData.number
      this.patient.address.additionalInfo = formData.additionalInfo
      this.patient.address.neighborhood = formData.neighborhood
      this.patient.address.zipCode = formData.zipCode
  }

  getDoctorByCpf(doctorCpf: string){
    return this.doctors.find(doctor => doctor.cpf === doctorCpf)
  }

  close(reload: boolean){
    this.dialogRef.close(reload)
  }
}
