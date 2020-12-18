<template>
  <nb-container>
    <nb-header><nb-title>Notifications</nb-title></nb-header>
    <NotificationCard v-for="pet in pets" v-bind:key="pet.id" :time="time" />
  </nb-container>
</template>
<script>
import NotificationCard from "../components/NotificationCard";
import petData from "../data/petMock.json";
const hour = new Date().getHours();
const min = new Date().getMinutes();

export default {
  components: {
    NotificationCard,
  },
  data: {
    pets: petData,
  },
  data() {
    return {
      time: hour + "" + min,
    };
  },
};
</script>