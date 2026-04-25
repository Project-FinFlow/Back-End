import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface LogItem {
  id: number;
  tabela: string;
  registroId: number;
  acao: string;
  descricao: string;
  dataHora: string | number[];
}

@Injectable({
  providedIn: 'root',
})
export class LogService {
  private readonly api = inject(ApiService);

  listar(): Observable<LogItem[]> {
    return this.api.get<LogItem[]>('/logs');
  }
}

export { LogService as Log };
