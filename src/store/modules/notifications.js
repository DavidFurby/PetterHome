import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    notifications: [],
    notification: {},
  },
  getters: {},
  actions: {
    fetchNotifications({ commit, state }, userId) {
      return axiosInstance
        .get(`/user/getPetNotifications?userId=${userId}`)
        .then((res) => {
          const notifications = res.data;
          commit("setNotifications", notifications);
          return state.notifications;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    createNotifications({ rootState }, params) {
      let petId = params.petId;
      let needId = params.needId;
      let scheduleId = params.scheduleId;
      
      let userId = rootState.auth.user.id;
      return axiosInstance
        .post(`/user/createNotifications?userId=${userId}`, petData)
        .then((res) => {
          console.log(res, "success");
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
  },
  mutations: {
    setNotifications(state, notifications) {
      Vue.set(state, "notifications", notifications);
    },
    setNotification(state, notification) {
      Vue.set(state, "notification", notification);
    },
  },
};
