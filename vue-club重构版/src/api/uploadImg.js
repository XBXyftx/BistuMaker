import request from "@/utils/request.js";

export const uploadImg = (data) =>{
    return request({
        url: '/upload/img',
        method: 'post',
        data
    })
}