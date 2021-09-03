import { User } from "./user.model";

export class Doctor {
    id: number;
    name: string;
    phoneNumber: string;
    cpf: string;
    gender: string;

    user: User = new User();
}
