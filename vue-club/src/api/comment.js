import request from "@/utils/request.js";

export const commentAddService = (commentData)=> {
  console.log(commentData)
  return request.post("/comment/add", commentData);
}

export const commentDeleteService = (deleteData)=> {
  return request.delete( "/comment/delete", {params:{id:deleteData}})
}

export const commentInfoService = (params)=> {
  return request.get("/comment/commentByArticleId", { params: { id: params } });
}