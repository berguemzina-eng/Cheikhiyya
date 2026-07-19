# الطريقة الشيخية — App Android nativa en Kotlin (100% offline)

Proyecto Android **nativo** (Kotlin + Jetpack Compose), reemplazo
completo de la versión anterior en React Native/Expo. Sin WebView,
sin llamadas de red: **el permiso de INTERNET ni siquiera está
declarado** en `AndroidManifest.xml`, así que el sistema operativo
bloquea cualquier intento de conexión aunque el código lo intentara.

## Qué incluye ya con contenido nativo real

- **الرئيسية** (portada) con accesos rápidos.
- **التصوف** — definición del sufismo, hadiz Yibril, los tres pilares
  del din.
- **الطريقة الشيخية** — historia, fundador (سيدي عبد القادر بن محمد),
  filiación a la Shadhiliyya, misión y zauias principales.
- **شروط الانتساب** — condiciones obligatorias y complementarias.
- **الشيخ الحالي (سيدي حمزة)** — breve semblanza.
- **حزب الفلاح** — texto completo del wird, con contador tocable por
  cada fórmula (marca `x / total`, se resalta al completar).

Todo ese texto vive en `app/src/main/java/com/cheikhiyya/tariqa/data/`
(`Articles.kt`, `HizbAlFalah.kt`) — es texto Kotlin puro, sin red.

## Qué falta por rellenar

El menú completo de la web ya está estructurado en
`data/Menu.kt` (Zauias, más Cheikhs, Fotos, la Hadra, la Yaqouta,
Adkar generales, Contacto...). Las secciones aún no transcritas
muestran una pantalla "قيد الإضافة" (pendiente) en vez de fallar —
la app nunca se rompe por una sección vacía.

**Para añadir una sección nueva:**
1. Escribe el texto como un nuevo `Article(...)` en `Articles.kt`
   (o un archivo de datos propio si es contenido especial, como se
   hizo con `HizbAlFalah.kt`).
2. En `Menu.kt`, cambia esa entrada de `articleId = pending("...")`
   a `articleId = "tu_nuevo_id"`.
3. Si esa sección necesita una pantalla especial (como el contador
   del Hizb Al-Falah), créala en `ui/screens/` y regístrala en
   `nav/Navigation.kt`.

Dímelo en el chat y te preparo el texto de cualquier sección
concreta (biografía, zauia, dhikr) para que solo copies/pegues.

## Imágenes y audio (offline)

- **Imágenes**: pon los archivos `.png`/`.jpg` en
  `app/src/main/res/drawable/` (nombres en minúsculas, sin espacios,
  ej. `sidi_hamza.png`). Luego referencia el nombre (sin extensión)
  en el campo `imageRes` del `Article` correspondiente.
- **Audio** (por ejemplo grabaciones del dhikr): pon los `.mp3` en
  `app/src/main/res/raw/` y referencia el nombre en `audioRes`. El
  proyecto ya incluye la librería Media3/ExoPlayer para reproducirlos
  sin conexión; dime cuándo tengas los archivos y conecto el
  reproductor a la pantalla del artículo correspondiente.

Como mi entorno no tiene acceso a internet para descargar binarios,
no pude incrustar las fotos/audios reales automáticamente — esa
parte la añades tú arrastrando archivos, o me los describes/subes
aquí y te preparo el código exacto.

## Generar el APK

### Opción A — GitHub Actions (recomendada, sin instalar nada)

El proyecto incluye `.github/workflows/build.yml`, 100% Gradle puro
(nada de Capacitor ni Expo):

1. Sube este proyecto a un repo de GitHub:
   ```bash
   git init && git add . && git commit -m "Tariqa Cheikhiyya Kotlin"
   git branch -M main
   git remote add origin <URL_DE_TU_REPO>
   git push -u origin main
   ```
2. Pestaña **Actions** → el workflow **build** corre solo, o dale a
   **Run workflow**.
3. Al terminar en verde ✅, entra en la ejecución → **Artifacts** →
   descarga `cheikhiyya-app-debug` → ahí está el `.apk`.

### Opción B — Android Studio (en tu ordenador)

1. Abre la carpeta del proyecto con **Android Studio** (Hedgehog o
   más reciente).
2. Deja que sincronice Gradle automáticamente.
3. `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`.
4. El `.apk` queda en `app/build/outputs/apk/debug/`.

## Publicar en Google Play

Para publicar necesitas un build de **release** firmado con tu propia
clave (`keystore`). Cuando llegues a ese punto dime y te preparo el
`build.gradle.kts` con la configuración de firma y el paso
correspondiente en el workflow.

## Estructura del proyecto

```
app/src/main/java/com/cheikhiyya/tariqa/
 ├─ MainActivity.kt          punto de entrada, fuerza RTL
 ├─ data/
 │   ├─ Models.kt            modelos MenuNode / Article
 │   ├─ Menu.kt              árbol completo del menú lateral
 │   ├─ Articles.kt          textos nativos ya redactados
 │   └─ HizbAlFalah.kt       wird completo con repeticiones
 ├─ nav/
 │   └─ Navigation.kt        Drawer + NavHost (rutas)
 └─ ui/
     ├─ theme/                colores y tipografía
     └─ screens/
         ├─ HomeScreen.kt
         ├─ DrawerContent.kt
         ├─ ArticleScreen.kt      pantalla genérica de texto
         ├─ HizbAlFalahScreen.kt  pantalla con contador de dhikr
         └─ PendingScreen.kt      aviso de sección aún no rellenada
```
