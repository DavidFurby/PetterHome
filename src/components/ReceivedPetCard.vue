<template>
  <view>
    <nb-content>
      <nb-card>
        <nb-card-item>
          <nb-body full info>
            <nb-text>name: {{ receivedPet }}</nb-text>
          </nb-body>
        </nb-card-item>
      </nb-card>
    </nb-content>
  </view>
</template>

<script>
export default {
  props: {
    receivedPet: {
      type: Object,
      default: () => [],
    },
  },
};
</script>
