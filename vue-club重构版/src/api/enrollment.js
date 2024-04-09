import request from "@/utils/request.js";
export const enrollmentAddService = (enrollmentData)=> {
  return request.post("/enrollment/add", enrollmentData);
}
export const enrollmentAllInfoService = ()=> {
  return request.get("/enrollment/enrollmentAllInfo");
}

export const enrollmentInfoService = (params)=> {
  return request.get("/enrollment/enrollmentInfo", { params: { id: params } });
}
