import { Injectable } from '@angular/core';
 
@Injectable({
  providedIn: 'root'
})
export class RecaptchaService {
  private scriptLoadingPromise: Promise<void> | null = null;
  private grecaptchaReady: Promise<void>;
 
  constructor() {
    this.grecaptchaReady = this.loadScript();
  }
 
  loadScript(): Promise<void> {
    if (this.scriptLoadingPromise) {
      return this.scriptLoadingPromise;
    }
 
    this.scriptLoadingPromise = new Promise<void>((resolve, reject) => {
      const grecaptcha = (window as any).grecaptcha;
      if (grecaptcha && typeof grecaptcha.ready === 'function') {
        grecaptcha.ready(() => resolve());
        return;
      }
 
      const script = document.createElement('script');
      script.src = 'https://www.google.com/recaptcha/api.js?render=explicit';
      script.async = true;
      script.defer = true;
      script.onload = () => {
        const g = (window as any).grecaptcha;
        if (g && typeof g.ready === 'function') {
          g.ready(() => resolve());
        } else {
          reject(new Error('reCAPTCHA failed to initialize'));
        }
      };
      script.onerror = () => reject(new Error('Failed to load reCAPTCHA script'));
      document.head.appendChild(script);
    });
 
    return this.scriptLoadingPromise;
  }
 
  render(containerId: string, siteKey: string): Promise<any> {
    return this.grecaptchaReady.then(() => {
      const grecaptcha = (window as any).grecaptcha;
      if (!grecaptcha) {
        return Promise.reject('reCAPTCHA not available');
      }
      return grecaptcha.render(containerId, {
        sitekey: siteKey,
        theme: 'light'
      });
    });
  }
 
  getToken(widgetId: any): Promise<string> {
    return this.grecaptchaReady.then(() => {
      const grecaptcha = (window as any).grecaptcha;
      if (!grecaptcha || widgetId == null) {
        return Promise.reject('reCAPTCHA not loaded');
      }
      return new Promise((resolve, reject) => {
        try {
          const token = grecaptcha.getResponse(widgetId);
          if (token) {
            resolve(token);
          } else {
            reject('Please complete the captcha');
          }
        } catch (e) {
          reject('reCAPTCHA error');
        }
      });
    });
  }
 
  reset(widgetId: any) {
    const grecaptcha = (window as any).grecaptcha;
    if (grecaptcha && widgetId != null) {
      try {
        grecaptcha.reset(widgetId);
      } catch (e) {
        // ignore
      }
    }
  }
}
