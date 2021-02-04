<template>
  <nb-header>
    <nb-left>
      <nb-button :on-press="leftButtonFunction" transparent>
        <nb-text>{{ leftButton }}</nb-text>
      </nb-button>
    </nb-left>
    <nb-body>
      <nb-title>{{ screen }}</nb-title>
    </nb-body>
    <nb-right>
      <nb-button :on-press="rightButtonFunction" transparent>
        <nb-text>{{ rightButton }}</nb-text>
      </nb-button>
    </nb-right>
  </nb-header>
</template>
<script>
export default {
  data() {
    return {};
  },
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
};
</script>