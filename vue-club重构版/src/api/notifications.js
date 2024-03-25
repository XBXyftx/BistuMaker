import request from "@/utils/request.js";


export const notificationsAllInfoService = ()=> {

    return request.get("/notifications/allInfo");
}

export const notificationsInfoService = (params)=> {
    return request.get("/notifications/info", { params: { id: params } });
}
export const notificationsAddService = (notificationsData)=> {
    console.log(notificationsData)
    return request.post("/notifications/add", notificationsData);
}

export const notificationsDeleteService = (params)=> {
    return request.delete("/notifications/delete", { params: { id: params } });
}

export const notificationsUpdateIsReadService = (id)=> {
    return request.get("/notifications/updateIsRead",{params: {
            id: id
        }});
}

export const notificationsGetIsReadService = ()=> {
    return request.get("/notifications/isRead");
}