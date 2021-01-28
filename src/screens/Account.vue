<template>
  <nb-container>
    <AppHeader
      screen="Account"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <nb-content padder>
      <AccountInformation :user="user" />
      <nb-button :on-press="changePassword"
        ><nb-text>Change password</nb-text></nb-button
      >
      <nb-button :on-press="logout"><nb-text>Logout</nb-text></nb-button>
    </nb-content>
  </nb-container>
</template>

<script>
import userData from "../data/userMock.json";
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
      user: userData,
    };
  },
  computed: {
    user() {
      let user = this.$store.state.auth.user; 
      return user; 
    }
  },
  created() {
    this.$store.dispatch("auth/fetchCurrentUser");
  },
  methods: {
    changePassword() {
      this.navigation.navigate("ChangePassword");
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