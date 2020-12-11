<template>
  <nb-container>
    <nb-header><nb-title>Settings</nb-title></nb-header>
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
  methods: {
    goToAccount() {
      this.navigation.navigate("Account");
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
