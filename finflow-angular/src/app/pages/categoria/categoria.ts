import { Component, DestroyRef, computed, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { CategoriaItem, CategoriaService } from '../../services/categoria';
import { buildApiLoadError } from '../../shared/api-config';

@Component({
  selector: 'app-categoria',
  imports: [],
  templateUrl: './categoria.html',
  styleUrl: './categoria.css',
})
export class Categoria {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(CategoriaService);

  protected readonly categorias = signal<CategoriaItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');
  protected readonly tipos = computed(
    () => new Set(this.categorias().map((item) => item.tipo)).size
  );

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
          this.categorias.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('as categorias'));
          this.carregando.set(false);
        },
      });
  }
}
