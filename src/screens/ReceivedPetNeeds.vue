<template>
  <nb-container>
    <AppHeader :screen="pet.petName" />
    <nb-content>
      <NeedPage
        v-for="(need, needIndex) in needs"
        :key="needIndex"
        :need="need"
      />
    </nb-content>
  </nb-container>
</template>

<script>
import NeedPage from "../components/ReceivedPetNeedPage";
export default {
  components: {
    NeedPage,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    pet() {
      return this.$store.state.receivedPets.receivedPet;
    },
    needs() {
      console.log(this.pet);
      let needs = this.pet.needs;
      return needs;
    },
    isNeedsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
  },
  async created() {
    const petId = this.navigation.getParam("petId", "undefined");
    let params = {};
    params.petId = petId;
    this.$store.dispatch("receivedPets/fetchReceivedPetById", params);
  },
};
</script>

<style></style>
