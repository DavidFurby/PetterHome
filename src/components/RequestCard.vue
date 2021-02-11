<template>
  <view>
    <nb-content v-for="(invite, inviteIndex) in invites" :key="inviteIndex">
      <nb-card>
        <nb-card-item>
          <nb-text>
            {{ invite.user.username }} wants to share their pet
            {{ invite.pet.petName }} with you
          </nb-text>
        </nb-card-item>
        <nb-button :on-press="() => acceptRequest(invite.inviteId)"
          ><nb-text>Accept</nb-text>
        </nb-button>
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
    invites: {
      type: Array,
      default: () => {},
    },
    acceptRequest: {
      type: Function,
    },
  },
  computed: {},
};
</script>