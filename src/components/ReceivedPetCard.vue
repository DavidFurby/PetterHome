<template>
  <view>
    <nb-content
      v-for="(receivedPet, receivedPetIndex) in receivedPets"
      :key="receivedPetIndex"
    >
      <nb-card>
        <nb-card-item>
          <nb-body full info>
            <nb-text>{{ receivedPet.pet.petName }}</nb-text>
            <nb-text>{{ receivedPet.pet.petAge }}</nb-text>
            <nb-text>{{ receivedPet.pet.animal.animal }}</nb-text>
            <nb-text>{{ receivedPet.pet.animal.breed }}</nb-text>
            <nb-text>{{ receivedPet.pet.gender }}</nb-text>
            <nb-text>{{ receivedPet.pet.height }}</nb-text>
            <nb-text>{{ receivedPet.pet.weight }}</nb-text>
            <nb-button :on-press="() => acceptRequest(invite.inviteId)"
              ><nb-text>Accept</nb-text>
            </nb-button>
          </nb-body>
        </nb-card-item>
      </nb-card>
    </nb-content>
  </view>
</template>

<script>
export default {};
</script>

<style>
</style>