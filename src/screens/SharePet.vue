<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Share Pet" />
      <SharePetCard :v-if="hasUser"
        v-for="pet in pets"
        :key="pet.id"
        :pet="pet"
        :navigateToShareList="goToShareList"
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
  created() {
    this.$store.dispatch("auth/fetchCurrentUser");
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
  methods: {
    goToShareList(pet) {
      const user = this.user;
      this.navigation.navigate("ShareList", { pet: pet, user: user });
    },
  },
};
</script>