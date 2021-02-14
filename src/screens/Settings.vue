<template>
  <nb-container>
    <AppHeader screen="Settings" />
    <nb-content padder>
      <nb-button class="button" block :on-press="goToAccount">
        <nb-text class="text">Account Settings</nb-text>
      </nb-button>
      <nb-button class="button" block :on-press="() => goToSharePet(this.user)">
        <nb-text class="text">Share Pet</nb-text>
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
  methods: {
    goToAccount() {
      let user = this.user;
      this.navigation.navigate("Account", { user: user });
    },
    goToSharePet() {
      const userId = this.user.id;
      this.navigation.navigate("SharePet", { userId: userId });
    },
  },
};
</script>
<style scoped>
.button {
  background-color: #edccaf;
}
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
</style>
