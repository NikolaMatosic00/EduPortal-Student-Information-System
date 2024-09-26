export class Termin {
  predmetId: number;
  tipPolaganjaId: number;
  napomena: string;
  nazivRoka: string;

  constructor(args: any = {}) {
    this.predmetId = args.predmetId;
    this.tipPolaganjaId = args.tipPolaganjaId;
    this.napomena = args.napomena;
    this.nazivRoka = args.nazivRoka;
  }
}
