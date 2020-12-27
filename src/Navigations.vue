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
  createMaterialTopTabNavigator,
  createSwitchNavigator,
} from "vue-native-router";

import MainScreen from "./screens/Home";
import NotificationScreen from "./screens/Notifications";
import SettingsScreen from "./screens/Settings";
import AccountScreen from "./screens/Account";
import NotificationSettingsScreen from "./screens/NotificationSettings";
import SharePetScreen from "./screens/SharePet";
import PetScreen from "./screens/Pet";
import AddPetScreen from "./screens/AddPet";
import { Root } from "native-base";
import ShareListScreen from "./screens/ShareList";
import RequestScreen from "./screens/Request";
import SchemaScreen from "./screens/Schema";
import LoginScreen from "./screens/Login";
import RegistrationScreen from "./screens/Register";
import RecoverPasswordScreen from "./screens/RecoverPassword";

const LoginNavigator = createStackNavigator(
  {
    Register: RegistrationScreen,
    Login: LoginScreen,
    RecoverPassword: RecoverPasswordScreen,
  },
  { initialRouteName: "Login", headerMode: "none" }
);
const MainNavigator = createStackNavigator(
  {
    Main: MainScreen,
    Pet: PetScreen,
    AddPet: AddPetScreen,
    Schema: SchemaScreen,
  },
  { initialRouteName: "Main", headerMode: "none" }
);

const RequestNavigation = createMaterialTopTabNavigator({
  Request: RequestScreen,
  SharePet: SharePetScreen,
});
const ShareNavigation = createStackNavigator(
  {
    ShareList: ShareListScreen,
    ShareMenu: RequestNavigation,
  },
  {
    initialRouteName: "ShareMenu",
  }
);
const SettingsNavigator = createStackNavigator(
  {
    Settings: SettingsScreen,
    Account: AccountScreen,
    NotificationSettings: NotificationSettingsScreen,
    SharePet: ShareNavigation,
  },
  {
    headerMode: "none",
  }
);

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

const AppNavigaton = createAppContainer(
  createSwitchNavigator({
    login: LoginNavigator,

    tabs: TabNavigation,
  })
);

export default {
  components: {
    AppNavigaton,
    Root,
  },
};
</script>
<style scoped>
</style>