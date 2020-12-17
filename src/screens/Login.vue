<template>
  <nb-container>
    <nb-header><nb-title>Login</nb-title></nb-header>
    <nb-content>
      <nb-form>
        <nb-item>
          <nb-input placeholder="Username" />
        </nb-item>
        <nb-item last>
          <nb-input placeholder="Password" />
        </nb-item>
        <nb-button @click="Login(userName, password)"
          ><nb-text>Login</nb-text></nb-button
        >
      </nb-form>
    </nb-content>
  </nb-container>
</template> 

<script>
import users from "../data/userMock.json";
export default {
  methods: {
    Login(userName, password) {
      if (users.includes(userName) && users.includes(password)) {
        alert("success");
      }
    },
  },
};
</script>

<style>
</style>