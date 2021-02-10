
<template>
  <nb-card>
    <nb-card-item>
      <nb-body>
        <nb-text>Name: {{ pet.petName }}</nb-text>
      </nb-body>
    </nb-card-item>
    <nb-card-item>
      <nb-body>
        <nb-text>Animal: {{ animal.animal }}</nb-text></nb-body
      ></nb-card-item
    >
    <nb-card-item>
      <nb-body>
        <nb-text>Breed: {{ animal.breed }}</nb-text></nb-body
      ></nb-card-item
    >
    <nb-card-item>
      <nb-body>
        <nb-text>Age: {{ pet.age }}</nb-text></nb-body
      ></nb-card-item
    >
    <nb-card-item>
      <nb-body>
        <nb-text>Weight: {{ pet.weight }}</nb-text></nb-body
      ></nb-card-item
    >
    <nb-card-item>
      <nb-body>
        <nb-text>Height: {{ pet.height }}</nb-text></nb-body
      ></nb-card-item
    >
    <nb-card-item>
      <nb-body>
        <nb-text>Birthday: {{ pet.birthday }}</nb-text></nb-body
      ></nb-card-item
    >
  </nb-card>
</template>

<script>
export default {
  props: {
    pet: {
      type: Object,
    },
    animal: { type: Object },
  },
};
</script>

<style>
</style>