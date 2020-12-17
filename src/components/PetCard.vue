<template>
  <nb-card>
    <nb-card-item info button :on-press="()  => navigateToPet(pet.id)">
      <nb-body full >
        <nb-text>{{ pet.petName }} </nb-text>
        <nb-text>{{ pet.animal }} </nb-text>
        <nb-text>{{ pet.age }} </nb-text>
        <nb-text>{{ pet.gender }} </nb-text>
        <nb-text>{{ pet.breed }} </nb-text>
      </nb-body>
      <nb-button :on-press="() => navigateToPetSchema(pet.id)"><nb-text>Calender</nb-text></nb-button>
    </nb-card-item>
  </nb-card>
</template>
<script>
export default {
  props: {
    pet: {
      type: Object,
      required: !true,
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
};
</script>