<template>
  <view>
    <nb-content v-for="(invite, inviteIndex) in invites" :key="inviteIndex">
      <nb-card>
        <nb-card-item>
          <nb-text>
            {{ invite.user.username }} wants to share their pet with you
          </nb-text>
        </nb-card-item>
      </nb-card>
      <nb-card>
        <nb-card-item>
          <nb-body full info>
            <nb-text>name: {{ invite.pet.petName }}</nb-text>
            <nb-text>age:{{ invite.pet.petAge }}</nb-text>
            <nb-text>animal: {{ invite.pet.animal.animal }}</nb-text>
            <nb-text>breed: {{ invite.pet.animal.breed }}</nb-text>
            <nb-text>gender: {{ invite.pet.gender }}</nb-text>
            <nb-text>height: {{ invite.pet.height }}</nb-text>
            <nb-text>weight: {{ invite.pet.weight }}</nb-text>
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
    user: {
      type: Object,
      default: () => {},
    },
    acceptRequest: {
      type: Function,
    },
  },
  computed: {
    invites() {
      const invites = this.$store.state.invites.invites;
      return invites;
    },
  },
};
</script>