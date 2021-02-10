<template>
  <nb-card
    ><nb-card-item>
      <nb-body><nb-text>Name of need: </nb-text> </nb-body>
      <nb-label>{{ need.type }}</nb-label>
    </nb-card-item>
    <nb-card-item>
      <nb-body>
        <nb-text>Notified: </nb-text>
      </nb-body>
      <nb-label>{{ need.notified }}</nb-label>
    </nb-card-item>
    <nb-button small :on-press="() => goToAddScheduleScreen(petId, need.id)"
      ><nb-text>Add new schedule</nb-text></nb-button
    >

    <nb-card
      v-for="(schedule, scheduleIndex) in need.schedules"
      :key="scheduleIndex"
      ><nb-card-item>
        <nb-body>
          <nb-text> At scheduled time: </nb-text>
        </nb-body>
        <nb-text>{{ schedule.time }}</nb-text>
      </nb-card-item>
      <nb-card-item>
        <nb-body><nb-text>Assigned to:</nb-text></nb-body>
        <nb-text>{{ schedule.assignedUser }}</nb-text>
      </nb-card-item>
      <nb-button
        small
        :on-press="() => deleteSchedule(petId, need.id, schedule.id)"
        ><nb-text>Delete Schedule</nb-text></nb-button
      >
    </nb-card>
    <nb-button block :on-press="() => deleteNeed(petId, need.id)"
      ><nb-text>Delete Need</nb-text>
    </nb-button>
    <nb-body />
  </nb-card>
</template>

<script>
export default {
  props: {
    need: {
      type: Object,
    },
    petId: {
      type: String,
      default: "",
    },
    goToAddScheduleScreen: {
      type: Function,
    },
    deleteNeed: {
      type: Function,
    },
    deleteSchedule: {
      type: Function,
    },
  },
};
</script>

<style>
</style>