import axios from "axios";

export default class HttpClientHelper {
    constructor() {
        const agent = undefined//new https.Agent({ rejectUnauthorized: false });

        this.instance = axios.create({
            httpsAgent: agent,
            timeout: 1000,
        })
    }
    get(endpoint) {
        return new Promise((resolve, reject) => {
            this.instance.get(endpoint)
                .then((response) => {
                    resolve(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    resolve();
                });
        })
    }
    post(endpoint, payload) {
        const headers = {
            'Content-Type': 'application/json',
        }
        return new Promise((resolve, reject) => {
            this.instance.post(endpoint, payload, headers)
                .then((response) => {
                    console.log(response.data);
                    resolve(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    resolve();
                });
        })
    }

    patch(endpoint, payload) {
        const headers = {
            'Content-Type': 'application/json',
        }
        return new Promise((resolve, reject) => {
            this.instance.patch(endpoint, payload, headers)
                .then((response) => {
                    console.log(response.data);
                    resolve(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    resolve();
                });
        })
    }
}