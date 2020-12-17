<template>
  <nb-container>
    <nb-header><nb-title>Pet</nb-title></nb-header>
    <nb-content padder>
      <nb-text>{{ pet.name }}</nb-text>
      <nb-text>Animal: {{ pet.animal }}</nb-text>
      <nb-text>Breed: {{ pet.breed }}</nb-text>
      <nb-text>Weight: {{ pet.weight }}</nb-text>
      <nb-text>Height:{{ pet.height }}</nb-text>
      <nb-text>Birthday:{{ pet.birth }}</nb-text>
      <nb-text>Walks:{{ pet.walks }}</nb-text>
      <nb-text>Medication:{{ pet.medication }}</nb-text>
      <nb-text>Allergies:{{ pet.allergies }}</nb-text>
      <nb-button
        ><nb-text>Delete {{ pet.name }} from the app</nb-text></nb-button
      >
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
  },
  computed: {
    pet() {
      return this.$store.state.pets
    }
  },
  created() {
    const petId = this.navigation.getParam('petId', 'undefined')

    this.$store.dispatch('pets/fetchPetsById', petId)
  },
};
</script>