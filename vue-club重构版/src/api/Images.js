import request from "@/utils/request.js";


export const selectImagesByImagesType = (id) => {
    return request.get( "/image/imageType", {params:{imageType:id}})
}

export const deleteImages = (id) => {
    return request.delete( "/image/delete", {params:{id:id}})
}

export const getPhoneAlbumAllImage = () => {
    return request.get( "/phoneAlbum/allImage")
}