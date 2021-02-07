<template>
  <nb-container v-if="hasUser">
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
  methods: {
    goToPetScreen(petId) {
      const userId = this.user.id;
      this.navigation.navigate("Pet", { petId: petId, userId: userId });
    },
    goToAddPetScreen() {
      let user = this.user;
      this.navigation.navigate("AddPet", { user: user });
    },
    goToPetSchema(petId) {
      const userId = this.user.id;

      this.navigation.navigate("Needs", { petId: petId, userId: userId });
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.user.pets;
    },
    hasUser() {
      return Object.keys(this.user).length > 0;
    },
  },
};
</script>
<style scoped>
</style>