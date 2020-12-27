import Vue from "vue-native-core";
import axios from "axios";
import { Platform } from "react-native";

const BASE_URL =
  Platform.OS === "ios" ? "http://localhost:8080" : "http://10.0.2.2:8080";

export default {
  namespaced: true,

  state: {
    items: [],
  },
  getters: {},
  actions: {
    fetchPets({ commit, state }) {
      commit("setPet", {});
      return axios
        .get(`${BASE_URL}/pets`)
        .then(async (res) => {
          const pets = await res.data;
          commit(
            "setItems",
            { items: pets, resource: "pets" },
            { root: true }
          );
          return state.items;
        })
        .catch((error) => {
          console.log("error");
        });
    },
    fetchPetById({ commit, state }, petId) {
      commit("setPet", {});
      return axios.get(`${BASE_URL}/pets/${petId}`).then((res) => {
        const pet = res.data;
        commit("setPet", pet);
        return state.item;
      });
    },
  },
  mutations: {
    setpet(state, pet) {
      Vue.set(state, "item", pet);
    },
  },
};
