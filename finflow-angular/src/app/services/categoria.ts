import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface CategoriaItem {
  id: number;
  nome: string;
  tipo: string;
}

@Injectable({
  providedIn: 'root',
})
export class CategoriaService {
  private readonly api = inject(ApiService);

  listar(): Observable<CategoriaItem[]> {
    return this.api.get<CategoriaItem[]>('/categorias');
  }

  criar(dados: Partial<CategoriaItem>): Observable<CategoriaItem> {
    return this.api.post<CategoriaItem>('/categorias', dados);
  }
}

export { CategoriaService as Categoria };
