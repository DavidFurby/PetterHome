<template>
  <nb-container>
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
    goToPetScreen(pet) {
      let user = this.user;

      this.navigation.navigate("Pet", { pet: pet, user: user });
    },
    goToAddPetScreen() {
      let user = this.user;
      this.navigation.navigate("AddPet", { user: user });
    },
    goToPetSchema(petData) {
      let user = this.user;
      let pet = petData;
      this.navigation.navigate("Schema", { pet: pet, user: user });
    },
  },
  async created() {
    const userId = this.user.id;
    await this.$store.dispatch("receivedPets/fetchReceivedPets", userId);
    await this.$store.dispatch("users/fetchCurrentUser", userId);
    await this.$store.dispatch("invites/fetchInvites", userId);
    await this.$store.dispatch("notifications/fetchNotifications", userId);
    
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