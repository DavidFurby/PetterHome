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
  data() {
    return {
      currentTime: Date.now(),
    };
  },
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
  methods: {
    createNotification() {
      this.pets.map((pet) => {
        pet.needs.map((need) => {
          need.schedules((schedule) => {
            if (schedule.time == this.currentTime) {
              let params = {};
              params.petId = pet.id;
              params.needId = need.id;
              params.schedule = schedule.id;
              this.$store
                .dispatch("notification/createNotification", params)
                .then((res) => {
                  console.log(res);
                })
                .catch((err) => {
                  console.log(err);
                });
            }
          });
        });
      });
    },
  },
};
</script>