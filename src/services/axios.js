import axios from "axios";
import { AsyncStorage } from "react-native";

const BASE_URL = __DEV__
  ? Platform.OS === "ios"
    ? "http://localhost:8080/api"
    : "http://10.0.2.2:8080/api"
  : "https://petterhome.herokuapp.com/api";

const axiosInstance = axios.create({
  timeout: 3000,
  baseURL: BASE_URL,
});

axiosInstance.interceptors.request.use(
  async (config) => {
    const token = await AsyncStorage.getItem("petterhome-jwt");
    console.log(token, "axios")
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

export default axiosInstance;
