import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { LogItem, LogService } from '../../services/log';
import { buildApiLoadError } from '../../shared/api-config';
import { formatDateTime } from '../../shared/formatters';

@Component({
  selector: 'app-log',
  imports: [],
  templateUrl: './logs.html',
  styleUrl: './logs.css',
})
export class Logs {
  private readonly destroyRef = inject(DestroyRef);
  private readonly service = inject(LogService);

  protected readonly logs = signal<LogItem[]>([]);
  protected readonly carregando = signal(true);
  protected readonly erro = signal('');
  protected readonly formatDateTime = formatDateTime;

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
          this.logs.set(dados);
          this.carregando.set(false);
        },
        error: () => {
          this.erro.set(buildApiLoadError('os logs'));
          this.carregando.set(false);
        },
      });
  }
}
