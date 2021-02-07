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
      console.log(params);
      let petId = params.petId;
      let userId = params.userId;
      let username = params.username;
      return axiosInstance
        .post(`/user/sendInvite?userId=${userId}&petId=${petId}`, { username })
        .then((res) => {
          console.log(res.data.message);
          return res.data.message;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    acceptInvite(context, params) {
      let userId = params.userId;
      let inviteId = params.inviteId;
      return axiosInstance
        .post(`/user/acceptInvite?userId=${userId}&inviteId=${inviteId}`)
        .then((res) => {
          console.log(res, "res");
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    fetchInvites({ commit, state }, userId) {
      return axiosInstance
        .get(`/user/getAllInvites?userId=${userId}`)
        .then((res) => {
          const invites = res.data;
          commit("setInvites", invites);
          return state.invites;
        })
        .catch((err) => {
          console.log(err);
        });
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
