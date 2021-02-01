<template>
  <nb-container>
    <AppHeader screen="Change Password" />
    <nb-content>
      <nb-form>
        <InputWithError
          :error="$v.passwordForm.oldPassword.$dirty && !$v.passwordForm.oldPassword.required"
          msg="assigning old password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="passwordForm.oldPassword"
            placeholder="Old Password"
            :on-blur="() => $v.passwordForm.oldPassword.$touch()"
          />
        </InputWithError>
        <InputWithError
          :error="
            $v.passwordForm.oldPasswordConfirmation.$dirty &&
            !$v.passwordForm.oldPasswordConfirmation.sameAsOldPassword
          "
          msg="Must match old password!"
        >
          <nb-input
            secure-text-entry
            v-model="passwordForm.passwordConfirmation"
            placeholder="Old Password Confirmation"
            :on-blur="() => $v.passwordForm.oldPasswordConfirmation.$touch()"
          />
        </InputWithError>
        <InputWithError
          :error="$v.passwordForm.newPassword.$dirty && !$v.passwordForm.newPassword.required"
          msg="Password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="passwordForm.password"
            placeholder="Password"
            :on-blur="() => $v.passwordForm.newPassword.$touch()"
          />
        </InputWithError>
        <InputWithError
          :error="
            $v.passwordForm.newPasswordConfirmation.$dirty &&
            !$v.passwordForm.newPasswordConfirmation.required &&
            !$v.passwordForm.newPasswordConfirmation.sameAsNewPassword
          "
          msg="Password is required!"
        >
          <nb-input
            secure-text-entry
            v-model="passwordForm.password"
            placeholder="Password"
            :on-blur="() => $v.passwordForm.newPasswordConfirmation.$touch()"
          />
        </InputWithError>
        <nb-button :on-press="changePassword"
          ><nb-text>Update</nb-text>
        </nb-button>
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
      user: {
        type: Object,
      },
      passwordForm: {
        oldPassword: "",
        oldPasswordConfirmation: "",
        newPassword: "",
        newPasswordConfirmation: "",
      },
    };
  },

  validations: {
    passwordForm: {
      oldPassword: {
        required,
      },
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
 created() {
    this.user = this.navigation.getParam("user", "undefined");
  },

  methods: {
    changePassword() {
      this.$v.passwordForm.$touch();
      if (this.$v.passwordForm.$invalid) {
        const userId = this.user.id;
        const passwordForm = this.$v.passwordForm;
        const params = { userId, passwordForm };
        this.$store
          .dispatch("user/changePassword", params)
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
      alert(JSON.stringify(this.passwordForm));
    },

    navigateToMain() {
      this.navigation.navigate("Main", {
        message: "Succesfull password update",
      });
    },
  },
};
</script>
