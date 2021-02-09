<template>
  <view>
    <nb-card>
      <nb-card-item>
        <nb-text>AccountName: {{ user.username }}</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-text>Email: {{ user.email }}</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-text>Phone Number: {{ user.phoneNumber }}</nb-text>
      </nb-card-item>
    </nb-card>
    <nb-card>
      <nb-card-item>
        <nb-text>Region: {{ user.region }}</nb-text>
      </nb-card-item>
    </nb-card>
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