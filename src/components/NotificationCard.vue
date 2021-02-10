<template>
  <view>
    <nb-card
      v-for="(notification, notificationIndex) in notifications"
      v-bind:key="notificationIndex"
    >
      <nb-card-item>
        <nb-text> {{ notification.schedule.time }} </nb-text>
        <nb-text>Need: {{ notification.need.type }} </nb-text>
        <nb-text>Pet: {{ notification.pet.petName }} </nb-text>
        <nb-text>User: {{ notification.schedule.assignedUser }}</nb-text>
        <nb-button><nb-text>Check</nb-text></nb-button>
      </nb-card-item>
    </nb-card>
  </view>
</template>
<script>
export default {
  props: {
    notifications: {
      type: Array,
      default: () => {},
    },
  },
  methods: {},
};
</script>