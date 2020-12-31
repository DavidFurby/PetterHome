<template>
  <nb-content padder>
    <nb-card>
      <nb-text>{{ pet.petName }}</nb-text>
      <nb-list-item v-for="need in pet" v-bind:key="need.id">
        <nb-checkbox :checked="setCheck" :on-press="notificationOn" />
        <nb-body><nb-text>Walks</nb-text></nb-body>
      </nb-list-item>
    </nb-card>
  </nb-content>
</template>
<script>
import { Picker } from "native-base";

export default {
  components: { Item: Picker.Item },
  props: {
    pet: { type: Object, required: !true },
    selected: { type: String },
    notificationOn: {
      type: Function,
      default: () => {},
    },
    setCheck: {type: Boolean, default: false}
  },
};
</script>