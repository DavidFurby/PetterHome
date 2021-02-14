<template>
  <view>
    <nb-card
      v-for="(notification, notificationIndex) in notifications"
      v-bind:key="notificationIndex"
    >
      <nb-card-item>
        <nb-body>
          <nb-text>At: {{ notification.schedule.time }}</nb-text>
          <nb-text>Need: {{ notification.need.type }} </nb-text>
          <nb-text>Pet: {{ notification.pet.petName }} </nb-text>
          <nb-text>User: {{ notification.schedule.assignedUser }}</nb-text>
        </nb-body>
        <nb-text>Done?</nb-text>
        <nb-checkbox
        :style="{borderRadius: 20}"
          :checked="notification.check"
          :on-press="() => checkNotification(notification)"
        />
      </nb-card-item>
        <nb-text :style="{ marginLeft: 300 }"> {{ notification.date }}</nb-text>
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
    checkNotification: {
      type: Function,
    },
  },
  methods: {},
};
</script>