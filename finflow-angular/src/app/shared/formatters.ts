function normalizarData(valor: unknown): Date | null {
  if (!valor) {
    return null;
  }

  if (Array.isArray(valor)) {
    const [ano, mes, dia, hora = 0, minuto = 0, segundo = 0] = valor.map(
      Number
    );

    if (![ano, mes, dia].every(Number.isFinite)) {
      return null;
    }

    return new Date(ano, mes - 1, dia, hora, minuto, segundo);
  }

  const data = new Date(String(valor));
  return Number.isNaN(data.getTime()) ? null : data;
}

export function formatCurrency(valor: number | string | null | undefined): string {
  const numero = Number(valor ?? 0);

  if (!Number.isFinite(numero)) {
    return 'R$ 0,00';
  }

  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(numero);
}

export function formatDate(valor: unknown): string {
  const data = normalizarData(valor);

  if (!data) {
    return '-';
  }

  return new Intl.DateTimeFormat('pt-BR').format(data);
}

export function formatDateTime(valor: unknown): string {
  const data = normalizarData(valor);

  if (!data) {
    return '-';
  }

  return new Intl.DateTimeFormat('pt-BR', {
    dateStyle: 'short',
    timeStyle: 'short',
  }).format(data);
}
