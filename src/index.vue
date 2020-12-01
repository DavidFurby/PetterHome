<template>
  <view class="container">
    <Navigation />
  </view>
</template>

<script>
import Navigation from "./Navigations";
import Vue from "vue-native-core";
import { VueNativeBase } from "native-base";

// registering all native-base components to the global scope of the Vue
Vue.use(VueNativeBase);

export default {
  components: {
    Navigation,
  },
};
</script>

<style scoped>
.container {
  flex: 1;
}
</style>
