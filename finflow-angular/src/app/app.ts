import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = 'FinFlow';
  protected readonly navigation = [
    {
      path: '/despesas',
      label: 'Despesas',
      description: 'Controle suas saidas e pagamentos.'
    },
    {
      path: '/receitas',
      label: 'Receitas',
      description: 'Acompanhe entradas e valores recebidos.'
    },
    {
      path: '/categorias',
      label: 'Categorias',
      description: 'Organize os lancamentos por tipo.'
    },
    {
      path: '/metas',
      label: 'Metas',
      description: 'Defina objetivos para o seu caixa.'
    },
    {
      path: '/usuarios',
      label: 'Usuarios',
      description: 'Gerencie acessos e perfis do sistema.'
    },
    {
      path: '/logs',
      label: 'Logs',
      description: 'Consulte eventos e historico do app.'
    }
  ] as const;
}
