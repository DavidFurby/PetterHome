<template >
  <nb-header class="header">
    <nb-left>
      <nb-button :on-press="leftButtonFunction" transparent>
        <nb-icon v-if="leftButton" name="arrow-back" />
      </nb-button>
    </nb-left>
    <nb-title class="screenName">{{ screen }}</nb-title>
    <nb-right>
      <nb-button :on-press="rightButtonFunction" transparent>
        <nb-icon v-if="deleteFunction" name="trash" />
        <nb-icon v-if="updateFunction" name="refresh" />
      </nb-button>
    </nb-right>
  </nb-header>
</template>
<script>
export default {
  props: {
    screen: {
      type: String,
      default: "Header",
    },
    leftButton: {
      type: String,
      default: null,
    },
    leftButtonFunction: {
      type: Function,
      default: () => {},
    },
    rightButton: {
      type: String,
      default: null,
    },
    rightButtonFunction: {
      type: Function,
      default: () => {},
    },
  },
  computed: {
    deleteFunction() {
      if(this.rightButton == "delete") {
        return true; 

      }
    },
    updateFunction() {
      if (this.rightButton == "update") {
        return true;
      }
    },
  },
};
</script>
<style scoped>
.header {
  background-color: #edccaf;
  border-bottom-color: #edccae;
}
.screenName {
  font-family: ArchitectsDaughter;
  margin-left: 80;
  margin-top: 10;
  font-size: 25;
}
</style>