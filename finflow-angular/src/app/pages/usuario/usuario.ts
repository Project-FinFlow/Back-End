import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { UsuarioItem, UsuarioService } from '../../services/usuario';
import { buildApiLoadError } from '../../shared/api-config';

@Component({
  selector: 'app-usuario',
  imports: [],
  templateUrl: './usuario.html',
  styleUrl: './usuario.css',
})
export class Usuario {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(UsuarioService);

  protected readonly usuarios = signal<UsuarioItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');

  constructor() {
    this.recarregar();
  }

  protected recarregar(): void {
    this.carregando.set(true);
    this.erro.set('');

    this.service
      .listar()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (dados) => {
          this.usuarios.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('os usuarios'));
          this.carregando.set(false);
        },
      });
  }
}
