<template>
  <view>
    <nb-text>{{ user.username }}</nb-text>
    <nb-text>Email: {{ user.email }}</nb-text>
    <nb-text>Account ID: {{ user.id }}</nb-text>
    <nb-text>Phone Number: {{ user.phoneNumber }}</nb-text>
    <nb-text>Region: {{ user.region }}</nb-text>
  </view>
</template>
<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => [],
    },
  },
};
</script>
<style scoped>
</style>