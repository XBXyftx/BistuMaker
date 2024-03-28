<template>
  <div class="header">
    <div class="extra">
      <el-button type="success" @click="dialogVisible1 = true"> 添加相册</el-button>    <br>
      <br>
      <br>
    </div>
  </div>
  <el-table :data="imageAlbumPageList" :border="parentBorder" style="width: 100%" :default-expand-all="true" >


    <el-table-column type="expand">
      <template #default="props">
          <div class="header">
            <div class="extra">
              <el-button type="primary" @click="changeState(props.row)"> 添加相册图片</el-button>
              <el-button  style="margin-left: 65vw" type="danger" @click="changeStateDeleteAlbum(props.row)"> 删除相册(需要把所有照片先删除)</el-button>
            </div>
          </div>

          <el-table :data="props.row.List" :border="childBorder" style="margin: 10px" >
            <el-table-column label="Name" prop="imageName" />
<!--            <el-table-column label="State" prop="image_url" />-->
            <el-table-column label="图片" width="150px">
              <template #default="scope">
                <el-image
                    @click="onImageClick(baseURL + scope.row.imagesUrl)"
                    preview-teleported="true"
                    :src= "`${baseURL + scope.row.imagesUrl}`"
                    :preview-src-list="`${baseURL + scope.row.imagesUrl}`"
                ></el-image>
              </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime" />
            <el-table-column label="操作" width="100" prop="id">
              <template #default="{ row }">
<!--                <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>-->
                <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row,props.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>

      </template>
    </el-table-column>
<!--    <el-table-column label="时间" prop="date" />-->
    <el-table-column label="相册名" prop="phoneAlbumName" />
  </el-table>


  <el-pagination layout="prev, pager, next"
                 :total="pageSize"
                 :default-page-size="2"
                 :current-page="currentPage"
                 @current-change="handleCurrentChange"
  />














  <!-- 添加弹窗 -->
  <el-dialog v-model="dialogVisible" title="添加相册图片" width="66%">
    <template #footer>

      <el-form-item label="图片名字" prop="imagesInfo.imageName"  >
        <el-input v-model="imagesInfo.imageName" ></el-input>
      </el-form-item>

      <el-upload action=""
                 :http-request="handleRequest"
                 :show-file-list="false">
        <img
            style="width: 99%;height: auto"
             :src= "`${imageUrl}`"
             class="avatar"  alt=""/>
        <br>
        <div>
          <i class="el-icon-plus avatar-uploader-icon"></i>
          <br>
          <div class="el-upload__text" v-if="!imagesInfo.imageUrl">点击上传封面</div>

        </div>
        <template #tip>
          <div
               class="el-upload__tip">只能上传 jpg/png 文件，且不超过 500kb</div>
        </template>
      </el-upload>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
                      <el-button type="primary" @click="addImages()"> 确认 </el-button>
        </span>
    </template>
  </el-dialog>


  <!-- 添加分类弹窗 -->
  <el-dialog v-model="dialogVisible1" title="添加相册" width="66%">
    <template #footer>
      <el-form-item label="图片名字" prop="phoneAlbumName"  >
        <el-input v-model="phoneAlbumName" ></el-input>
      </el-form-item>

      <span class="dialog-footer">
            <el-button @click="dialogVisible1 = false">取消</el-button>
                      <el-button type="primary" @click="addPhoneAlbum()"> 确认 </el-button>
        </span>
    </template>
  </el-dialog>


  <!-- 添加分类弹窗 -->
  <el-dialog v-model="dialogVisible2" title="删除相册" width="66%">
    一定要先把相册的相片删除再删除相册!!!
      <span class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取消</el-button>
                      <el-button type="primary" @click="deletePhoneAlbum"> 确认 </el-button>
        </span>

  </el-dialog>

   <div class="image-container" v-if="onImage" @click="onImage=false">
      <el-image
          v-if="onImage"
          :src="onImageUrl"
          style="width: 60%; max-width: 100%; display: block; margin: auto;"
          class="img1"
      >
      </el-image>
    </div>
</template>

<style scoped>
.image-container {
  z-index: 9999999;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
<style>
/* 更改表头背景颜色 */
.el-table__header {
  background-color: #f0f0f0;
}

/* 更改表格行鼠标悬停时的背景颜色 */
.el-table__row:hover {
  background-color: #e0e0e0;
}
</style>

<script  setup>
import {getCurrentInstance, ref, toRaw,onMounted } from 'vue'
import {phoneAlbumAddService, phoneAlbumAllInfoService, phoneAlbumDeleteService} from "@/api/phoneAlbum.js"
import {Delete} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {deleteImages, selectImagesByImagesType} from '@/api/Images.js'
import {uploadImg} from "@/api/uploadImg.js";
//控制添加用户弹窗
let dialogVisible = ref(false)//添加图片
let dialogVisible1 = ref(false)//
let dialogVisible2 = ref(false)//删除相册弹窗
const parentBorder = ref(false)
const childBorder = ref(false)

const url =
    'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'
const list = [
  'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
]

//点击图片
const onImage = ref(false)
const onImageUrl = ref('')

const onImageClick = (url)=>{
  onImageUrl.value=url
  onImage.value=true
  // console.log(url)
}
const closeViewer = ()=>{
  onImage.value=false
}
const tableData = [
]
const PhoneAlbumNameList=ref([
])


const deleteCategory = (row,id) => {
  //提示用户  确认框
  phoneAlbumId.value=id.id-1
  console.log(id.id)
  console.log("id为"+row.id)
  ElMessageBox.confirm(
      '你确认要删除该分类信息吗?',
      '温馨提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(async () => {
        //调用接口
        // let result = await articleDeleteService(row.id)
        let result = await deleteImages(row.id)
        console.log(result)
        if (result.code===0){
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        }else{
          ElMessage({
            type: 'error',
            message: '删除失败',
          })
        }

        //刷新列表
        // await articleAllInfo()
        // await getImageAlbumAllInfo()
        // phoneAlbumId
        await updatePhoneAlbumImage()

      })
      .catch(() => {
        // ElMessage({
        //   type: 'info',
        //   message: '用户取消了删除',
        // })
      })
}


const changeState = (row) => {
  console.log(row.id)
  phoneAlbumId.value=row.id
  dialogVisible.value= true
}
const changeStateDeleteAlbum = (row)=>{
  console.log(row.id)
  phoneAlbumId.value=row.id
  dialogVisible2.value= true
}
const deletePhoneAlbum= async () => {
  console.log(phoneAlbumName.value)
  dialogVisible2.value=false
  const res = await phoneAlbumDeleteService(phoneAlbumId.value)
  imageAlbumAllList.value=[]
  await getImageAlbumAllInfo()
}

const phoneAlbumId = ref()
const addImages = async () =>{
  if (imagesInfo.value.imageName==='' && imagesInfo.value.image===''){
    ElMessage.error("标题不能为空")
    return
  }
  imagesInfo.value.imageType=phoneAlbumId.value
  console.log(imagesInfo.value)
  var { file } = imagesInfo.value.image;
  var formData = new FormData();
  // formData.append("token", data.params.token);
  formData.append("file", file);
  formData.append('imageType', imagesInfo.value.imageType)
  formData.append('imageName', imagesInfo.value.imageName)
  const res =await uploadImg(formData)
  dialogVisible.value=false
  imagesInfo.value.image=''
  imagesInfo.value.imageName=''
  imagesInfo.value.imageType=''
  imageAlbumAllList.value=[]
  await getImageAlbumAllInfo()
}

const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL
const imagesInfo = ref({
  imageType:'',
  imageName:'',
  image:'',
})
const imageUrl = ref()

//将图片预览为本地流形式
const handleRequest = async (params) => {
  imageUrl.value = window.URL.createObjectURL(params.file)
  imagesInfo.value.image=params
  imagesInfo.value.imageType=phoneAlbumId.value
  console.log(imageUrl)
}


const phoneAlbumName = ref('')
const addPhoneAlbum= async () => {
  console.log(phoneAlbumName.value)
  dialogVisible1.value=false
  if (phoneAlbumName.value===''){
    ElMessage.error("标题不能为空")
    return
  }
  const res = await phoneAlbumAddService(phoneAlbumName.value)
  imageAlbumAllList.value=[]
  await getImageAlbumAllInfo()
}
let imageAlbumAllList = ref([])
const getImageAlbumAllInfo = async () => {
  const res = await phoneAlbumAllInfoService()

  imageAlbumAllList.value=res.data
  // console.log(imageAlbumAllList.value)
  for (let i = 0; i < imageAlbumAllList.value.length; i++) {
    // console.log(imageAlbumAllList.value[i].id)
    imageAlbumAllList.value[i].List=await getImageAlbumList(imageAlbumAllList.value[i].id)
  }
  // PhoneAlbumNameList.value=res.data
  // console.log(toRaw(imageAlbumAllList.value))
  pageSize.value=imageAlbumAllList.value.length
  handleCurrentChange(currentPage.value)
}
getImageAlbumAllInfo()
const getImageAlbumList = async (type) => {
  const res = await selectImagesByImagesType(type)
  // console.log(res)
  return res.data
}
getImageAlbumList()

const updatePhoneAlbumImage = async () => {
  // console.log("更新前"+toRaw(imageAlbumAllList.value[phoneAlbumId.value].List))
  // // console.log(phoneAlbumId.value)
  // imageAlbumAllList.value[phoneAlbumId.value].List.length=0
  // console.log("更新清零"+imageAlbumAllList.value[phoneAlbumId.value].List)
  // imageAlbumAllList.value[phoneAlbumId.value].List = await getImageAlbumList(phoneAlbumId.value)
  // console.log("更新后"+toRaw(imageAlbumAllList.value[phoneAlbumId.value].List))

  imageAlbumAllList.value=[]
  await getImageAlbumAllInfo()


}
let queryInfo = ref({
  query: '', // 查询参数
  pageNum: 1, // 当前页码
  pageSize: 10 // 每页显示条数
});
const imageAlbumPageList = ref([])
const pageSize=ref()
const currentPage=ref(1)
const  handleCurrentChange = (i)=>{
  // imageAlbumAllList.value=null
  currentPage.value=i
  imageAlbumPageList.value=imageAlbumAllList.value.slice((i-1)*2,i*2)
}
handleCurrentChange(currentPage.value)
</script>
