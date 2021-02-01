<template>
  <view>
    <nb-card v-for="pet in pets" v-bind:key="pet.id">
      <nb-card-item>
        <nb-body v-for="need in pet.needs" v-bind:key="need.id">
          <nb-text> {{ pet.petName }} {{need.type}}</nb-text>
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
  methods: {
  },
};
</script>