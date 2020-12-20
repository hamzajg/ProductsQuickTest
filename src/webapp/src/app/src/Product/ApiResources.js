import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
        this.baseUrl = "https://18afad2dff7a.ngrok.io"
    }

    async getAllProducts() {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/products/all`);
        if (result == undefined)
            return [];
        return result.items;
    }
    async getAllProductsByCategoryId(categoryId) {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/products/${categoryId}/all`);
        if (result == undefined)
            return [];
        return result.items;
    }
    async getProductById(id) {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/products/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createProduct(product) {
        let result = await this.httpClientHelper.post(`${this.baseUrl}/api/v1/products/create`, product);
        if (result == undefined)
            return {};
        return result;
    }
    async updateProduct(product) {
        let result = await this.httpClientHelper.patch(`${this.baseUrl}/api/v1/products/${product.id}/update`, product);
        if (result == undefined)
            return {};
        return result;
    }
    async deleteProduct(id) {
        let result = await this.httpClientHelper.delete(`${this.baseUrl}/api/v1/products/${id}/delete`);
        if (result == undefined)
            return {};
        return result;
    }
}
export default ApiResources;