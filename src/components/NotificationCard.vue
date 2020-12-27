<template>
  <nb-content padder>
    <nb-card>
      <nb-card-item>
        <nb-body>
          <nb-text>{{ time }} {{ pet.name }} needs to be walked</nb-text>
        </nb-body>
      </nb-card-item>
    </nb-card>
    <nb-button full info>
      <nb-text>View More </nb-text>
    </nb-button>
  </nb-content>
</template>
<script>
export default {
  props: {
    pet: Array,
    default: () => [],
    time: Object,
    default: () => [],
  },
};
</script>