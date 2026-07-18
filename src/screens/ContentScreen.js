import React, { useRef, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ActivityIndicator,
  I18nManager,
} from 'react-native';
import { WebView } from 'react-native-webview';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import colors from '../theme/colors';

export default function ContentScreen({ route, navigation }) {
  const { url, title } = route.params;
  const webviewRef = useRef(null);
  const [loading, setLoading] = useState(true);
  const [canGoBack, setCanGoBack] = useState(false);

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.goBack()} style={styles.iconBtn}>
          <MaterialCommunityIcons
            name={I18nManager.isRTL ? 'arrow-right' : 'arrow-left'}
            size={24}
            color={colors.white}
          />
        </TouchableOpacity>
        <Text style={styles.headerTitle} numberOfLines={1}>
          {title}
        </Text>
        <TouchableOpacity
          onPress={() => canGoBack && webviewRef.current?.goBack()}
          style={styles.iconBtn}
        >
          <MaterialCommunityIcons
            name="refresh"
            size={22}
            color={colors.white}
            onPress={() => webviewRef.current?.reload()}
          />
        </TouchableOpacity>
      </View>

      {loading && (
        <View style={styles.loader}>
          <ActivityIndicator size="large" color={colors.primary} />
        </View>
      )}

      <WebView
        ref={webviewRef}
        source={{ uri: url }}
        style={styles.webview}
        onLoadStart={() => setLoading(true)}
        onLoadEnd={() => setLoading(false)}
        onNavigationStateChange={(nav) => setCanGoBack(nav.canGoBack)}
        startInLoadingState
        allowsInlineMediaPlayback
        mediaPlaybackRequiresUserAction={false}
        // Ajusta el tamaño de fuente para lectura cómoda dentro del shell nativo
        injectedJavaScript={`
          try {
            document.body.style.webkitTextSizeAdjust = '110%';
          } catch(e) {}
          true;
        `}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.cream },
  header: {
    backgroundColor: colors.primary,
    paddingTop: 50,
    paddingBottom: 12,
    paddingHorizontal: 12,
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  iconBtn: { padding: 6 },
  headerTitle: {
    flex: 1,
    color: colors.gold,
    fontSize: 16,
    fontWeight: '700',
    textAlign: 'center',
  },
  webview: { flex: 1 },
  loader: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: 'center',
    alignItems: 'center',
    zIndex: 5,
    backgroundColor: colors.cream,
  },
});
