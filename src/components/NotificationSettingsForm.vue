<template>
  <nb-content padder>
    <nb-card v-for="pet in pets" v-bind:key="pet.id">
      <nb-text>{{ pet.petName }}</nb-text>
      <nb-text>{{ pet.animal }}</nb-text>
      <nb-list-item v-for="needs in pet" v-bind:key="needs.id">
        <nb-checkbox :checked="false" :on-press="notificationOn" />
        <nb-text>{{needs.type}}</nb-text>
      </nb-list-item>
    </nb-card>
  </nb-content>
</template>
<script>
import { Picker } from "native-base";

export default {
  components: { Item: Picker.Item },
  props: {
    pets: { type: Array, required: !true },
    selected: { type: String },
    notificationOn: {
      type: Function,
      default: () => {},
    },
    setCheck: { type: Boolean, default: false },
  },
};
</script>