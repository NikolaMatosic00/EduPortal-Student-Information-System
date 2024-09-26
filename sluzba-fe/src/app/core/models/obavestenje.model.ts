export class Obavestenje {
  idPredmet: number;
  tekst: string;

  constructor(args: any = {}) {
    this.idPredmet = args.idPredmet;
    this.tekst = args.tekst;
  }
}
