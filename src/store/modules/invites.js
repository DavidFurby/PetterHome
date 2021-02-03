import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    invites: [],
    invite: {},
  },
  getters: {},
  actions: {
    sendInvite(context, params) {
      let petId = params.petId;
      let userId = params.userId;
      let username = params.username;
      return axiosInstance.post(
        `/user/sendInvite?petId=${petId}&userId=${userId}`,
        username
      );
    },
    fetchInvites(context, params) {
      let userId = params.userId;
      return axiosInstance.get(`/user/getAllInvites?userId=${userId}`);
    },
  },
  mutations: {
    setInvites(state, invites) {
      Vue.set(state, "invites", invites);
    },
    setInvite(state, invite) {
      Vue.set(state, "invite", invite);
    },
  },
};
