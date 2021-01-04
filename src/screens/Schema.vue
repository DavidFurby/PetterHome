<template>
  <nb-container>
    <AppHeader screen="Schema" />
    <nb-content></nb-content>
  </nb-container>
</template>

<script>
import userMock from "../data/userMock.json"
export default {
  data() {
    return{
      petTemp:userMock
    }
  },
  props: {
    pet: {
      type: Array,
      default: () => [],
    },
    navigation: {
      type: Object,
    },
    computed: {
      pet() {
        return this.store.state.pets;
      },
    },
    created() {
      const petId = this.navigation.getParam("petId", "undefined");

      this.$store.dispatch("pets/fetchPetById", petId);
    },
  },
};
</script>

<style>
</style>