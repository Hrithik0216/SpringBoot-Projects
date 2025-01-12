import axios from "axios"
import { CART_URL, MEMBER_SERVICE_SERVER_URL, PRODUCTS_URL } from "./static/Constants";

export const URLS = {
    signIn: `${MEMBER_SERVICE_SERVER_URL}/signin`,
    signUp: `${MEMBER_SERVICE_SERVER_URL}/signup`,
    getAllProducts : `${PRODUCTS_URL}/getAllProductsById`,
    addToCart:`${CART_URL}/addToCart`
};

export const makeApi = (config, doCancelRequest, hasCredentials = true) => {
    const { CancelToken } = axios;
    config.method = config.method || "GET";
    config.headers = {
      ...config.headers,
      Accept: "application/json",
      "Content-Type": "application/json"
    };
    // console.log("config.headers:",config.headers)
    if (hasCredentials) {
      config.withCredentials = true;
    }
  
    if (doCancelRequest !== undefined) {
      config.cancelToken = new CancelToken(function executor(cancelHttpRequest) {
        doCancelRequest(cancelHttpRequest);
      });
    }
  
    return axios(config)
      .then(response => {
        return response;
      })
      .catch(response => {
        return response;
      });
  };
  
  export default makeApi;