<template>
  <view>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>AccountName: </nb-text>
        </nb-body>
        <nb-text>{{ user.username }} </nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-body>
          <nb-text>Email: </nb-text>
        </nb-body>
        <nb-text>{{ user.email }} </nb-text>
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