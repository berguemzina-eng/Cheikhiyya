import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  Image,
  TouchableOpacity,
  I18nManager,
} from 'react-native';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import colors from '../theme/colors';
import { HOME_SECTIONS } from '../data/content';

export default function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.openDrawer()} style={styles.menuBtn}>
          <MaterialCommunityIcons name="menu" size={28} color={colors.white} />
        </TouchableOpacity>
        <Text style={styles.headerTitle}>الطريقة الشيخية</Text>
        <View style={{ width: 28 }} />
      </View>

      <ScrollView contentContainerStyle={styles.scroll}>
        <View style={styles.hero}>
          <Text style={styles.heroText}>
            موقع الطريقة الشيخية الشاذلية{'\n'}( طريقة أسلاف بيضاء نقية )
          </Text>
        </View>

        <View style={styles.grid}>
          {HOME_SECTIONS.map((item) => (
            <TouchableOpacity
              key={item.id}
              style={styles.card}
              activeOpacity={0.85}
              onPress={() =>
                navigation.navigate('Content', { url: item.url, title: item.title })
              }
            >
              <Image source={{ uri: item.image }} style={styles.cardImage} />
              <View style={styles.cardOverlay}>
                <Text style={styles.cardTitle} numberOfLines={2}>
                  {item.title}
                </Text>
              </View>
            </TouchableOpacity>
          ))}
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.cream },
  header: {
    backgroundColor: colors.primary,
    paddingTop: 50,
    paddingBottom: 16,
    paddingHorizontal: 16,
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  menuBtn: { padding: 4 },
  headerTitle: {
    color: colors.gold,
    fontSize: 20,
    fontWeight: '700',
    textAlign: 'center',
  },
  scroll: { padding: 12, paddingBottom: 32 },
  hero: {
    backgroundColor: colors.primary,
    borderRadius: 14,
    padding: 18,
    marginBottom: 16,
  },
  heroText: {
    color: colors.goldLight,
    fontSize: 15,
    textAlign: 'center',
    lineHeight: 24,
  },
  grid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  card: {
    width: '48%',
    height: 140,
    borderRadius: 12,
    overflow: 'hidden',
    marginBottom: 12,
    backgroundColor: colors.border,
  },
  cardImage: { width: '100%', height: '100%' },
  cardOverlay: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    backgroundColor: 'rgba(11,61,46,0.82)',
    paddingVertical: 8,
    paddingHorizontal: 8,
  },
  cardTitle: {
    color: colors.white,
    fontSize: 13,
    fontWeight: '600',
    textAlign: 'center',
  },
});
