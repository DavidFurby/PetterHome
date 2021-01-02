<template>
  <view>
      <nb-card v-for="pet in pets" v-bind:key="pet.id">
        <nb-card-item>
          <nb-body>
            <nb-text>{{ time }} {{ pet.petName }} needs to be walked</nb-text>
          </nb-body>
        </nb-card-item>
      </nb-card>
 
  </view>
</template>
<script>
export default {
  props: {
    pets: Array,
    default: () => [],
    time: String,
    default: () => [],
  },
};
</script>