
<template>
  <nb-content>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>Name:</nb-text>
        </nb-body>
        <nb-text>{{ pet.petName }}</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>Animal:</nb-text>
        </nb-body>
        <nb-text>{{ animal.animal }}</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-body>
          <nb-text>Breed:</nb-text>
        </nb-body>
        <nb-text>{{ animal.breed }}</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>Age: </nb-text>
        </nb-body>
        <nb-text>{{ pet.petAge }} years old</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>Weight: </nb-text>
        </nb-body>
        <nb-text>{{ pet.weight }} kg</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-body> <nb-text>Height: </nb-text></nb-body>
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