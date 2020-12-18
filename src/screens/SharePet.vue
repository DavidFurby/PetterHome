<template>
  <nb-container>
    <nb-header><nb-title>Share Pet</nb-title></nb-header>
    <SharePetCard v-for="pet in petStore" :key="pet.id" :pet="pet" :navigateToShareList="goToShareList"/>
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
    petStore() {
      let petTemp = this.$store.state.pets;
      return petTemp;
    },
  },
  methods: {
    goToShareList(petId) {
      this.navigation.navigate("ShareList", { petId: { petId } });
    },
  },
};
</script>