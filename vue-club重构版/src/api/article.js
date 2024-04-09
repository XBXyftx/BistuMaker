import request from "@/utils/request.js";

export const articleAddService = (articleData)=> {
    return request.post( "/article/add", articleData)
};
//查询全部
export const articleAllInfoService = ()=> {
    return request.get( "/article/articleAllInfo")
};

export const articleInfoService = (params)=> {
    return request.get("/article/articleInfo", { params: { id: params } });
};

export const articleTypeService = (params)=> {
    return request.get("/article/articleType", { params: { type: params } });
};

//修改文章
export const articleUpdateService = (articleData)=> {
    return request.put( "/article/update", articleData)
};

//删除文章
export const articleDeleteService = (deleteData)=> {
    return request.delete( "/article/delete", {params:{id:deleteData}})
};
//根据文章类型查询
export const articleTypeQueryService = (params)=> {
    return request.get("/article/articleType", { params: { type: params } });
};
//根据id查询文章
// export const articleIdQueryService = (params)=> {
//     return request.get("/article/articleInfo", { params: { id: params } });
// };

//查询博文
export const blogQueryService = (params)=> {
    return request.get("/article/blogAll");
};

//修改置顶
export const articleTopService = (params)=> {
    return request.get("/article/updateTop", { params: { id: params } });
};

//文章自增一个阅读量
export const articleAddVisitsService = (params)=> {
    return request.get("/article/addVisits", { params: { id: params } });
};
