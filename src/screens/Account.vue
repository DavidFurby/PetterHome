<template>
  <nb-container>
    <AppHeader
      screen="Account"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <nb-content padder>
      <AccountInformation :user="user" />
      <nb-button block info :on-press="changePassword"
        ><nb-text>Change password</nb-text></nb-button
      >
    </nb-content>
    <nb-button block danger :on-press="logout"><nb-text>Logout</nb-text></nb-button>
  </nb-container>
</template>

<script>
import { AsyncStorage } from "react-native";
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
      user: {
        type: Object,
      },
    };
  },
  created() {
    this.user = this.navigation.getParam("user", "undefined");
  },

  methods: {
    changePassword() {
      const user = this.user;
      this.navigation.navigate("ChangePassword", { user: user });
    },

    logout() {
      AsyncStorage.removeItem("petterHome-jwt");
      this.navigation.navigate("Login");

      this.$store.dispatch("auth/logout").then(() => {});
    },
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>