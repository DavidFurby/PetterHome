<template>
  <nb-container>
    <AppHeader :screen="user.username" />
    <nb-text v-if="hasUser" :style="{ paddingLeft: 20 }"
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

  created() {
    this.$store.dispatch("auth/fetchCurrentUser", this.user.id);
  },
  methods: {
    goToPetScreen(pet) {
      this.navigation.navigate("Pet", { pet: pet });
    },
    goToAddPetScreen() {
      let user = this.user;
      this.navigation.navigate("AddPet", { user: { user } });
    },
    goToPetSchema(petData) {
      let user = this.user;
      let pet = petData;
      this.navigation.navigate("Schema", { pet: pet, user: user });
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
      return this.user && this.user.length > 0;
    },
  },
};
</script>
<style scoped>
</style>