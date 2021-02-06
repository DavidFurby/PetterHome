<template>
  <nb-content>
    <nb-card>
      <nb-card-item info button :on-press="() => navigateToPet(pet)">
        <nb-body full>
          <nb-text>{{ pet.petName }} </nb-text>
          <nb-text>Animal: {{ animal.animal }} </nb-text>
          <nb-text>Age: {{ pet.petAge }} </nb-text>
          <nb-text>Gender: {{ pet.gender }} </nb-text>
          <nb-text>Breed: {{ animal.breed }} </nb-text>
        </nb-body>
        <nb-content>
          <nb-button :on-press="() => navigateToShareList(pet)">
            <nb-text>Users</nb-text>
          </nb-button>
        </nb-content>
      </nb-card-item>
    </nb-card>
  </nb-content>
</template>
<script>
export default {
  props: {
    pet: {
      type: Object,
      required: !true,
    },
    navigateToShareList: {
      type: Function,
      required: true,
    },
    navigateToPet: {
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