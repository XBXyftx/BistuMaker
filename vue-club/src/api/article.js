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

//修改文章
export const articleUpdateService = (articleData)=> {
    return request.put( "/article/update", articleData)
};

//删除文章
export const articleDeleteService = (deleteData)=> {
    return request.delete( "/article/delete", {params:{id:deleteData}})
};

