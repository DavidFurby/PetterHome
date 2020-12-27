<template>
  <nb-container>
    <nb-header><nb-title>Register</nb-title></nb-header>
    <nb-content>
      <nb-form>
  <nb-form>
        <InputWithError
          :error="$v.form.username.$dirty && !$v.form.username.minLength"
          msg="Minimum length is 8 characters!"
         >          
        <nb-input v-model="form.username" placeholder="Username" auto-captialize="none" :on-blur="() => $v.form.username.$touch()"
/>
        </InputWithError>
        <InputWithError  :error="$v.form.email.$dirty && !$v.form.email.validEmail"
          msg="Email format must be valid!">
          <nb-input v-model="form.email" placeholder="Email" :on-blur="() => $v.form.email.$touch()" />
        </InputWithError>
        <InputWithError    :error="$v.form.password.$dirty && !$v.form.password.required"
          msg="Password is required!">
          <nb-input
            secure-text-entry
            v-model="form.password"
            placeholder="Password"
            :on-blur="() => $v.form.password.$touch()"
          />
        </InputWithError>
        <InputWithError :error="$v.form.passwordConfirmation.$dirty && !$v.form.passwordConfirmation.sameAsPassword" msg="Must match password!">
          <nb-input
            secure-text-entry
            v-model="form.passwordConfirmation"
            placeholder="Password Confirmatinon"
            :on-blur="() => $v.form.passwordConfirmation.$touch()"
          />
        </InputWithError>
        <nb-button :on-press="register"><nb-text>Register</nb-text></nb-button>
        <nb-button transparent :on-press="goToLogin"
          ><nb-text>Return to login</nb-text></nb-button
        >
      </nb-form>
    </nb-content>
  </nb-container>
</template>
<script>
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";
import InputWithError from "../components/InputWithError";
export default {
  components: {
    InputWithError,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      form: {
        username: "",
        email: "",
        password: "",
        passwordConfirmation: "",
      },
    };
  },
  validations: {
    form: {
      username: { minLength: minLength(12) },
      email: { validEmail: email },
      password: { required },
      passwordConfirmation: { sameAsPassword: sameAs("password") },
    },
  },
  methods: {
    register() {
      this.$v.form.$touch();
      alert(JSON.stringify(this.form));
    },
    goToLogin() {
      this.navigation.navigate("Login");
    },
  },
};
</script>