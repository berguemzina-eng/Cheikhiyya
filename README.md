# الطريقة الشيخية — App Android nativa en Kotlin (100% offline)

Proyecto Android **nativo** (Kotlin + Jetpack Compose). Sin WebView,
sin llamadas de red: **el permiso de INTERNET ni siquiera está
declarado** en `AndroidManifest.xml`, así que el sistema operativo
bloquea cualquier intento de conexión aunque el código lo intentara.
La única excepción es "الكتاب الذهبي", que abre el navegador externo
del teléfono (como cualquier enlace a redes sociales) solo cuando el
usuario lo toca explícitamente.

## Contenido ya completo (texto real, offline)

- **الرئيسية** — portada con 10 accesos rápidos.
- **التصوف** — pantalla propia con diseño visual (3 niveles del din,
  5 pilares, qué no es tasawwuf).
- **الطريقة الشيخية** — historia, citas de al-Rifa'i/al-Sha'rani/al-Junayd,
  misión, tribus adherentes.
- **العهد والبيعة وآداب الذكر** — ritual de iniciación completo.
- **شروط الانتساب** — 11 condiciones de validez + 14 de perfección.
- **شيوخ الطريقة** — 6 biografías (incluye سيدي حمزة, el sheikh actual,
  con su biografía completa).
- **أشهر الزوايا** — **19 de 22** zauias, por país (Argelia/Marruecos).
  Faltan: بوعرفة, ز ش بوعمامة.
- **سلسلة الطريقة** — 2 versiones (resumida y detallada), con selector
  y un árbol vectorial dorado decorativo.
- **من خريجي الطريقة** — **20 de ~24** discípulos con biografía real.
- **أجداد سيدي الشيخ** — **4/4 completos**.
- **الأذكار والأوراد** — حزب الفلاح (contador), الحضرة (24 versos +
  contador), الياقوتة (178 versos, con función de guardar posición de
  lectura tipo marcador), الأذكار العامة, المسبحة (contador de tasbih
  interactivo con 6 dhikr y metas seleccionables).
- **المتحف** — كتب y رسائل, catálogo con los títulos reales (las
  páginas originales son galerías de fotos sin texto transcribible).
- **الكتاب الذهبي** — enlace externo al grupo de Facebook real.
- **إتصل بنا** — redes sociales reales.

Todo el texto vive en `app/src/main/java/com/cheikhiyya/tariqa/data/`
(`Articles.kt`, `ZawiyasExtra.kt`, `Kharijeen.kt`, `Ancestors.kt`,
`Silsila.kt`, `HizbAlFalah.kt`, `Hadra.kt`, `Yaqouta.kt`, `Museum.kt`)
— es texto Kotlin puro, sin red.

## Qué falta por rellenar

Pendiente real (nombres ya en el menú, esperando texto/imágenes):

- **2 zauias**: بوعرفة, ز ش بوعمامة — sin contenido fiable encontrado
  tras varias búsquedas.
- **~4 discípulos**: سيدي احمد البطيوي، سيدي احمد بن بودي، سيدي ابو
  عبد الله — igual, sin contenido fiable encontrado todavía.
- **Fotos**: 2 galerías del museo (كتب/رسائل) + 2 secciones de fotos
  generales (شيوخ الطريقة/زوايا الطريقة) — necesitan que el usuario
  suba las imágenes reales (ver más abajo).

**Para añadir una sección nueva:**
1. Escribe el texto como un nuevo `Article(...)` en el archivo de
   datos que corresponda (o crea uno nuevo si es contenido especial).
2. En `Menu.kt`, conecta esa entrada con `articleId = "tu_nuevo_id"`
   (las que faltan usan `articleId = pending("...")`).
3. Si la sección necesita una pantalla especial (como los contadores
   de dhikr), créala en `ui/screens/` y regístrala en
   `nav/Navigation.kt`.

## Diseño islámico y logo

- **Splash screen**: `ui/screens/SplashScreen.kt` — logo real sobre
  fondo verde con patrón geométrico, dura **exactamente 3 segundos**.
- **Logo real integrado** ✅ en splash, portada, menú lateral, barras
  superiores de todas las pantallas, e icono de la app —
  `res/drawable/logo_circular.png` y `logo_horizontal.png`.
- **Cabecera de portada**: silueta festoneada (`ScallopedShape.kt`),
  medallón dorado con resplandor radial (`IslamicMedallion.kt`),
  ornamentos de esquina.
- **Árbol de la silsila**: `SilsilaTree.kt`, motivo vectorial propio
  con ramas doradas y nodos verdes.
- **Divisores ornamentales**: `IslamicDivider.kt`, roseta de 8 puntas
  entre secciones de texto.
- Si más adelante quieres cambiar el logo, solo reemplaza esos dos
  PNG en `res/drawable/` manteniendo el mismo nombre.

## Imágenes y audio (offline)

- **Imágenes**: pon los archivos `.png`/`.jpg` en
  `app/src/main/res/drawable/` (nombres en minúsculas, sin espacios).
  Luego referencia el nombre (sin extensión) en el campo `imageRes`
  del `Article` correspondiente.
- **Audio**: pon los `.mp3` en `app/src/main/res/raw/` y referencia
  el nombre en `audioRes`. El proyecto ya incluye Media3/ExoPlayer.

Como el entorno de trabajo usado para construir esta app no tiene
acceso a internet para descargar binarios, las fotos/audios reales se
añaden subiéndolos directamente al chat de trabajo, o arrastrándolos
tú mismo a las carpetas indicadas.

## Generar el APK

### Opción A — GitHub Actions (recomendada, sin instalar nada)

El proyecto incluye `.github/workflows/build.yml`, 100% Gradle puro:

1. Sube este proyecto a un repo de GitHub.
2. Pestaña **Actions** → el workflow **build** corre solo, o dale a
   **Run workflow**.
3. Al terminar en verde ✅, entra en la ejecución → **Artifacts** →
   descarga `cheikhiyya-app-debug` → ahí está el `.apk`.

### Opción B — Android Studio (en tu ordenador)

1. Abre la carpeta del proyecto con Android Studio.
2. Deja que sincronice Gradle automáticamente.
3. `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`.

## Publicar en Google Play

Para publicar necesitas un build de **release** firmado con tu propia
clave (`keystore`). Aún no configurado — pídelo cuando llegues a ese
punto.

## Estructura del proyecto

```
app/src/main/java/com/cheikhiyya/tariqa/
 ├─ MainActivity.kt          punto de entrada, splash, fuerza RTL
 ├─ data/
 │   ├─ Models.kt            modelos MenuNode / Article
 │   ├─ Menu.kt              árbol completo del menú lateral
 │   ├─ Articles.kt          artículos principales (Tasawwuf, Tariqa...)
 │   ├─ ZawiyasExtra.kt      19 zauias adicionales
 │   ├─ Kharijeen.kt         20 biografías de discípulos
 │   ├─ Ancestors.kt         4 ancestros de sidi Cheikh
 │   ├─ Silsila.kt           las 2 cadenas espirituales
 │   ├─ Museum.kt            catálogo del museo
 │   ├─ HizbAlFalah.kt       wird completo con repeticiones
 │   ├─ Hadra.kt             24 versos + dhikr de apertura/cierre
 │   └─ Yaqouta.kt           178 versos
 ├─ nav/
 │   └─ Navigation.kt        Drawer + NavHost (rutas)
 └─ ui/
     ├─ theme/                colores, formas, patrones decorativos
     └─ screens/              ~20 pantallas (Home, artículos, dhikr...)
```
