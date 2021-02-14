<template>
  <nb-container>
    <AppHeader :screen="header" />
    <nb-content v-if="ifReceivedPets">
      <scroll-view>
        <ReceivedPetCard
          v-for="(receivedPet, receivedPetIndex) in receivedPets"
          :key="receivedPetIndex"
          :receivedPet="receivedPet"
          :navigateToPet="goToPetScreen"
          :navigateToPetNeeds="goToPetSchema"
        />
      </scroll-view>
    </nb-content>
  </nb-container>
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
  methods: {
    goToPetScreen(petId, userId) {
      this.navigation.navigate("ReceivedPetPage", {
        petId: petId,
        userId: userId,
      });
    },
    goToPetSchema(petId) {
      this.navigation.navigate("ReceivedPetNeeds", { petId: petId });
    },
  },
};
</script>