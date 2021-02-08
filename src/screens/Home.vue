<template>
  <nb-container v-if="hasPets">
    <AppHeader root :screen="title" />

    <scroll-view>
      <PetCard
        v-for="pet in pets"
        :key="pet.id"
        :pet="pet"
        :navigateToPet="goToPetScreen"
        :navigateToPetSchema="goToPetSchema"
      />
    </scroll-view>
    <nb-button block :on-press="goToAddPetScreen" :pets="pets">
      <nb-text>Add Pet</nb-text>
    </nb-button>
  </nb-container>
  <nb-container v-else class="spinner-container">
    <nb-spinner color="blue" />
  </nb-container>
</template>
<script>
import PetCard from "../components/PetCard";

export default {
  components: {
    PetCard,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      notification: {},
      title: "Home Screen",
    };
  },
  created() {
    this.$store.dispatch("pets/fetchPets", this.user.id);
  },
  methods: {
    goToPetScreen(petId) {
      this.navigation.navigate("Pet", { petId: petId});
    },
    goToAddPetScreen() {
      this.navigation.navigate("AddPet");
    },
    goToPetSchema(petId) {

      this.navigation.navigate("Needs", { petId: petId });
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.$store.state.pets.items;
    },
     hasPets() {
      return  Object.keys(this.pets).length > 0;
    },
  },
};
</script>
<style scoped>
</style>