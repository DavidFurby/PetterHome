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
    sendInvite({ rootState }, params) {
      console.log(params);
      let petId = params.petId;
      let userId = rootState.auth.user.id;
      let username = params.username;
      return axiosInstance
        .post(`/user/sendInvite?userId=${userId}&petId=${petId}`, { username })
        .then((res) => {
          return res.data.message;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    acceptInvite({ rootState }, params) {
      let userId = rootState.auth.user.id;
      let inviteId = params.inviteId;
      return axiosInstance
        .post(`/user/acceptInvite?userId=${userId}&inviteId=${inviteId}`)
        .then((res) => {
          const invite = res.data.invite;
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    declineInvite({ rootState }, inviteId) {
      let userId = rootState.auth.user.id;
      return axiosInstance
        .delete(`/user/deleteInvite?userId=${userId}&inviteId=${inviteId}`)
        .then((res) => {
          const inviteId = res.data.objectId;
          commit("deleteInvite", inviteId);
          return res.data.msg;
        })
        .catch((err) => {
          console.log(err, "deleteInvite");
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
          console.log(err, "invites");
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
    deleteInvite(state, inviteId) {
      let newInviteArr = [];
      state.items.filter((filterInvite) => {
        if (filterInvite.id != inviteId) {
          newInviteArr.push(filterInvite);
        }
      });
      state.items = newInviteArr;
    },
  },
};
