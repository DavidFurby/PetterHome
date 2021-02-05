

<template>
  <view :style="inputStyle">
    <nb-picker
      mode="dropdown"
      placeholder="Select Time"
      placeholderStyle="{ color: '#bfc6ea' }"
      :selectedValue="selectedValue"
      :onValueChange="handleValueChange"
    >
      <nb-item v-for="time in times" :key="time" :label="time" :value="time" />
    </nb-picker>
  </view>
</template>

<script>
export default {
  props: {
    interval: {
      type: Number,
      default: 30,
    },
    onValueChange: {
      type: Function,
      required: true,
    },
  },
  data() {
    return {
      times: this.generateTimes(),
      selectedValue: "",
      inputStyle: {
        flex: 1,
        alignSelf: "stretch",
        paddingLeft: null,
        marginLeft: null,
        heigth: 50,
      },
    };
  },
  methods: {
    handleValueChange(time) {
      this.selectedValue = time;
      this.onValueChange(time);
    },
    generateTimes() {
      const times = []; 
      let tt = 0;

   
      for (let i = 0; tt < 24 * 60; i++) {
        const hh = Math.floor(tt / 60); 
        const mm = tt % 60; 
     
        times[i] =
          ("0" + (hh >= 12 ? hh % 24 : hh % 12)).slice(-2) +
          ":" +
          ("0" + mm).slice(-2);
        tt += this.interval; 
      }
      times.push("23:59");
      return times;
    },
  },
};
</script>
