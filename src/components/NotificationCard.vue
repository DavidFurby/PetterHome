<template>
  <view>
    <nb-card
      v-for="(notification, notificationIndex) in notifications"
      v-bind:key="notificationIndex"
    >
      <nb-card-item>
        <nb-label> {{ notification.schedule.time }} </nb-label>
        <nb-label>Need: {{ notification.need.type }} </nb-label>
        <nb-label>Pet: {{ notification.pet.petName }} </nb-label>
        <nb-label>User: {{ notification.schedule.assignedUser }}</nb-label>
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