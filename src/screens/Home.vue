<template>
  <nb-container>
    <AppHeader screen="Home" />
    <scroll-view>
      <PetCard
        v-for="pet in petData"
        :key="pet.id"
        :pet="pet"
        :navigateToPet="goToPetScreen"
        :navigateToPetSchema="goToPetSchema"
      />
    </scroll-view>
    <nb-button block :on-press="goToAddPetScreen" :pets="petData">
      <nb-text>Add Pet</nb-text>
      <nb-text v-if="user">Welcome {{ user.username }}</nb-text>
    </nb-button>
  </nb-container>
</template>
<script>
import mock from "../data/petMock.json";
import PetCard from "../components/PetCard";
import AppHeader from "../components/AppHeader";

export default {
  components: {
    PetCard,
    AppHeader,
  },

  data() {
    return {
      petData: mock,
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    pets() {
      petData = this.$store.state.pets;
      return petData;
    },
    user() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    goToPetScreen(petId) {
      this.navigation.navigate("Pet", { petId: { petId } });
    },
    goToAddPetScreen() {
      this.navigation.navigate("AddPet");
    },
    goToPetSchema(petId) {
      this.navigation.navigate("Schema", { petId: { petId } });
    },
  },
};
</script>
<style scoped>
</style>