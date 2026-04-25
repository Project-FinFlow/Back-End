import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface MetaItem {
  id: number;
  descricao: string;
  valorObjetivo: number;
  valorAtual: number;
  usuarioId?: number;
}

@Injectable({
  providedIn: 'root',
})
export class MetaService {
  private readonly api = inject(ApiService);

  listar(): Observable<MetaItem[]> {
    return this.api.get<MetaItem[]>('/metas');
  }

  criar(dados: Partial<MetaItem>): Observable<MetaItem> {
    return this.api.post<MetaItem>('/metas', dados);
  }
}

export { MetaService as Meta };
