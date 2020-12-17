<template>
  <nb-container>
    <nb-content padder>
          <nb-header><nb-title>Add Pet</nb-title></nb-header>
 
    </nb-content>
  </nb-container>
</template>
<script>
import { Picker } from "native-base";

export default {
  props: {
    animalsAvailable : {
      type: Array,
      default: () => [],
    },
  },
  components: { Item: Picker.Item },
  data: function () {
    return {
      selected: "key0",
    };
  },
  methods: {
    onValueChange: function (value) {
      this.selected = value;
    },
  },
};
</script>