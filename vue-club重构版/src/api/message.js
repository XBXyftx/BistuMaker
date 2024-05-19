import request from "@/utils/request.js";

export const messageAllInfoService = (params)=> {
    return request.get("/messages/allInfo");
}


export const messageDeleteService = (params)=> {
    return request.delete("/messages/delete", { params: { id: params } });
}

export const messageAddService = (messageData)=> {
    return request.post("/messages/add", messageData);
}