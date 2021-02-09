<template>
  <nb-container>
    <AppHeader screen="Notifications" />
    <nb-content v-if="ifNotifications">
      <scroll-view>
        <NotificationCard :notifications="notifications" />
      </scroll-view>
    </nb-content>
    <nb-text v-else>No new notifications</nb-text>
  </nb-container>
</template>


<script>
import NotificationCard from "../components/NotificationCard";

export default {
  components: {
    NotificationCard,
  },
  created() {
    this.$store.dispatch("notifications/fetchNotifications", this.user.id);
  },
  computed: {
    notifications() {
      return this.$store.state.notifications.notifications;
    },
    ifNotifications() {
      return Object.keys(this.notifications).length > 0;
    },
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.user.pets;
    },
  },
};
</script>