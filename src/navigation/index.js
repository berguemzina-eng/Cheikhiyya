import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import HomeScreen from '../screens/HomeScreen';
import ContentScreen from '../screens/ContentScreen';
import DrawerMenu from '../screens/DrawerMenu';
import colors from '../theme/colors';

const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();

function HomeStack() {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="HomeMain" component={HomeScreen} />
      <Stack.Screen name="Content" component={ContentScreen} />
    </Stack.Navigator>
  );
}

export default function RootNavigator() {
  return (
    <NavigationContainer>
      <Drawer.Navigator
        drawerContent={(props) => <DrawerMenu {...props} />}
        screenOptions={{
          headerShown: false,
          drawerPosition: 'right', // el menú abre desde la derecha (natural para RTL)
          drawerStyle: { width: '82%', backgroundColor: colors.cream },
        }}
      >
        <Drawer.Screen name="Home" component={HomeStack} />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}
