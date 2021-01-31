<template>
  <nb-container>
    <AppHeader screen="Settings" />
    <nb-content padder>
      <nb-button block light :on-press="goToAccount">
        <nb-text>Account Settings</nb-text>
      </nb-button>
      <nb-button block light :on-press="goToNotificationSettings">
        <nb-text>Notification Settings</nb-text>
      </nb-button>
      <nb-button block light :on-press="goToSharePet">
        <nb-text>Share Pet</nb-text>
      </nb-button>
    </nb-content>
  </nb-container>
</template>

<script>
export default {
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.user.pets;
    },
  },
  created() {
    this.$store.dispatch("pets/fetchPets");
    this.$store.dispatch("auth/fetchCurrentUser");
  },
  methods: {
    goToAccount() {
      let user = this.user;
      this.navigation.navigate("Account", {user: user});
    },
    goToNotificationSettings() {
      this.navigation.navigate("NotificationSettings");
    },
    goToSharePet() {
      this.navigation.navigate("SharePet");
    },
  },
};
</script>
