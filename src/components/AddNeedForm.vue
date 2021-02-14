<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <nb-card>
          <InputWithError
            :error="$v.needForm.type.$dirty && !$v.needForm.type.required"
            msg="Must select a type"
          >
            <nb-card-item stackedLabel>
              <nb-text :style="{ width: 200 }">Name of the pets need</nb-text>
              <nb-input
                placeholder="type"
                v-model="needForm.type"
                :on-blur="() => $v.needForm.type.$touch()"
            /></nb-card-item>
          </InputWithError>
        </nb-card>
      </nb-form>

      <nb-form>
        <nb-card>
          <nb-card-item>
            <nb-body>
              <nb-text>Create schedule</nb-text>
            </nb-body>
            <nb-item stackedLabel>
              <nb-text>Time of day</nb-text>
              <AppTimePicker :onValueChange="(time) => setTime(time, 'time')" />
            </nb-item>
          </nb-card-item>
          <nb-card-item>
            <nb-body>
              <nb-text>Assign schedule to user</nb-text>
            </nb-body>
            <nb-item stackedLabel>
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
          </nb-card-item>
        </nb-card>
      </nb-form>
      <nb-button block success :on-press="addPetNeed">
        <nb-text>Add Need </nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>
<script>
import React from "react";
import { Picker, Icon } from "native-base";
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item },
  data() {
    return {
      needForm: {
        type: null,
        notified: false,
      },
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
    username: {
      type: String,
    },
    petId: {
      type: String,
    },
    navigation: {
      type: Object,
    },
    goBack: {
      type: Function,
    },
  },
  validations: {
    needForm: {
      type: {
        required,
      },
    },
    scheduleForm: {
      time: {
        required,
      },
    },
  },

  methods: {
    ontypeChange(typeValue) {
      this.needForm.type = typeValue;
    },
    onUserChange(userValue) {
      this.scheduleForm.assignedUser = userValue;
    },
    getIosIcon() {
      return <Icon name="ios-arrow-down-outline" />;
    },
    setMedication() {
      this.needForm.medication = !this.needForm.medication;
    },
    addPetNeed() {
      this.$v.needForm.$touch();
      this.$v.scheduleForm.$touch();
      if (!this.$v.needForm.$invalid || !this.$v.scheduleForm.$invalid) {
        let needForm = this.needForm;
        let scheduleForm = this.scheduleForm;
        let need = {};
        let schedule = [];
        schedule.push(scheduleForm);
        need.type = needForm.type;
        need.notified = needForm.notified;
        need.schedule = schedule;
        let petId = this.petId;
        let params = { need, petId };
        this.$store
          .dispatch("pets/addNeedToPet", params)
          .then((res) => {
            if (res == "Pets need added successfully!") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "success",
                duration: 3000,
              });
              this.goBack();
            } else if (
              res == "Error: A name for the pets need must be selected"
            ) {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            } else if (res == "Error: No schedule has been made") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            }
          })
          .catch((err) => {
            console.log(err);
            Toast.show({
              text: "Something went wrong",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      } else {
        return alert("something went wrong");
      }
    },
    setTime(time, label) {
      this.scheduleForm[label] = time;
    },
    setNotification() {
      this.needForm.notified = !this.needForm.notified;
    },
  },
  computed: {
    selectedUser() {
      return this.scheduleForm.assignedUser;
    },
  },
};
</script>