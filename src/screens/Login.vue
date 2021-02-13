<template>
  <nb-container>
    <AppNavigationEvents :onDidFocus="checkForMessage" />
    <AppHeader root screen="PetterHome" />
    <nb-content>
      <nb-form>
        <InputWithError
          class="textInput"
          :error="$v.form.username.$dirty && !$v.form.username.required"
          msg="Username is required"
        >
          <nb-input
            v-model="form.username"
            placeholder="Username"
            auto-capitalize="none"
            :on-blur="() => $v.form.username.$touch()"
          />
        </InputWithError>
        <InputWithError
          class="textInput"
          :error="$v.form.password.$dirty && !$v.form.password.required"
          msg="Password is required!"
        >
          <nb-input
            v-model="form.password"
            placeholder="Password"
            auto-capitalize="none"
            :on-blur="() => $v.form.password.$touch()"
            secure-text-entry
          />
        </InputWithError>
        <view>
          <nb-button class="button" block :on-press="login"
            ><nb-text class="text">Login</nb-text></nb-button
          >
        </view>
        <nb-button class="subButton" transparent :on-press="goToRegister"
          ><nb-text class="text">Register new account</nb-text></nb-button
        >
        <nb-button
          :style="{ marginLeft: 120 }"
          class="subButton"
          transparent
          :on-press="goToRecoverPassword"
          ><nb-text class="text">Forgot Password?</nb-text></nb-button
        >
      </nb-form>
    </nb-content>
  </nb-container>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";
import AppNavigaton from "../react-components/AppNavigationEvents";

export default {
  components: {
    AppNavigaton,
  },
  props: { navigation: { type: Object } },

  data() {
    return {
      isCheckingUser: false,
      form: {
        username: "",
        password: "",
      },
    };
  },
  validations: {
    form: {
      username: {
        required,
      },
      password: {
        required,
      },
    },
  },
  computed: {
    users() {
      users = this.$store.state.auth.users;
      return users;
    },
  },
  created() {
    const isAuth = this.$store.getters["auth/isAuth"];
    if (isAuth) {
      this.navigation.navigate("Main");
    }

    //await AsyncStorage.removeItem('petterhome-jwt')
    /*this.isCheckingUser = true;
    this.$store
      .dispatch("auth/verifyUser")
      .then(() => this.navigation.navigate("Main"))
      .catch(() => {
        (this.isCheckingUser = false), this.checkForMessage();
      });*/
  },
  methods: {
    login() {
      this.$v.form.$touch();
      if (!this.$v.form.$invalid) {
        this.$store
          .dispatch("auth/login", this.form)
          .then((user) => {
            this.navigation.navigate("Main");
          })
          .catch((err) => {
            Toast.show({
              text: "Wrong password!",
              buttonText: "Okay",
              type: "danger",
              duration: 3000,
            });
          });
      } else {
        return alert("something went wrong");
      }
    },

    checkForMessage() {
      const message = this.navigation.getParam("message");
      if (message) {
        Toast.show({
          text: message,
          buttonText: "Okay",
          type: "Success",
          duration: 3000,
        });
      }
    },
    goToRegister() {
      this.navigation.navigate("Register");
    },
    goToRecoverPassword() {
      this.navigation.navigate("RecoverPassword");
    },
  },
};
</script>
<style lang="scss" scoped>
.button {
  border-radius: 20;
  margin-top: 10;
  margin-right: 40;
  margin-left: 40;
  background-color: #edccaf;

}
.subButton {
  border-radius: 20;
  margin-top: 10;
  margin-right: 50;
  margin-left: 110;
  background-color: #edccaf;
}
.textInput {
  margin-right: 40;
  margin-left: 40;
}
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
</style>