<template>
  <view>
    <nb-item><nb-input placeholder="Walks"></nb-input></nb-item>
    <nb-content>
      <nb-date-picker
        :defaultDate="defaultDate"
        :minimumDate="minimumDate"
        :maximumDate="maximumDate"
        placeHolderText="Select time to walk"
        :textStyle="{ color: 'green' }"
        :placeHolderTextStyle="{ color: '#d3d3d3' }"
        :onDateChange="setDate"
      />
    </nb-content>
  </view>
</template>
<script>
export default {
  data() {
    return {
      defaultDate: new Date("2018-06-04"),
      minimumDate: new Date("2018-01-01"),
      maximumDate: new Date("2018-12-31"),
      chosenDate: new Date(),
    };
  },
  computed: {
    setDate(newDate) {
      this.chosenDate = newDate;
    },
  },
};
</script>