<template>
  <nb-container>
        <nb-header><nb-title>Pet</nb-title></nb-header>
    <nb-content padder>
      <nb-text>{{ animal.name }}</nb-text>
      <nb-text>Animal: {{ animal.animal }}</nb-text>
      <nb-text>Breed: {{ animal.breed }}</nb-text>
      <nb-text>Weight: {{ animal.weight }}</nb-text>
      <nb-text>Height:{{ animal.height }}</nb-text>
      <nb-text>Birthday:{{ animal.birth }}</nb-text>
      <nb-text>Walks:{{ animal.walks }}</nb-text>
      <nb-text>Medication:{{ animal.medication }}</nb-text>
      <nb-text>Allergies:{{ animal.allergies }}</nb-text>
      <nb-button><nb-text>Delete {{animal.name}} from the app</nb-text></nb-button>
    </nb-content>
  </nb-container>
</template>
<script>
export default {
  props: {
    animal: {
      type: Array,
      default: () => [],
    },
  },
};
</script>