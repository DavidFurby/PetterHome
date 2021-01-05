<template>
  <view>
    <nb-text>{{ user[0].username }}</nb-text>
    <nb-text>Email: {{ user[0].email }}</nb-text>
    <nb-text>Account ID: {{ user[0].id }}</nb-text>
    <nb-text>Phone Number: {{ user[0].phoneNumber }}</nb-text>
    <nb-text>Region: {{ user[0].region }}</nb-text>
  </view>
</template>
<script>
export default {
  props: {
    user: {
      type: Array,
      default: () => [],
    },
  },
};
</script>
<style scoped>
</style>