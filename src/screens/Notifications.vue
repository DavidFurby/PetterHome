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
import petData from "../data/petMock.json";
import AppHeader from "../components/AppHeader";
const hour = new Date().getHours();
const min = new Date().getMinutes();

export default {
  components: {
    NotificationCard,
    AppHeader,
  },
  data() {
    return {
      pets: petData,

      time: hour + ":" + min,
    };
  },
};
</script>