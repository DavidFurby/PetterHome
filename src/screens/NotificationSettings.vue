<template>
  <nb-container>
        <nb-header><nb-title>Notification Settings</nb-title></nb-header>

    <nb-content padder>
      <nb-form v-for="animal in animals" :key="animal.id">
        <nb-text>{{ animal.name }}</nb-text>
        <nb-picker
          note
          mode="dropdown"
          :style="{ width: 120 }"
          :selectedValue="selected"
          :onValueChange="onValueChange"
        >
          <item label="Food" value="key0" />
          <item label="Walks" value="key1" />
          <item label="Medicine" value="key2" />
        </nb-picker>
      </nb-form>
    </nb-content>
  </nb-container>
</template>
<script>
import { Picker } from "native-base";

export default {
  props: {
    animals: {
      type: Array,
      default: () => [],
    },
  },
  components: { Item: Picker.Item },
  data: function () {
    return {
      selected: "key1",
    };
  },
  methods: {
    onValueChange: function (value) {
      this.selected = value;
    },
  },
};
</script>