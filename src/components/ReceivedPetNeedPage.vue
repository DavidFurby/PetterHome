<template>
  <nb-content>
    <nb-card
      ><nb-card-item
        ><nb-body
          ><nb-text
            >Type of Need: <nb-text fixedLabel>{{ need.type }}</nb-text>
          </nb-text>
          <nb-text> Notified: {{ need.notified }}</nb-text>
        </nb-body>
      </nb-card-item>
      <nb-card
        v-for="(schedule, scheduleIndex) in need.schedules"
        :key="scheduleIndex"
        ><nb-card-item
          ><nb-body
            ><nb-text
              >{{ schedule.time }} assigned to
              {{ schedule.assignedUser }}
            </nb-text>
          </nb-body>
        </nb-card-item>
      </nb-card>
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