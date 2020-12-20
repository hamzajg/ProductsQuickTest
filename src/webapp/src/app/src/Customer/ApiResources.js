import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
    }

    async getAllCustomers() {
        let result = await this.httpClientHelper.get('http://localhost:8082/api/v1/customers/all');
        if (result == undefined)
            return [];
        return result.items;
    }
    async getCustomerById(id) {
        let result = await this.httpClientHelper.get(`http://localhost:8082/api/v1/customers/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createCustomer(customer) {
        let result = await this.httpClientHelper.post(`http://localhost:8082/api/v1/customers/create`, customer);
        if (result == undefined)
            return {};
        return result;
    }
    async updateCustomer(customer) {
        let result = await this.httpClientHelper.patch(`http://localhost:8082/api/v1/customers/${customer.id}/update`, customer);
        if (result == undefined)
            return {};
        return result;
    }
    async deleteCustomer(id) {
        let result = await this.httpClientHelper.delete(`http://localhost:8082/api/v1/customers/${id}/delete`);
        if (result == undefined)
            return {};
        return result;
    }
}
export default ApiResources;