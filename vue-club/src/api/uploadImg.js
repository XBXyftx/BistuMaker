import request from "@/utils/request.js";

export const uploadImg = (data) =>{
    return request({
        url: '/uploadImg',
        method: 'post',
        data
    })
}