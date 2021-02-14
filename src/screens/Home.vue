<template>
  <nb-container>
    <AppHeader root :screen="title" />

    <nb-content v-if="hasPets">
      <scroll-view>
        <PetCard
          :style="{
            marginTop: 30,
            marginLeft: 20,
            marginRight: 20,
            marginBottom: 40,
          }"
          v-for="pet in pets"
          :key="pet.id"
          :pet="pet"
          :navigateToPet="goToPetScreen"
          :navigateToPetSchema="goToPetSchema"
        />
      </scroll-view>
    </nb-content>
    <nb-container v-else>
      <nb-text>You haven't added any pets yet</nb-text>
    </nb-container>
    <nb-button success block :on-press="goToAddPetScreen" :pets="pets">
      <nb-text class="text"
        >Add Pet <nb-icon :style="{ fontSize: 15, color: 'white' }" name="paw"
      /></nb-text>
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
  async created() {
    this.$store.dispatch("pets/fetchPets", this.user.id);
  },
  methods: {
    goToPetScreen(petId) {
      this.navigation.navigate("Pet", { petId: petId });
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
      return Object.keys(this.pets).length > 0;
    },
  },
};
</script>
<style>
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
</style>
