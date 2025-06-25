import { ApiCepResponse } from "../api/viacep/viacep.request.model";

export interface Driver{
  driverId:number, 
  nameDriver:string, 
  cpf : string,
  cnh : string,
  cnhExpiration:string;
  email:string;
  ddd:string;
  phoneNumber:string;
  address:ApiCepResponse;
  isAtive:boolean;
  isSuperUser:boolean;
}