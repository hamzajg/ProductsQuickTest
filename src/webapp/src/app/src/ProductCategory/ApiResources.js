import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
        this.baseUrl = "http://localhost:8080"
    }

    async getAllProductCategories() {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/product-categories/all`);
        if (result == undefined)
            return [];
        return result.items;
    }
    async getProductCategoryById(id) {
        let result = await this.httpClientHelper.get(`${this.baseUrl}/api/v1/product-categories/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createProductCategory(category) {
        let result = await this.httpClientHelper.post(`${this.baseUrl}/api/v1/product-categories/create`, category);
        if (result == undefined)
            return {};
        return result;
    }
    async updateProductCategory(category) {
        let result = await this.httpClientHelper.patch(`${this.baseUrl}/api/v1/product-categories/${category.id}/update`, category);
        if (result == undefined)
            return {};
        return result;
    }
    async deleteProductCategory(id) {
        let result = await this.httpClientHelper.delete(`${this.baseUrl}/api/v1/product-categories/${id}/delete`);
        if (result == undefined)
            return {};
        return result;
    }

}
export default ApiResources;