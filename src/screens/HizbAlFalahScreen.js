import React, { useState, useCallback } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  I18nManager,
} from 'react-native';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import colors from '../theme/colors';
import {
  HIZB_AL_FALAH_INTRO,
  HIZB_AL_FALAH,
  HIZB_AL_FALAH_NOTE,
  HIZB_AL_FALAH_SOURCE,
} from '../data/hizbAlFalah';

function DhikrLine({ item, index }) {
  // Contador local: toca la línea para llevar la cuenta de las repeticiones
  const [count, setCount] = useState(0);
  const target = item.repeat || 1;
  const done = count >= target;

  const onPress = useCallback(() => {
    setCount((c) => (c >= target ? 0 : c + 1));
  }, [target]);

  return (
    <TouchableOpacity
      style={[styles.line, done && styles.lineDone]}
      activeOpacity={0.7}
      onPress={onPress}
    >
      <View style={styles.lineTop}>
        <Text style={styles.lineIndex}>{index + 1}</Text>
        {target > 1 && (
          <View style={styles.counterBadge}>
            <Text style={styles.counterText}>
              {count} / {target}
            </Text>
          </View>
        )}
      </View>
      <Text style={styles.arabicText}>{item.text}</Text>
    </TouchableOpacity>
  );
}

export default function HizbAlFalahScreen({ navigation }) {
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
        <Text style={styles.headerTitle}>حزب الفلاح</Text>
        <View style={{ width: 24 }} />
      </View>

      <View style={styles.offlineBanner}>
        <MaterialCommunityIcons name="wifi-off" size={14} color={colors.primary} />
        <Text style={styles.offlineText}>متوفر بدون إنترنت — اضغط على أي فقرة للعد</Text>
      </View>

      <ScrollView contentContainerStyle={styles.scroll}>
        <View style={styles.introBox}>
          <Text style={styles.introText}>{HIZB_AL_FALAH_INTRO.istiadha}</Text>
          <Text style={styles.introText}>{HIZB_AL_FALAH_INTRO.basmala}</Text>
        </View>

        {HIZB_AL_FALAH.map((item, idx) => (
          <DhikrLine key={idx} item={item} index={idx} />
        ))}

        <View style={styles.noteBox}>
          <Text style={styles.noteText}>{HIZB_AL_FALAH_NOTE}</Text>
        </View>

        <Text style={styles.source}>{HIZB_AL_FALAH_SOURCE}</Text>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.cream },
  header: {
    backgroundColor: colors.primary,
    paddingTop: 50,
    paddingBottom: 14,
    paddingHorizontal: 12,
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  iconBtn: { padding: 6 },
  headerTitle: { color: colors.gold, fontSize: 18, fontWeight: '700' },
  offlineBanner: {
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.goldLight,
    paddingVertical: 6,
    gap: 6,
  },
  offlineText: { color: colors.primary, fontSize: 12, fontWeight: '600' },
  scroll: { padding: 16, paddingBottom: 40 },
  introBox: {
    backgroundColor: colors.primary,
    borderRadius: 12,
    padding: 14,
    marginBottom: 14,
  },
  introText: {
    color: colors.goldLight,
    fontSize: 15,
    textAlign: 'center',
    lineHeight: 26,
    marginBottom: 4,
  },
  line: {
    backgroundColor: colors.white,
    borderRadius: 10,
    padding: 12,
    marginBottom: 10,
    borderWidth: 1,
    borderColor: colors.border,
  },
  lineDone: {
    backgroundColor: '#F1E9D2',
    borderColor: colors.gold,
  },
  lineTop: {
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 6,
  },
  lineIndex: { color: colors.textMuted, fontSize: 12, fontWeight: '700' },
  counterBadge: {
    backgroundColor: colors.primary,
    borderRadius: 20,
    paddingHorizontal: 10,
    paddingVertical: 2,
  },
  counterText: { color: colors.gold, fontSize: 12, fontWeight: '700' },
  arabicText: {
    color: colors.text,
    fontSize: 17,
    textAlign: 'right',
    lineHeight: 30,
  },
  noteBox: {
    marginTop: 10,
    padding: 12,
    borderRadius: 10,
    backgroundColor: colors.border,
  },
  noteText: {
    color: colors.text,
    fontSize: 13,
    textAlign: 'right',
    lineHeight: 22,
  },
  source: {
    marginTop: 14,
    color: colors.textMuted,
    fontSize: 12,
    textAlign: 'center',
  },
});
