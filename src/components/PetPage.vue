
<template>
  <nb-content>
    <nb-card>
      <nb-card-item>
        <nb-text :style="{ width: 280 }">Animal:</nb-text>
        <nb-text>{{ animal.animal }}</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-text :style="{ width: 280 }">Breed:</nb-text>
        <nb-text>{{ animal.breed }}</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-text :style="{ width: 280 }">Age: </nb-text>
        <nb-text>{{ pet.petAge }} years old</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-text :style="{ width: 280 }">Weight: </nb-text>
        <nb-text>{{ pet.weight }} kg</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-text :style="{ width: 280 }">Height: </nb-text>
        <nb-text>{{ pet.height }} cm</nb-text>
      </nb-card-item>
    </nb-card>
  </nb-content>
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