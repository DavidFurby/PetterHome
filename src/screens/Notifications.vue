<template>
  <nb-container>
    <AppHeader screen="Notifications" />
    <nb-content v-if="ifNotifications">
      <scroll-view>
        <NotificationCard
          :notifications="notifications"
          :checkNotification="checkNotification"
        />
      </scroll-view>
    </nb-content>
  </nb-container>
</template>


<script>
import { Toast } from "native-base";

import NotificationCard from "../components/NotificationCard";

export default {
  data() {
    return {
      currentTime: Date.now(),
    };
  },
  components: {
    NotificationCard,
  },
  mounted() {
    this.$store.dispatch("notifications/fetchNotifications", this.user.id);
  },
  updated() {

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
  methods: {
    checkNotification(notification) {
      if (notification.schedule.assignedUser == this.user.username) {
        notification.check = !notification.check;
        notificationId = notification.notificationId;
        this.$store
          .dispatch("notifications/checkNotification", notificationId)
          .then(() => {
              Toast.show({
              text: "checked notification",
              buttonText: "Ok",
              type: "success",
              duration: 3000,
            });
          })
          .catch((err) => {
            Toast.show({
              text: "Something went wrong!",
              buttonText: "Ok",
              type: "success",
              duration: 3000,
            });
          });
      } else {
        Toast.show({
          text: "Only the assigned user can check the need",
          buttonText: "ok",
          type: "danger",
          duration: 3000,
        });
      }
    },
  },
};
</script>