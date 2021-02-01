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
const hour = new Date().getHours();
const min = new Date().getMinutes();

export default {
  components: {
    NotificationCard,
  },
  data() {
    return {
      pets: petData,

      time: hour + ":" + min,
    };
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
};
</script>