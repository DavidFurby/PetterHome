<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Share Pet" />
      <SharePetCard
        v-for="pet in pets"
        :key="pet.id"
        :pet="pet"
        :navigateToShareList="() => goToShareList()"
        :navigateToPet="() => navigateToPet()"
      />
    </scroll-view>
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
  },
  methods: {
    goToShareList(pet) {
      const user = this.user;
      this.navigation.navigate("ShareList", { pet: pet, user: user });
    },
    navigateToPet(pet) {
      const user = this.user; 
      this.navigation.navigate("Pet", { pet: pet, user: user });
    },
  },
};
</script>