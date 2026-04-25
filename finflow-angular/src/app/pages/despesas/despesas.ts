import { Component, DestroyRef, computed, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { DespesaItem, DespesaService } from '../../services/despesa';
import { buildApiLoadError } from '../../shared/api-config';
import { formatCurrency, formatDate } from '../../shared/formatters';

@Component({
  selector: 'app-despesas',
  imports: [],
  templateUrl: './despesas.html',
  styleUrl: './despesas.css',
})
export class Despesas {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(DespesaService);

  protected readonly despesas = signal<DespesaItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');
  protected readonly total = computed(() =>
    this.despesas().reduce((acumulado, item) => acumulado + Number(item.valor), 0)
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
          this.despesas.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('as despesas'));
          this.carregando.set(false);
        },
      });
  }
}
