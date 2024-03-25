import request from "@/utils/request.js";

export const articleAddService = (articleData)=> {
    // const params = new URLSearchParams()
    // for(let key in articleData){
    //     params.append(key,articleData[key]);
    // }
    return request.post( "/article/add", articleData)
};
//查询全部
export const articleAllInfoService = ()=> {
    // console.log("查询全部")
    return request.get( "/article/articleAllInfo")
};


export const articleInfoService = (params)=> {
    // const params = new URLSearchParams()
    // for(let key in Id){
    //     params.append(key,Id[key]);
    // }
    // return request.post( "/article/articleInfo", Id)
    // params.append("id",Id)
    // console.log("测试"+params)
    // return request.get( "/article/articleInfo?id="+params)
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

