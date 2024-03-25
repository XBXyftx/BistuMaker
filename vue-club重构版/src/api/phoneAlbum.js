import request from "@/utils/request.js";


export const phoneAlbumAddService = (params)=> {
  return request.get("/phoneAlbum/add", { params: { phoneAlbum: params } });
}

export const phoneAlbumDeleteService = (phoneAlbumData)=> {
  return request.delete("/phoneAlbum/delete", {params:{id:phoneAlbumData}})
}

export const phoneAlbumAllInfoService = (params)=> {
  return request.get("/phoneAlbum/allInfo", );
}

export const phoneAlbumInfoService = (params)=> {
  return request.get("/phoneAlbum/imageType", { params: { id: params } });
}

