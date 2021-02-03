<template>
  <view>
    <nb-card-item v-for="invite in invites" :key="invite.id">
      <nb-body full info>
        <nb-text
          >{{ invite.user }} want to share their {{ invite.pet }}
        </nb-text>
        <nb-button :on-press="acceptRequest"
          ><nb-text>Accept</nb-text></nb-button
        >
      </nb-body>
    </nb-card-item>
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