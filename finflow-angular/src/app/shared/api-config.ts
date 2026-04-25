export const API_BASE_PATH = '/api';
export const API_BACKEND_URL = 'http://localhost:8080';

export function buildApiLoadError(recurso: string): string {
  return `Nao foi possivel carregar ${recurso}. Verifique se a API do backend esta ativa. Em desenvolvimento local, ela deve responder em ${API_BACKEND_URL}.`;
}
