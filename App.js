import React, { useEffect, useState, useCallback } from 'react';
import { I18nManager } from 'react-native';
import * as SplashScreen from 'expo-splash-screen';
import { StatusBar } from 'expo-status-bar';
import { GestureHandlerRootView } from 'react-native-gesture-handler';
import RootNavigator from './src/navigation';
import colors from './src/theme/colors';

SplashScreen.preventAutoHideAsync().catch(() => {});

// Fuerza la dirección RTL en toda la app (contenido en árabe).
// NOTA: en Android, tras el primer cambio de isRTL hace falta
// recargar la app una vez (esto es normal y solo ocurre la
// primera vez que se instala / tras un cambio de configuración).
if (!I18nManager.isRTL) {
  I18nManager.allowRTL(true);
  I18nManager.forceRTL(true);
}

export default function App() {
  const [ready, setReady] = useState(false);

  useEffect(() => {
    (async () => {
      // Aquí se podrían precargar fuentes/recursos si se añaden.
      setReady(true);
      await SplashScreen.hideAsync().catch(() => {});
    })();
  }, []);

  const onLayout = useCallback(async () => {
    if (ready) {
      await SplashScreen.hideAsync().catch(() => {});
    }
  }, [ready]);

  if (!ready) return null;

  return (
    <GestureHandlerRootView style={{ flex: 1 }} onLayout={onLayout}>
      <StatusBar style="light" backgroundColor={colors.primary} />
      <RootNavigator />
    </GestureHandlerRootView>
  );
}
