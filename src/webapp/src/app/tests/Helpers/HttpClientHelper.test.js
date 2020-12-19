import { HttpClientHelper } from "../../src/Helpers/";
import axios from "axios";
jest.mock('axios', () => {
    return {
        create: jest.fn(),
        post: jest.fn(() => Promise.resolve({ data: {} }))
    }
});
process.env['NODE_TLS_REJECT_UNAUTHORIZED'] = 0

test('Helper Can Post product category', async () => {
    const helper = new HttpClientHelper();
    helper.instance = axios;
    const result = await helper.post('http://localhost:8081/api/v1/product-categories/create', { name: "Test From Jest"})
    console.log(result)
    expect(result).not.toBe(undefined);
})
// test('Can Post', () => {
//     axios.post('http://localhost:5000/api/dashboard', { retailerId: "f05c16af-3fdd-4bee-8fef-4d987383bfce", period: 0 }).then((response) => {
//         console.log(response.data);
//         //resolve(response.data);
//     })
// })