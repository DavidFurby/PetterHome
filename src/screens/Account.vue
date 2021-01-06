<template>
  <nb-container>
    <AppHeader
      screen="Account"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <nb-content padder>
      <account-information :user="user" />
      <nb-button :on-press="changePassword"
        ><nb-text>Change password</nb-text></nb-button
      >
      <nb-button :on-press="logout"><nb-text>Logout</nb-text></nb-button>
    </nb-content>
  </nb-container>
</template>

<script>
import userData from "../data/userMock.json";
import { AsyncStorage } from "@react-native-community/async-storage";
import AccountInformation from "../components/AccountInformation.vue";
export default {
  components: { AccountInformation },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      user: userData,
    };
  },
  methods: {
    changePassword() {
      this.navigation.navigate("ChangePassword");
    },

    logout() {
      this.navigation.navigate("Login");

      this.$store.dispatch("auth/logout").then(() => {});
    },
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>