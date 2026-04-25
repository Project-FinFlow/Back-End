import { Component, DestroyRef, computed, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ReceitaItem, ReceitaService } from '../../services/receita';
import { buildApiLoadError } from '../../shared/api-config';
import { formatCurrency, formatDate } from '../../shared/formatters';

@Component({
  selector: 'app-receita',
  imports: [],
  templateUrl: './receita.html',
  styleUrl: './receita.css',
})
export class Receita {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(ReceitaService);

  protected readonly receitas = signal<ReceitaItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');
  protected readonly total = computed(() =>
    this.receitas().reduce((acumulado, item) => acumulado + Number(item.valor), 0)
  );
  protected readonly formatCurrency = formatCurrency;
  protected readonly formatDate = formatDate;

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
          this.receitas.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('as receitas'));
          this.carregando.set(false);
        },
      });
  }
}
