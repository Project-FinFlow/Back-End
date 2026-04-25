import { Component, DestroyRef, computed, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { MetaItem, MetaService } from '../../services/meta';
import { buildApiLoadError } from '../../shared/api-config';
import { formatCurrency } from '../../shared/formatters';

@Component({
  selector: 'app-meta',
  imports: [],
  templateUrl: './meta.html',
  styleUrl: './meta.css',
})
export class Meta {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(MetaService);

  protected readonly metas = signal<MetaItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');
  protected readonly valorPlanejado = computed(() =>
    this.metas().reduce(
      (acumulado, item) => acumulado + Number(item.valorObjetivo),
      0
    )
  );
  protected readonly valorAtualTotal = computed(() =>
    this.metas().reduce(
      (acumulado, item) => acumulado + Number(item.valorAtual ?? 0),
      0
    )
  );
  protected readonly formatCurrency = formatCurrency;

  protected percentualDaMeta(item: MetaItem): number {
    const objetivo = Number(item.valorObjetivo ?? 0);
    const atual = Number(item.valorAtual ?? 0);

    if (!objetivo) {
      return 0;
    }

    return Math.min(100, Math.round((atual / objetivo) * 100));
  }

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
          this.metas.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('as metas'));
          this.carregando.set(false);
        },
      });
  }
}
