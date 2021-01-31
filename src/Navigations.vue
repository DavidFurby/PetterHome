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
import ChangePasswordScreen from "./screens/ChangePassword";
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
import AddSchedule from "./screens/AddSchema"

const MainNavigator = createStackNavigator(
  {
    Main: MainScreen,
    Pet: PetScreen,
    AddPet: AddPetScreen,
    Schema: SchemaScreen,
    AddSchema: AddSchedule,
  },
  {
    initialRouteName: "Main",
    headerMode: "none",
    navigationOptions: {
      headerVisible: false,
    },
  }
);

const RequestNavigation = createBottomTabNavigator(
  {
    Request: RequestScreen,
    SharePet: SharePetScreen,
  },
  {
    initialRouteName: "Request",
    headerMode: "none",
    navigationOptions: {
      headerVisible: false,
    },
  }
);
const ShareNavigation = createStackNavigator(
  {
    ShareList: ShareListScreen,
    ShareMenu: RequestNavigation,
  },
  {
    initialRouteName: "ShareMenu",
    headerMode: "none",
    navigationOptions: {
      headerVisible: false,
    },
  }
);
const SettingsNavigator = createStackNavigator(
  {
    Settings: SettingsScreen,
    ChangePassword: ChangePasswordScreen,
    Account: AccountScreen,
    NotificationSettings: NotificationSettingsScreen,
    SharePet: ShareNavigation,
  },
  {
    initialRouteName: "Settings",
    headerMode: "none",
    navigationOptions: {
      headerVisible: false,
    },
  }
);
const LoginNavigator = createStackNavigator({
  Login: LoginScreen,
  Register: RegistrationScreen,
  RecoverPassword: RecoverPasswordScreen,
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

const AppNavigaton = createAppContainer(
  createSwitchNavigator({
    auth: LoginNavigator,
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
<style scoped></style>
