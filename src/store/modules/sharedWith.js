import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    sharedWithUsers: [],
    sharedWithUser: {},
  },
  getters: {},
  actions: {
    fetchSharedWithUsers({ commit, state }, params) {
      const userId = params.userId;
      const petId = params.petId;
      console.log(params, "params")
      return axiosInstance
        .get(`/user/getSharedWithUsers?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const sharedWithUsers = res.data;
          console.log(sharedWithUsers);
          commit("setSharedWithUsers", sharedWithUsers);
          return state.sharedWithUsers;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mutations: {
    setSharedWithUsers(state, sharedWithUsers) {
      Vue.set(state, "sharedWithUsers", sharedWithUsers);
    },
    setSharedWithUser(state, sharedWithUser) {
      Vue.set(state, "sharedWithUser", sharedWithUser);
    },
  },
};
