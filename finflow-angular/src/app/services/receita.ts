import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface ReceitaItem {
  id: number;
  valor: number;
  descricao: string;
  data: string | number[];
}

@Injectable({
  providedIn: 'root',
})
export class ReceitaService {
  private readonly api = inject(ApiService);

  listar(): Observable<ReceitaItem[]> {
    return this.api.get<ReceitaItem[]>('/receitas');
  }

  criar(dados: Partial<ReceitaItem>): Observable<ReceitaItem> {
    return this.api.post<ReceitaItem>('/receitas', dados);
  }
}

export { ReceitaService as Receita };
