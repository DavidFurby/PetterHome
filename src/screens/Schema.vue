<template>
  <nb-container>
        <nb-header><nb-title>{{pet.name}} Schema</nb-title></nb-header>

    <nb-content>
      <nb-text>Calender</nb-text>
    </nb-content>
  </nb-container>
</template>

<script>

export default {
  props: {
    pet: {
      type: Array,
      default: () => []
    }
  }
};
</script>

<style>
</style>