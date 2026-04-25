import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface DespesaItem {
  id: number;
  valor: number;
  descricao: string;
  data: string | number[];
}

@Injectable({
  providedIn: 'root',
})
export class DespesaService {
  private readonly api = inject(ApiService);

  listar(): Observable<DespesaItem[]> {
    return this.api.get<DespesaItem[]>('/despesas');
  }

  criar(dados: Partial<DespesaItem>): Observable<DespesaItem> {
    return this.api.post<DespesaItem>('/despesas', dados);
  }
}

export { DespesaService as Despesa };
