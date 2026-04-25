import { Routes } from '@angular/router';
import { Despesas } from './pages/despesas/despesas';
import { Usuario } from './pages/usuario/usuario';
import { Categoria } from './pages/categoria/categoria';
import { Meta} from './pages/meta/meta';
import { Receita} from './pages/receita/receita';
import { Logs } from './pages/logs/logs';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'despesas' },
  { path: 'despesas', component: Despesas },
  { path: 'usuarios', component: Usuario },
  { path: 'categorias', component: Categoria },
  { path: 'metas', component: Meta},
  { path: 'receitas', component: Receita },
  { path: 'logs', component: Logs },
  { path: '**', redirectTo: 'despesas' }
];
