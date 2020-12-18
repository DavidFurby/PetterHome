<template>
  <nb-content padder>
    <nb-form>
      <nb-text>{{ pet.petName }}</nb-text>
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
</template>
<script>
import { Picker } from "native-base";

export default {
  components: { Item: Picker.Item },

  data() {
    return {
      petId: undefined,
    };
  },
  props: {
    pet: { type: Object, required: !true },
    selected: { type: String },
  },
  computed: {
    pet() {
      return this.$store.state.pets;
    },
  },
  created() {
    const petIdTemp = this.navigation.getParam("petId", "undefined");
    this.petId = petIdTemp;
    alert(this.petId);
  },
};
</script>