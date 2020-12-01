<template>
  <nb-container>
    <nb-content padder>
      <nb-form>
        <nb-picker
          note
          mode="dropdown"
          :style="{ width: 120 }"
          :selectedValue="selected"
          :onValueChange="onValueChange"
          v-for="animal in animalsAvailable" :key="animal.id"
        >
          <item :label="animal.animal" value="key0" />
        </nb-picker>

        <nb-input>Name: </nb-input>
        <nb-input>Weight:</nb-input>
        <nb-input>Height:</nb-input>
        <nb-input>Birthday:</nb-input>
        <nb-input>Walks:</nb-input>
        <nb-input>Medication:</nb-input>
        <nb-input>Allergies:</nb-input>
        <nb-button><nb-text>Add pet to the app</nb-text></nb-button>
      </nb-form>
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