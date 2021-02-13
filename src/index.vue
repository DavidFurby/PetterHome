<template>
  <view class="container">
    <Navigation />
  </view>
</template>

<script>
import Navigation from "./Navigations";
import Vue from "vue-native-core";
import { VueNativeBase } from "native-base";
import { Platform } from "react-native";

import store from "./store";
import axios from "axios";
import AppHeader from "./components/AppHeader";
import InputWithError from "./components/InputWithError";
import PickerWithError from "./components/PickerWithError";
import AppTimePicker from "./components/AppTimePicker";
// registering all native-base components to the global scope of the Vue
Vue.use(VueNativeBase, axios);
Vue.prototype.$store = store;
Vue.component("AppHeader", AppHeader);
Vue.component("InputWithError", InputWithError);
Vue.component("PickerWithError", PickerWithError);
Vue.component("AppTimePicker", AppTimePicker);
export default {
  components: {
    Navigation,
  },
  data() {
    return {
      Platform,
    };
  },
  computed: {
    isAuthResolved() {
      return this.$store.state.auth.isAuthResolved;
    },
  },
  created() {
    this.$store.dispatch("auth/verifyUser").catch((err) => {
      console.log(err, "verify");
    });
  },
};
</script>

<style>
.container {
  flex: 1;
}
.spinner-container {
  display: flex;
  justify-content: center;
}
</style>
