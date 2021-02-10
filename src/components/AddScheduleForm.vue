<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <nb-text>Create schedule</nb-text>
        <nb-item stackedLabel>
          <nb-text>Time of day</nb-text>
          <AppTimePicker :onValueChange="(time) => setTime(time, 'time')" />
        </nb-item>
        <nb-item stackedLabel>
          <nb-text>Assign schedule to user</nb-text>
          <nb-picker
            note
            mode="dropdown"
            :style="{ width: 120 }"
            :selectedValue="selectedUser"
            :onValueChange="onUserChange"
          >
            <item
              v-for="(availableUser, availableUserIndex) in availableUsers"
              :key="availableUserIndex"
              :label="availableUser"
              :value="availableUser"
            />
          </nb-picker>
        </nb-item>
        <!-- <nb-list-item>
          <nb-text>Medication ?</nb-text>
          <nb-body> </nb-body>
          <nb-checkbox
            :on-press="setMedication"
            :checked="needForm.medication"
          />
        </nb-list-item>
        <Medication
          v-if="ifMedication"
          :medicationName="$v.needForm.medicationName"
          :dosage="$v.needForm.dosage"
        />
        <DogForm v-if="ifDog" />
        <nb-button block :on-press="addPet">
          <nb-text>Add Pet</nb-text> 
        </nb-button>-->
      </nb-form>
      <nb-button block :on-press="addPetSchedule">
        <nb-text>Add Schedule </nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>
<script>
import { Picker } from "native-base";
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item },
  data() {
    return {
      scheduleForm: {
        time: "00:00",
        assignedUser: this.firstAvailableUser,
      },
    };
  },
  props: {
    availableUsers: {
      type: Array,
    },
    firstAvailableUser: {
      type: String,
    },
    petId: {
      type: String,
    },
    needId: {
      type: String,
    },
    goBack: {
      type: Function,
    },
  },
  validations: {
    scheduleForm: {
      time: {
        required,
      },
    },
  },
  methods: {
    onUserChange(userValue) {
      this.scheduleForm.assignedUser = userValue;
    },
    addPetSchedule() {
      this.$v.scheduleForm.$touch();
      if (!this.$v.scheduleForm.$invalid) {
        let schedule = this.scheduleForm;
        let petId = this.petId;
        let needId = this.needId;
        let params = { schedule, petId, needId };
        this.$store
          .dispatch("pets/addScheduleToNeed", params)
          .then((res) => {
            if (res == "Schedule added to pets need") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "success",
                duration: 3000,
              });
              this.goBack();
            } else if (res == "Error: time of day must be selected") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            } else if (
              res == "Error: A user must be assigned to the schedule"
            ) {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            }
          })
          .catch(() => {
            Toast.show({
              text: "Something went wrong",
              buttonText: "ok",
              type: "danger",
              duration: 3000,
            });
          });
      } else {
        Toast.show({
          text: "Information is invalid",
          buttonText: "ok",
          type: "danger",
          duration: 3000,
        });
      }
    },
    setTime(time, label) {
      this.scheduleForm[label] = time;
    },
  },
  computed: {
    selectedUser() {
      return this.scheduleForm.assignedUser;
    },
  },
};
</script>