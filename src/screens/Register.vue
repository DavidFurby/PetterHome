<template>
  <nb-container>
    <AppHeader screen="Register" />
    <nb-content>
      <nb-form>
        <InputWithError
          class="textInput"
          :error="$v.form.username.$dirty && !$v.form.username.minLength"
          msg="Minimum length of 6 characters!"
        >
          <nb-input
            v-model="form.username"
            placeholder="Username"
            auto-captialize="none"
            :on-blur="() => $v.form.username.$touch()"
          />
        </InputWithError>
        <InputWithError
          class="textInput"
          :error="$v.form.email.$dirty && !$v.form.email.validEmail"
          msg="Email format must be valid!"
        >
          <nb-input
            v-model="form.email"
            placeholder="Email"
            auto-captialize="none"
            :on-blur="() => $v.form.email.$touch()"
          />
        </InputWithError>
        <InputWithError
          class="textInput"
          :error="$v.form.password.$dirty && !$v.form.password.required"
          msg="Password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="form.password"
            placeholder="Password"
            :on-blur="() => $v.form.password.$touch()"
          />
        </InputWithError>
        <InputWithError
          class="textInput"
          :error="
            $v.form.passwordConfirmation.$dirty &&
            !$v.form.passwordConfirmation.sameAsPassword
          "
          msg="Must match Password Confirmation!"
        >
          <nb-input
            secure-text-entry
            v-model="form.passwordConfirmation"
            placeholder="Password Confirmatinon"
            :on-blur="() => $v.form.passwordConfirmation.$touch()"
          />
        </InputWithError>
        <nb-button class="button" block :on-press="register"
          ><nb-text class="text">Register</nb-text></nb-button
        >
        <nb-button class="subButton" transparent :on-press="goToLogin"
          ><nb-text class="text">Return to login</nb-text></nb-button
        >
      </nb-form>
    </nb-content>
  </nb-container>
</template>
<script>
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      form: {
        usernam: "",
        email: "",
        password: "",
        passwordConfirmation: "",
      },
    };
  },

  validations: {
    form: {
      username: { minLength: minLength(6) },
      email: { validEmail: email },
      password: { required },
      passwordConfirmation: { sameAsPassword: sameAs("password") },
    },
  },
  methods: {
    register() {
      this.$v.form.$touch();
      if (!this.$v.form.$invalid) {
        this.$store
          .dispatch("auth/register", this.form)
          .then(() => this.navigateToLogin())
          .catch((error) => {
            Toast.show({
              text: "Registration failed",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      }
    },
    goToLogin() {
      this.navigation.navigate("Login");
    },
    navigateToLogin() {
      Toast.show({
        text: "Registration succeeded",
        buttonText: "ok",
        type: "success",
        duration: 3000,
      });
      this.navigation.navigate("Login");
    },
  },
};
</script>
<style>
.button {
  border-radius: 20;
  margin-top: 10;
  margin-right: 40;
  margin-left: 40;
  background-color: #edccaf;
}
.textInput {
  margin-right: 40;
  margin-left: 50;
}
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
.subButton {
  border-radius: 20;
  margin-top: 10;
  margin-right: 50;
  margin-left: 130;
  background-color: #edccaf;
}
</style>