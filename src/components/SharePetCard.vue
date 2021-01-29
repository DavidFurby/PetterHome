<template>

  <nb-card>
    <nb-card-item button :on-press="() => navigateToShareList(pet.id)">
      <nb-body full info>
        <nb-text>{{ pet.petName }} </nb-text>
        <nb-text> {{ pet.age }} år </nb-text>
        <nb-text>{{ pet.breed }} </nb-text>
      </nb-body>
    </nb-card-item>
  </nb-card>
</template>
<script>
export default {
    props:{
        pet: {
            type: Object,
            required: !true,
        },
        navigateToShareList: {
            type: Function,
            required: true,
        }
    }
};
</script>