<template>
  <root>
    <AppNavigaton />
  </root>
</template>

<script>
import {
  createAppContainer,
  createBottomTabNavigator,
  createStackNavigator,
  createSwitchNavigator,
} from "vue-native-router";

import MainScreen from "./screens/Home";
import NotificationScreen from "./screens/Notifications";
import SettingsScreen from "./screens/Settings";
import AccountScreen from "./screens/Account";
import ChangePasswordScreen from "./screens/ChangePassword";
import SharePetScreen from "./screens/SharePet";
import PetScreen from "./screens/Pet";
import AddPetScreen from "./screens/AddPet";
import { Root } from "native-base";
import ShareListScreen from "./screens/ShareList";
import RequestScreen from "./screens/Request";
import NeedsScreen from "./screens/Needs";
import LoginScreen from "./screens/Login";
import RegistrationScreen from "./screens/Register";
import RecoverPasswordScreen from "./screens/RecoverPassword";
import AddNeed from "./screens/AddNeed";
import AddSchedule from "./screens/AddSchedule";
import ReceivedPetsScreen from "./screens/ReceivedPets";
import ReceivedPetPage from "./screens/ReceivedPetPage";
import ReceivedPetNeeds from "./screens/ReceivedPetNeeds";
import UpdatePetScreen from "./screens/UpdatePetInformation";
const MainNavigator = createStackNavigator(
  {
    Main: MainScreen,
    Pet: PetScreen,
    AddPet: AddPetScreen,
    Needs: NeedsScreen,
    AddNeed: AddNeed,
    AddSchedule: AddSchedule,
    UpdatePet: UpdatePetScreen,
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
    Requests: RequestScreen,
    SharePet: SharePetScreen,
    ReceivedPets: ReceivedPetsScreen,
  },
  {
    initialRouteName: "SharePet",
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
    ReceivedPetPage: ReceivedPetPage,
    ReceivedPetNeeds: ReceivedPetNeeds,
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
const LoginNavigator = createStackNavigator(
  {
    Login: LoginScreen,
    Register: RegistrationScreen,
    RecoverPassword: RecoverPasswordScreen,
  },
  {
    initialRouteName: "Login",
    headerMode: "none",
    navigationOptions: {
      headerVisible: false,
    },
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
<nb-text scoped>
.container {
  flex: 1;
  background-color: red;
}

</style>