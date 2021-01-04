import Axios from "axios";
import { Platform } from "react-native";
import { AsyncStorage } from "@react-native-community/async-storage";
import jwtDecode from "jwt-decode";
import axiosInstance from "../../services/axios";
const BASE_URL =
  Platform.OS === "ios" ? "http://localhost:8080" : "http://10.0.2.2:8080";
const isTokenValid = (token) => {
  if (token) {
    const decodedToken = jwtDecode();
    return decodedToken && decodedToken.exp * 1000 > new Date().getTime();
  }
  return false;
};
export default {
  namespaced: true,

  state: {
    user: null,
    isAuthResolved: false,
  },
  getters: {
    isAuth(state) {
      return !!state.user;
    },
  },
  actions: {
    login({ commit, state }, userData) {
      return Axios.post(`${BASE_URL}/users/login`, userData).then((res) => {
        const user = res.data;
        AsyncStorage.setItem("petterhome-jwt", user.token);
        commit("setAuthUser", user);
        return state.user;
      });
    },
    register(context, userData) {
      return Axios.post(`${BASE_URL}/user/register`, userData);
    },
    updatePassword(context, userData) {
      return Axios.post(`${BASE_URL}/user/updatePassword`, userData);
    },
    logout({ commit }) {
      return new Promise((resolve) => {
        AsyncStorage.removeItem("petterhome-jwt");
        commit("setAuthUser", null);
        resolve(true);
      });
    },
    fetchCurrentUser({ commit, state }) {
      return axiosInstance
        .get(`${BASE_URL}/users/current`)
        .then((res) => {
          const user = res.data;
          AsyncStorage.setItem("petterhome-jwt", user.token);
          commit("setAuthUser", user);
          return state.user;
        })
        .catch(() => undefined);
    },
    async verifyUser({ dispatch, commit }) {
      const token = await AsyncStorage.getItem("petterhome-jwt");
      alert(token);
      if (token && isTokenValid(token)) {
        const user = await dispatch("fetchCurrentUser");
        commit("resolveAuth");
        return user
          ? Promise.resolve(token)
          : Promise.reject("Cannot fetch user");
      } else {
        commit("resolveAuth");
        return Promise.reject("Token is not valid");
      }
    },
  },
  mutations: {
    setAuthUser(state, user) {
      return (state.user = user);
    },
    resolveAuth(state) {
      state.isAuthResolved = true;
    },
  },
};
