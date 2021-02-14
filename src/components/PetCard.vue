<template>
  <nb-card>
    <nb-card-item button :on-press="() => navigateToPet(pet.id)">
      <nb-body>
        <nb-text>{{ pet.petName }} </nb-text>
        <nb-text>Animal: {{ animal.animal }} </nb-text>
        <nb-text>Age: {{ pet.petAge }} </nb-text>
        <nb-text>Gender: {{ pet.gender }} </nb-text>
        <nb-text>Breed: {{ animal.breed }} </nb-text>
      </nb-body>
      <nb-button
        rounded
        class="button"
        :on-press="() => navigateToPetSchema(pet.id)"
        ><nb-text class="text">Schedules</nb-text>
      </nb-button>
    </nb-card-item>
  </nb-card>
</template>
<script>
export default {
  props: {
    pet: {
      type: Object,
    },
    navigateToPet: {
      type: Function,
      required: true,
    },
    navigateToPetSchema: {
      type: Function,
      required: true,
    },
  },
  computed: {
    animal() {
      return this.pet.animal;
    },
  },
};
</script>
<style scoped>
.button {
  background-color: #edccaf;
}
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
</style>