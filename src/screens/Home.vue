<template>
  <nb-container>
    <nb-header><nb-title>Home</nb-title></nb-header>
    <nb-content>
      <PetCard
        v-for="pet in petData"
        :key="pet.id"
        :pet="pet"
        :navigateToPet="goToPetScreen"
        :navigateToPetSchema="goToPetSchema"
      />
      <nb-button block :on-press="goToAddPetScreen" :pets="petData">
        <nb-text>Add Animal</nb-text>
      </nb-button>
    </nb-content>
  </nb-container>
</template>
<script>
import mock from "../data/petMock.json";
import PetCard from "../components/PetCard";

const apiURL = "http://83.226.207.208:8080/pets";
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
      petData: mock,
    };
  },
  computed: {
    pets() {
      let petData = this.$store.state.pets;
      return petData;
    },
  },
  created() {
    fetch(apiURL)
      .then((response) => {
        console.log(response.json());
        return response.json();
      })
      .then((pets) => {
        console.log(pets);
        this.petData = pets;
      })
      .catch((error) => {
        console.log(error);
      });
    this.$store.dispatch("fetchPets");
  },
  methods: {
    goToPetScreen(petId) {
      this.navigation.navigate("Pet", {petId: {petId}});
    },
    goToAddPetScreen() {
      this.navigation.navigate("AddPet");
    },
    goToPetSchema(petId) {
      this.navigation.navigate("Schema", {petId: {petId}});
    },
  },
};
</script>
<style scoped>
</style>