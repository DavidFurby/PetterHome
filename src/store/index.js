import Vuex from "vuex";
import Vue from "vue-native-core";
import axios from "axios";
import { Platform } from "react-native";

Vue.use(Vuex);

const BASE_URL =
  Platform.OS === "ios"
    ? "http://localhost:8080"
    : "http://10.0.2.2:3001/api/v1";

export default new Vuex.Store({
  state: {
    pets: [],
  },
  getters: {},
  actions: {
    fetchPets({ commit, state }) {
      axios
        .get(`http://localhost:8080/pets`)
        .then((res) => {
          const pets = res.data;
          console.log(pets)
          commit("setPets", pets);
          return state.pets;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mutations: {
    setPets(state, pets) {
      Vue.set(state, "pets", pets);
    },
  },
});
