import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
    }

    async getAllProducts() {
        let result = await this.httpClientHelper.get('http://localhost:8081/api/v1/products/all');
        if (result == undefined)
            return [];
        return result.items;
    }
    async getAllProductsByCategoryId(categoryId) {
        let result = await this.httpClientHelper.get(`http://localhost:8081/api/v1/products/${categoryId}/all`);
        if (result == undefined)
            return [];
        return result.items;
    }
    async getProductById(id) {
        let result = await this.httpClientHelper.get(`http://localhost:8081/api/v1/products/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createProduct(product) {
        let result = await this.httpClientHelper.post(`http://localhost:8081/api/v1/products/create`, product);
        if (result == undefined)
            return {};
        return result;
    }
    async updateProduct(product) {
        let result = await this.httpClientHelper.patch(`http://localhost:8081/api/v1/products/${product.id}/update`, product);
        if (result == undefined)
            return {};
        return result;
    }
    async deleteProduct(id) {
        let result = await this.httpClientHelper.delete(`http://localhost:8081/api/v1/products/${id}/delete`);
        if (result == undefined)
            return {};
        return result;
    }
}
export default ApiResources;