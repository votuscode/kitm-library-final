import { Injectable } from '@angular/core';

const escapeHtml = (html: string) => {
  const div = document.createElement('div');
  div.textContent = html;
  return div.innerHTML;
};

@Injectable({ providedIn: 'root' })
export class ToastService {
  notify = (message: string, header = 'Notification', type = 'primary', icon = 'info-circle', duration = 3000) => {
    const alert = Object.assign(document.createElement<any>('sl-alert'), {
      type,
      closable: true,
      duration,
      innerHTML: `
        <sl-icon name="${icon}" slot="icon"></sl-icon>
        <strong>${escapeHtml(header)}</strong><br>
        ${escapeHtml(message)}
      `,
    });

    document.body.append(alert);
    return typeof alert.toast === 'function' && alert.toast();
  };

  success = (message: string, header = 'Success') => this.notify(message, header, 'success', 'check2-circle');
  neutral = (message: string, header = 'Info') => this.notify(message, header, 'neutral', 'gear');
  warning = (message: string, header = 'Warning') => this.notify(message, header, 'warning', 'exclamation-triangle');
  error = (message: string, header = 'Error') => this.notify(message, header, 'danger', 'exclamation-octagon', 5000);
}
