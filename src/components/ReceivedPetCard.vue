<template>
  <view>
    <nb-content
      v-for="(receivedPet, receivedPetIndex) in desperation"
      :key="receivedPetIndex"
    >
      <nb-card>
        <nb-card-item>
          <nb-body full info>
            <nb-text>name: {{ receivedPets }}</nb-text>
            <nb-text>age:{{ receivedPet.pet.petAge }}</nb-text>
            <nb-text>animal: {{ receivedPet.pet.animal.animal }}</nb-text>
            <nb-text>breed: {{ receivedPet.pet.animal.breed }}</nb-text>
            <nb-text>gender: {{ receivedPet.pet.gender }}</nb-text>
            <nb-text>height: {{ receivedPet.pet.height }}</nb-text>
            <nb-text>weight: {{ receivedPet.pet.weight }}</nb-text>
          </nb-body>
        </nb-card-item>
      </nb-card>
    </nb-content>
  </view>
</template>

<script>
export default {
  data() {
    return {
      desperation: [],
    };
  },
  props: {
    receivedPets: {
      type: Array,
      default: () => [],
    },
  },
  mounted() {
    this.desperation = this.receivedPets;
  },
};
</script>
