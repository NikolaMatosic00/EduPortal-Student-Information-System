export class User {
  ime: string;
  prezime: string;
  brojIndeksa: string;

  constructor(args: any = {}) {
    this.ime = args.ime;
    this.prezime = args.prezime;
    this.brojIndeksa = args.brojIndeksa;
  }
}
