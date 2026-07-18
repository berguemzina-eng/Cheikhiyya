import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  Image,
  I18nManager,
  Linking,
} from 'react-native';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import colors from '../theme/colors';
import { MENU } from '../data/content';

function MenuNode({ node, depth, navigation }) {
  const [open, setOpen] = useState(false);
  const hasChildren = !!node.children;

  const handlePress = () => {
    if (hasChildren) {
      setOpen((o) => !o);
    } else if (node.external) {
      Linking.openURL(node.url);
    } else if (node.native) {
      navigation.navigate(node.native);
      navigation.closeDrawer && navigation.closeDrawer();
    } else {
      navigation.navigate('Content', { url: node.url, title: node.title });
      navigation.closeDrawer && navigation.closeDrawer();
    }
  };

  return (
    <View>
      <TouchableOpacity
        style={[styles.row, { paddingRight: 16 + depth * 16 }]}
        onPress={handlePress}
        activeOpacity={0.7}
      >
        {node.icon ? (
          <MaterialCommunityIcons
            name={node.icon}
            size={20}
            color={colors.gold}
            style={styles.rowIcon}
          />
        ) : (
          <View style={styles.dot} />
        )}
        <Text style={[styles.rowText, depth === 0 && styles.rowTextTop]} numberOfLines={2}>
          {node.title}
        </Text>
        {hasChildren && (
          <MaterialCommunityIcons
            name={open ? 'chevron-up' : 'chevron-down'}
            size={20}
            color={colors.textMuted}
          />
        )}
      </TouchableOpacity>
      {hasChildren && open && (
        <View>
          {node.children.map((child, idx) => (
            <MenuNode
              key={child.id || `${child.title}-${idx}`}
              node={child}
              depth={depth + 1}
              navigation={navigation}
            />
          ))}
        </View>
      )}
    </View>
  );
}

export default function DrawerMenu({ navigation }) {
  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Image
          source={{ uri: 'http://www.cheikhiyya.com/Foto/Logo.png' }}
          style={styles.logo}
          resizeMode="contain"
        />
        <Text style={styles.headerTitle}>الطريقة الشيخية الشاذلية</Text>
        <Text style={styles.headerSubtitle}>Tariqa Cheikhiyya</Text>
      </View>
      <ScrollView contentContainerStyle={{ paddingVertical: 8 }}>
        {MENU.map((node) => (
          <MenuNode key={node.id} node={node} depth={0} navigation={navigation} />
        ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.cream },
  header: {
    backgroundColor: colors.primary,
    paddingTop: 56,
    paddingBottom: 20,
    alignItems: 'center',
  },
  logo: { width: 64, height: 64, marginBottom: 8, borderRadius: 8 },
  headerTitle: { color: colors.gold, fontSize: 16, fontWeight: '700' },
  headerSubtitle: { color: colors.goldLight, fontSize: 12, marginTop: 2 },
  row: {
    flexDirection: I18nManager.isRTL ? 'row' : 'row-reverse',
    alignItems: 'center',
    paddingVertical: 12,
    paddingLeft: 12,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: colors.border,
  },
  rowIcon: { marginHorizontal: 10 },
  dot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: colors.textMuted,
    marginHorizontal: 12,
  },
  rowText: { flex: 1, color: colors.text, fontSize: 14, textAlign: 'right' },
  rowTextTop: { fontWeight: '700' },
});
