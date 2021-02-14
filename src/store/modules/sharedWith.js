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
    fetchSharedWithUsers({ rootState, commit, state }, params) {
      const userId = rootState.auth.user.id;
      const petId = params.petId;
      return axiosInstance
        .get(`/user/getSharedWithUsers?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const sharedWithUsers = res.data;
          commit("setSharedWithUsers", sharedWithUsers);
          return state.sharedWithUsers;
        })
        .catch((err) => {
          console.log(err, "sharedWith");
        });
    },
    removeReceivedUser({ rootState, commit, state }, params) {
      const userId = rootState.auth.user.id;
      const petId = params.petId;
      const receiverId = params.receiverId;
      return axiosInstance
        .delete(
          `/user/removeReceivedUser?receiverId=${receiverId}&petId=${petId}&userId=${userId}`
        )
        .then((res) => {
          console.log(res);
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
