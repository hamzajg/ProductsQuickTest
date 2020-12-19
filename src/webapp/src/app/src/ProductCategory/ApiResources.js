
class ApiResources {
    fakeData () {return[{ id: "11111", name: "test 1" }, { id: "222222", name: "test 2" }, { id: "33333", name: "test 3" }];}
    getAllProductCategories() {
        return this.fakeData() 
    }
    getProductCategoryById(id) {
        return this.fakeData().find(i => i.id == id)
    }

}
export default ApiResources;