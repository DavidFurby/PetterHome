import { AsyncStorage } from "react-native";
import jwtDecode from "jwt-decode";
import axiosInstance from "../../services/axios";

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
      return axiosInstance.post(`/auth/login`, userData).then(async (res) => {
        const user = res.data;
        await AsyncStorage.setItem("petterhome-jwt", user.accessToken);
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
    fetchCurrentUser({ commit, state }, userId) {
      console.log(userId);
      return axiosInstance
        .get(`/user/getCurrentUser?userId=${userId}`)
        .then((res) => {
          const user = res.data;
          commit("setAuthUser", user);
          return state.user;
        })
        .catch((error) => error);
    },
    async verifyUser({ dispatch, commit }) {
      const jwt = await AsyncStorage.getItem("petterhome-jwt");
      if (jwt && isTokenValid(jwt)) {
        const user = await dispatch("fetchCurrentUser");

        return user
          ? Promise.resolve(jwt)
          : Promise.reject("Cannot fetch user");
      } else {
        commit("resolveAuth");
        return Promise.reject("Token is not valid");
      }
    },
    changePassword(context, params) {
      const userId = params.userId;
      const passwordRequest = params.passwordForm;
      return axiosInstance.put(
        `/user/changePassword?userId=${userId}`,
        passwordRequest
      );
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
