import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../api.service';

export interface UsuarioItem {
  id: number;
  nome: string;
  email: string;
  senha?: string;
}

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly api = inject(ApiService);

  listar(): Observable<UsuarioItem[]> {
    return this.api.get<UsuarioItem[]>('/usuarios');
  }

  criar(dados: Partial<UsuarioItem>): Observable<UsuarioItem> {
    return this.api.post<UsuarioItem>('/usuarios', dados);
  }
}

export { UsuarioService as Usuario };
