<template>
  <Root>
    <AppNavigaton />
  </Root>
</template>

<script>
import {
  createAppContainer,
  createBottomTabNavigator ,
  createStackNavigator,
} from "vue-native-router";

import MainScreen from "./screens/Home";
import NotificationScreen from "./screens/Notifications";
import SettingsScreen from "./screens/Settings";
import AccountScreen from "./screens/Account";
import NotificationSettingsScreen from "./screens/NotificationSettings";
import SharePetScreen from "./screens/SharePet"
import PetScreen from "./screens/Pet";
import AddPetScreen from "./screens/AddPet";
import { Root } from "native-base";

const MainNavigator = createStackNavigator(
  {
    Main: MainScreen,
    Pet: PetScreen,
    AddPet: AddPetScreen,
  },
  { initialRouteName: "Main" }
);
const SettingsNavigator = createStackNavigator({
  Settings: SettingsScreen,
  Account: AccountScreen,
  NotificationSettings: NotificationSettingsScreen,
  SharePet: SharePetScreen,
});

const TabNavigation = createBottomTabNavigator(
  {
    Notification: NotificationScreen,
    Animals: MainNavigator,
    Settings: SettingsNavigator,
  },
  {
    initialRouteName: "Animals",
  }
);

const AppNavigaton = createAppContainer(TabNavigation);

export default {
  components: {
    AppNavigaton,
    Root,
  },
};
</script>
<style scoped>
</style>