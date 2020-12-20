import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
        this.baseUrl = "http://localhost:8080"
    }

    async getAllCustomers() {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/customers/all`);
        if (result == undefined)
            return [];
        return result.items;
    }
    async getCustomerById(id) {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/customers/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createCustomer(customer) {
        let result = await this.httpClientHelper.post(`${this.baseUrl}/api/v1/customers/create`, customer);
        if (result == undefined)
            return {};
        return result;
    }
    async updateCustomer(customer) {
        let result = await this.httpClientHelper.patch(`${this.baseUrl}/api/v1/customers/${customer.id}/update`, customer);
        if (result == undefined)
            return {};
        return result;
    }
    async deleteCustomer(id) {
        let result = await this.httpClientHelper.delete(`${this.baseUrl}/api/v1/customers/${id}/delete`);
        if (result == undefined)
            return {};
        return result;
    }
}
export default ApiResources;