import request from "@/utils/request.js";


export const selectImagesByImagesType = (id) => {
    return request.get( "/images/imageType", {params:{imageType:id}})
}

export const deleteImages = (id) => {
    console.log(id)
    return request.delete( "/images/delete", {params:{id:id}})
}

export const getPhoneAlbumAllImage = () => {
    return request.get( "/phoneAlbum/allImage")
}