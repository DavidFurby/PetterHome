<template>
  <view>
    <nb-content v-for="(invite, inviteIndex) in invites" :key="inviteIndex">
      <nb-text
        >{{ invite.user.username }} wants to share their pet
        {{ invite.pet.petName }} with you</nb-text
      >
      <nb-card>
        <nb-card-item>
          <nb-body full info>
            <nb-text>{{ invite.pet.petName }}</nb-text>
            <nb-text>{{ invite.pet.petAge }}</nb-text>
            <nb-text>{{ invite.pet.animal.animal }}</nb-text>
            <nb-text>{{ invite.pet.animal.breed }}</nb-text>
            <nb-text>{{ invite.pet.gender }}</nb-text>
            <nb-text>{{ invite.pet.height }}</nb-text>
            <nb-text>{{ invite.pet.weight }}</nb-text>
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
export default {
  props: {
    invites: {
      type: Array,
      default: () => [],
    },

    acceptRequest: {
      type: Function,
    },
  },
};
</script>