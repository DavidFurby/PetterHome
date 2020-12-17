<template>
  <nb-container>
    <nb-header><nb-title>Account</nb-title></nb-header>

    <nb-content padder>
      <nb-text>{{ user[0].userName }}</nb-text>
      <nb-text>Email: {{ user[0].email }}</nb-text>
      <nb-text>Account ID: {{ user[0].id }}</nb-text>
      <nb-text>Phone Number: {{ user[0].phoneNumber }}</nb-text>
      <nb-text>Region: {{ user[0].region }}</nb-text>
      <nb-button :on-press="changePassword"
        ><nb-text>Change password</nb-text></nb-button
      >
      <nb-button :on-press="logout"><nb-text>Log out</nb-text></nb-button>
    </nb-content>
  </nb-container>
</template>

<script>
import userData from "../data/userMock.json";
export default {
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
      alert("change passowrd");
      this.navigation.navigate("ChangePassword");
    },
    
    logout() {
      alert("logout");
      this.navigation.navigate("Pet");
    },
  },
};
</script>