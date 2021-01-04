<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Share Pet" />
      <SharePetCard
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
import petMock from "../data/petMock.json";
export default {
  components: {
    SharePetCard,
  },
  data() {
    return {
      pets: petMock,
    };
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