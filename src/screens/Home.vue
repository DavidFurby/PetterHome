<template>
  <nb-container>
    <AppHeader :screen="user.username" />
    <nb-text v-if="user" :style="{ paddingLeft: 20 }"
      >Welcome {{ user.username }}</nb-text
    >
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
  methods: {
    goToPetScreen(petId) {
      let params = {petId, user}
      this.navigation.navigate("Pet", { params: { params } });
    },
    goToAddPetScreen() {
      let user = this.user.id;
      console.log(user)
      this.navigation.navigate("AddPet", { user: { user } });
    },
    goToPetSchema(petId) {
      this.navigation.navigate("Schema", { petId: { petId } });
    },
  },
};
</script>
<style scoped>
</style>