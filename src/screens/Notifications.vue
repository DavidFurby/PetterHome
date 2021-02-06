<template>
  <nb-container>
    <AppHeader screen="Notifications" />
    <scroll-view>
      <NotificationCard :time="time" :pets="pets" />
    </scroll-view>
    <nb-button block>
      <nb-text>View More</nb-text>
    </nb-button>
  </nb-container>
</template>


<script>
import NotificationCard from "../components/NotificationCard";

export default {
  components: {
    NotificationCard,
  },
  async updated() {
   await checkSchedule();
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.user.pets;
    },
  },
};
</script>