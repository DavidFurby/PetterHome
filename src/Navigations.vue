<template>
  <Root>
    <AppNavigaton />
  </Root>
</template>

<script>
import {
  createAppContainer,
  createBottomTabNavigator,
  createStackNavigator,
  createDrawerNavigator,
} from "vue-native-router";

import HomeScreen from "./screens/Home";
import NotificationScreen from "./screens/Notifications";
import SettingsScreen from "./screens/Settings";

import { Root } from "native-base";


const TabNavigation = createBottomTabNavigator({
  Meetups: HomeScreen,
  Notification: NotificationScreen,
  Settings: SettingsScreen,
});

const AppNavigaton = createAppContainer(TabNavigation);

export default {
  components: {
    AppNavigaton,
    Root,
  },
};
</script>
