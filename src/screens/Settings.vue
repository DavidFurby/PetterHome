<template>
  <nb-container
    ><nb-content padder>
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
      alert("Account");
      this.navigation.navigate("Account");
    },
    goToNotificationSettings() {
      alert("notifications");
      this.navigation.navigate("NotificationSettings");
    },
    goToSharePet() {
      alert("share pet");
      this.navigation.navigate("SharePet");
    },
  },
};
</script>
