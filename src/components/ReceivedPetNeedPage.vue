<template>
  <nb-content>
    <nb-card>
      <nb-card-item>
        <nb-text
          >Type of Need: <nb-text fixedLabel>{{ need.type }}</nb-text>
        </nb-text>
      </nb-card-item>
      <nb-content
        v-for="(schedule, scheduleIndex) in need.schedules"
        :key="scheduleIndex"
      >
        <nb-card-item>
          <nb-text>At: {{ schedule.time }} </nb-text>
        </nb-card-item>
        <nb-card-item>
          <nb-text>Assigned to: {{ schedule.assignedUser }}</nb-text>
        </nb-card-item>
      </nb-content>
    </nb-card>
  </nb-content>
</template>
<script>
export default {
  props: {
    need: {
      type: Object,
    },
  },
};
</script>