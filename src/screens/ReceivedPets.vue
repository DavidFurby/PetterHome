<template>
  <view>
    <AppHeader :screen="header" />
    <nb-content v-if="ifReceivedPets">
      <ReceivedPetCard
        v-for="(receivedPet, receivedPetIndex) in receivedPets"
        :key="receivedPetIndex"
        :receivedPet="receivedPet"
      />
    </nb-content>
    <nb-text v-else>No Received pets available</nb-text>
  </view>
</template>
<script>
import ReceivedPetCard from "../components/ReceivedPetCard";
export default {
  components: {
    ReceivedPetCard,
  },
  data() {
    return {
      header: "Received Pets",
    };
  },

  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    receivedPets() {
      return this.$store.state.receivedPets.receivedPets;
    },
    ifReceivedPets() {
      return Object.keys(this.receivedPets).length > 0;
    },
  },
};
</script>