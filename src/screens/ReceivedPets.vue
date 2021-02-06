<template>
  <view>
    <AppHeader :screen="header" />
    <ReceivedPetCard v-if="ifReceivedPets" :receivedPets="receivedPets" />
    <nb-text v-else>No Received pets available</nb-text>
  </view>
</template>
<script>
import ReceivedPetCard from "../components/ReceivedPetCard";
export default {
  data() {
    return {
      header: "Received Pets",
      receivedPets: [],
    };
  },
  components: {
    ReceivedPetCard,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  mounted() {
    this.receivedPets = this.$store.state.receivedPets.receivedPets;
  },
  computed: {
    ifReceivedPets() {
      return Object.keys(this.receivedPets).length > 0;
    },
  },
};
</script>