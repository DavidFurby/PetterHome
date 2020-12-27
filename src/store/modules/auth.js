import Axios from "axios";
import { Platform } from "react-native";

const BASE_URL =
  Platform.OS === "ios" ? "http://localhost:8080" : "http://10.0.2.2:8080";

export default {
  namespaced: true,

  state: {
    user: {},
    isAuth: false,
  },
  getters: {},
  actions: {
    login({ commit, state }, userData) {
      return Axios.post(`${BASE_URL}/users/login`, userData).then((res) => {
        const user = res.data;
        commit("setAuthUser", user);
        return state.user;
      });
    },
  },
  mutations: {
    setAuthUser(state, user) {
      return (state.user = user);
    },
  },
};
