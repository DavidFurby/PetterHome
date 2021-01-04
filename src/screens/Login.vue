<template>
  <nb-container>
    <AppNavigationEvents :onDidFocus="checkForMessage" />
    <AppHeader screen="Login" />
    <nb-content>
      <nb-form>
        <InputWithError
          :error="$v.form.email.$dirty && !$v.form.email.required"
          msg="Email is required!"
        >
          <nb-input
            v-model="form.email"
            placeholder="Email"
            auto-capitalize="none"
            :on-blur="() => $v.form.email.$touch()"
          />
        </InputWithError>
        <InputWithError
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
        <nb-button :on-press="login" block><nb-text>Login</nb-text></nb-button>
        <nb-button transparent :on-press="goToRegister"
          ><nb-text>Register new account</nb-text></nb-button
        >
        <nb-button transparent :on-press="goToRecoverPassword"
          ><nb-text>Forgot Password?</nb-text></nb-button
        >
      </nb-form>
    </nb-content>
  </nb-container>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import users from "../data/userMock.json";
import { Toast } from "native-base";
import AppNavigaton from "../react-components/AppNavigationEvents";

//import { AsyncStorage } from "@react-native-community/async-storage";

export default {
  components: {
    AppNavigaton,
  },
  props: { navigation: { type: Object } },

  data() {
    return {
      isCheckingUser: false,
      form: {
        email: "",
        password: "",
      },
    };
  },
  validations: {
    form: {
      email: {
        required,
      },
      password: {
        required,
      },
    },
  },
  async created() {
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
          .then(() => {
            alert(JSON.stringify(user));
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
