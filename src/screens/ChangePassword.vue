<template>
  <nb-container>
    <AppHeader screen="Change Password" />
    <nb-content>
      <nb-form>
        <InputWithError
          :error="
            $v.form.oldPasswordConfirmation.$dirty &&
            !$v.form.oldPasswordConfirmation.sameAsOldPassword
          "
          msg="Must match old password!"
        >
          <nb-input
            secure-text-entry
            v-model="form.passwordConfirmation"
            placeholder="Old Password Confirmation"
            :on-blur="() => $v.form.oldPasswordConfirmation.$touch()"
          />
        </InputWithError>
        <InputWithError
          :error="$v.form.newPassword.$dirty && !$v.form.newPassword.required"
          msg="Password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="form.password"
            placeholder="Password"
            :on-blur="() => $v.form.newPassword.$touch()"
          />
        </InputWithError>
        <InputWithError
          :error="
            $v.form.newPasswordConfirmation.$dirty &&
            !$v.form.newPasswordConfirmation.required &&
            !$v.form.newPasswordConfirmation.sameAsNewPassword
          "
          msg="Password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="form.password"
            placeholder="Password"
            :on-blur="() => $v.form.newPasswordConfirmation.$touch()"
          />
        </InputWithError>
        <nb-button :on-press="updatePassword"
          ><nb-text>Update</nb-text>
        </nb-button>
      </nb-form>
    </nb-content>
  </nb-container>
</template>
<script>
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";
import { Toast } from "native-base";
import user from "../data/userMock.json";
export default {
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      user: user,
      form: {
        oldPassword: user.password,
        oldPasswordConfirmation: "",
        newPassword: "",
        newPasswordConfirmation: "",
      },
    };
  },

  validations: {
    form: {
      oldPasswordConfirmation: {
        required,
        sameAsOldPassword: sameAs("oldPassword"),
      },
      newPassword: { required },
      newPasswordConfirmation: {
        required,
        sameAsNewPassword: sameAs("newPassword"),
      },
    },
  },
  methods: {
    updatePassword() {
      this.$v.form.$touch();
      if (this.$v.form.$invalid) {
        this.$store
          .dispatch("auth/updatePassword")
          .then(() => this.navigateToMain())
          .catch(() => {
            Toast.show({
              text: "something went wrong",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      }
      alert(JSON.stringify(this.form));
    },
  
    navigateToMain() {
      this.navigation.navigate("Main", {
        message: "Succesfull password update",
      });
    },
  },
};
</script>
