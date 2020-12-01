<template>
  <nb-container>
    <nb-content padder>
      <nb-card>
        <nb-card-item>
          <nb-body>
            <nb-text
              >{{ hour }}:{{ min }} {{ animals.name }} needs to be
              walked</nb-text
            >
          </nb-body>
        </nb-card-item>
      </nb-card>
      <nb-button full info>
        <nb-text>View More </nb-text>
      </nb-button>
    </nb-content>
  </nb-container>
</template>
<script>
export default {
  props: {
    animals: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      hour: new Date().getHours(),
      min: new Date().getMinutes(),
    };
  },
};
</script>