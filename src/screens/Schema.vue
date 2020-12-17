<template>
  <nb-container>
    <nb-header
      ><nb-title>{{ pet.name }} Schema</nb-title></nb-header
    >

    <nb-content>
      <nb-text>Calender</nb-text>
    </nb-content>
  </nb-container>
</template>

<script>
export default {
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