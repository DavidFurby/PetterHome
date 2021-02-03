import Axios from "axios";
import Vue from "vue-native-core";

import { AsyncStorage } from "react-native";
import jwtDecode from "jwt-decode";
import axiosInstance from "../../services/axios";
const BASE_URL = __DEV__
  ? Platform.OS === "ios"
    ? "http://localhost:8080/api"
    : "http://10.0.2.2:8080/api"
  : "https://petterhome.herokuapp.com/api";

const isTokenValid = (token) => {
  if (token) {
    const decodedToken = jwtDecode(token);
    return decodedToken && decodedToken.exp * 1000 > new Date().getTime();
  }
  return false;
};
export default {
  namespaced: true,

  state: {
    users: [],
    user: null,
    isAuth: false,
  },
  getters: {
    isAuth(state) {
      return !!state.user;
    },
  },
  actions: {
    login({ commit, state }, userData) {
      return axiosInstance.post(`/auth/login`, userData).then(async (res) => {
        const user = res.data;
        await AsyncStorage.setItem("petterhome-jwt", user.accessToken);
        const token = await AsyncStorage.getItem("petterhome-jwt");
        console.log(token, "auth");
        commit("setAuthUser", user);
        return state.user;
      });
    },
    register(context, userData) {
      return axiosInstance
        .post(`/auth/register`, userData)
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    updatePassword(context, userData) {
      return axiosInstance.put(`/user/recoverPassword`, userData);
    },
    logout({ commit }) {
      return new Promise((resolve) => {
        AsyncStorage.removeItem("petterhome-jwt");
        commit("setAuthUser", null);
        resolve(true);
      });
    },
    fetchUsers({ commit, state }) {
      return axiosInstance
        .get(`/user/getAllUsers`)
        .then((res) => {
          const users = res.data;
          commit("setUsers", users);
          return state.users;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchCurrentUser({ commit, state }) {
      return axiosInstance
        .get(`/user/getCurrentUser`)
        .then((res) => {
          const user = res.data;
          AsyncStorage.setItem("petterhome-jwt", user.token);
          commit("setAuthUser", user);
          return state.user;
        })
        .catch((error) => error);
    },
    async verifyUser({ dispatch }) {
      const jwt = await AsyncStorage.getItem("petterhome-jwt");
      if (jwt && isTokenValid(jwt)) {
        return user
          ? Promise.resolve(jwt)
          : Promise.reject("cannot fetch user");
      } else {
        return Promise.reject("Token is not valid");
      }
    },
  },
  mutations: {
    setUsers(state, users) {
      Vue.set(state, "users", users);
    },
    setAuthUser(state, user) {
      return (state.user = user);
    },
    resolveAuth(state) {
      state.isAuthResolved = true;
    },
  },
};
