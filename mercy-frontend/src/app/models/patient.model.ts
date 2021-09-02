import { Address } from "./address.model";
import { Doctor } from "./doctor.model";

export class Patient {
    id: number;
    name: string;
    cpf: string;
    phoneNumber: string;
    observation: string;

    doctor: Doctor;
    address: Address = new Address()
}
