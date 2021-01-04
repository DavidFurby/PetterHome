<template>
  <view>
    <nb-card-item v-for="request in requests" :key="request.id">
      <nb-body full info>
        <nb-text
          >{{ request.user }} want to share their {{ request.pet }}
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
    requests: {
      type: Array,
      default: () => [],
    },
    acceptRequest: {
        type: Function,
    }
  },
};
</script>