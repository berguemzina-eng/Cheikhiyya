# الطريقة الشيخية — App Android (Tariqa Cheikhiyya)

App nativa (React Native + Expo) para la Tariqa Cheikhiyya, basada en
el contenido de www.cheikhiyya.com.

## Qué incluye

- Navegación 100% nativa en árabe con interfaz **RTL** (menú lateral
  que reproduce toda la estructura real de la web: Tasawwuf, Tariqa,
  Zauias de Argelia y Marruecos, cadena espiritual (Silsila), jeques,
  discípulos destacados, Adkar/Aurad, fotos, audios, videos, museo,
  redes sociales y contacto).
- Pantalla de inicio con tarjetas destacadas (igual que la portada de
  la web).
- Cada sección se abre en una pantalla con cabecera nativa (título,
  botón atrás, recargar) y el contenido real de esa página se muestra
  dentro de la app (WebView optimizado, soporta audio/video/PDF que ya
  usa la web).
- Colores y estilo inspirados en manuscritos sufíes (verde + dorado).

> Este enfoque híbrido (shell nativo + contenido incrustado) te
> permite tener las +80 páginas de la web dentro de la app desde el
> día uno, sin transcribir cada una a mano, y se mantiene siempre
> actualizado si editas la web. Si más adelante quieres contenido
> 100% nativo (offline total) para más secciones (p. ej. la Hadra,
> biografías), se pueden ir migrando una a una — dímelo y lo hacemos
> por partes.

### Contenido ya 100% nativo (offline)

- **حزب الفلاح (Hizb Al-Falah)**: pantalla propia
  (`src/screens/HizbAlFalahScreen.js` + datos en
  `src/data/hizbAlFalah.js`), con el texto completo del wird, tipografía
  árabe legible y un contador tocable por cada fórmula (útil para
  contar las repeticiones del dhikr). No necesita conexión a internet.
  Está enlazada tanto desde la tarjeta de portada como desde el menú
  lateral (Adkar y Aurad → حزب الفلاح).

## Requisitos

- Node.js 18+
- Cuenta gratuita en https://expo.dev (para EAS Build)
- `npm install -g eas-cli` (o usar `npx eas-cli`)

## Pasos para generar el APK

```bash
cd cheikhiyya-app
npm install

# Iniciar sesión en Expo (una sola vez)
npx eas login

# Vincular el proyecto (una sola vez, genera un projectId en app.json)
npx eas init

# Compilar el APK en la nube (no necesitas Android Studio)
npx eas build -p android --profile preview
```

Al terminar, EAS te da un link para descargar el `.apk` directamente
al teléfono o a tu PC.

### Alternativa: build local con Android Studio

```bash
npx expo prebuild -p android
npx expo run:android
```

Esto genera la carpeta `android/` nativa; desde ahí puedes abrir el
proyecto en Android Studio y generar el APK/Bundle firmado como con
cualquier proyecto nativo.

## Personalizar

- **Icono / splash**: reemplaza los archivos en `assets/` (ahora hay
  placeholders generados automáticamente con el verde/dorado de la
  app — puedes poner el logo real de la Tariqa en `assets/icon.png`,
  `assets/adaptive-icon.png` y `assets/splash.png`, 1024×1024 e
  imagen de splash respectivamente).
- **Menú y páginas**: todo el árbol de navegación está en
  `src/data/content.js` — añadir, quitar o reordenar secciones es
  editar ese único archivo.
- **Colores**: `src/theme/colors.js`.
- **Nombre del paquete Android**: `app.json` → `android.package`
  (cámbialo antes de publicar en Play Store, actualmente es
  `com.cheikhiyya.tariqa`).

## Publicar en Google Play

1. Crea una cuenta de desarrollador en Play Console (pago único).
2. Genera un build de producción: `eas build -p android --profile production` (AAB).
3. Sube el `.aab` a Play Console y completa la ficha de la app.
