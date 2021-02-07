<template>
  <nb-container>
    <AppHeader screen="Share Pet" />
    <nb-content v-if="ifPets">
      <scroll-view>
        <SharePetCard
          v-for="pet in pets"
          :key="pet.id"
          :pet="pet"
          :navigateToShareList="goToShareList"
          :navigateToPet="navigateToPet"
        />
      </scroll-view>
    </nb-content>
    <nb-text v-else></nb-text>
  </nb-container>
</template>
<script>
import SharePetCard from "../components/SharePetCard";
export default {
  components: {
    SharePetCard,
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
    ifPets() {
      return Object.keys(this.pets).length > 0;
    },
  },
  created() {
    this.$store.dispatch("invites/fetchInvites", this.user.id);
    this.$store.dispatch("receivedPets/fetchReceivedPets", this.user.id);
  },
  methods: {
    goToShareList(petId) {
      const userId = this.user.id;

      this.navigation.navigate("ShareList", { petId: petId, userId: userId });
    },
    navigateToPet(petId) {
      const userId = this.user.id;
      this.navigation.navigate("Pet", { petId: petId, userId: userId });
    },
  },
};
</script>