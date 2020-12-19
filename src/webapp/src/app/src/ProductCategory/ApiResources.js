import HttpClientHelper from "../Helpers";

class ApiResources {

    constructor() {
        this.httpClientHelper = new HttpClientHelper();
    }

    async getAllProductCategories() {
        let result = await this.httpClientHelper.get('http://localhost:8081/api/v1/product-categories/all');
        if (result == undefined)
            return [];
        return result.items;
    }
    async getProductCategoryById(id) {
        let result = await this.httpClientHelper.get(`http://localhost:8081/api/v1/product-categories/${id}`);
        if (result == undefined)
            return {};
        return result;
    }
    async createProductCategory(category) {
        let result = await this.httpClientHelper.post(`http://localhost:8081/api/v1/product-categories/create`, category);
        if (result == undefined)
            return {};
        return result;
    }
    async updateProductCategory(category) {
        let result = await this.httpClientHelper.patch(`http://localhost:8081/api/v1/product-categories/${category.id}/update`, category);
        if (result == undefined)
            return {};
        return result;
    }

}
export default ApiResources;