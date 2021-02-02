import axios from "axios";
import { AsyncStorage } from "@react-native-async-storage/async-storage";

const BASE_URL =
  Platform.OS === "ios"
    ? "http://192.168.10.206:8080/api"
    : "http://192.168.10.206:8080/api";

const axiosInstance = axios.create({
  timeout: 3000,
  baseURL: BASE_URL,
});

axiosInstance.interceptors.request.use(
  async (config) => {
    const token = await AsyncStorage.getItem("petterhome-jwt");
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
