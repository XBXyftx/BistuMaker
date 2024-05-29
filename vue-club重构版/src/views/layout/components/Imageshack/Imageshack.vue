<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>图床管理</span>
      </div>
    </template>
    <el-button type="primary" @click="changeState()"> 添加相册图片</el-button>
    <el-table :data="imageListPage" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="标题" prop="imageName"></el-table-column>
      <el-table-column label="修改时间" prop="createTime"></el-table-column>

      <el-table-column label="图片" width="150px">
        <template #default="scope">
          <el-image
              preview-teleported="true"
              :src= "`${ scope.row.imagesUrl}`"
              :preview-src-list="`${ scope.row.imagesUrl}`"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="链接" prop="imagesUrl"></el-table-column>


      <el-table-column label="操作" width="100" prop="id">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="copy(row.imagesUrl)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>

    <!-- 添加弹窗 -->
    <el-dialog v-model="dialogVisible" title="添加相册图片" width="66%">
      <template #footer>

        <el-form-item label="图片名字" prop="imagesInfo.imageName"  >
          <el-input v-model="imagesInfo.imageName" ></el-input>
        </el-form-item>

        <el-upload action=""
                   :http-request="handleRequest"
                   :show-file-list="false"
                   accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP"
        >
          <img
              style="width: 99%;height: auto"
              :src= "`${imageUrl}`"
              class="avatar"  alt=""/>
          <br>
          <div>
            <i class="el-icon-plus avatar-uploader-icon"></i>
            <br>
            <div class="el-upload__text" v-if="!imagesInfo.imageUrl">点击上传图片</div>

          </div>
          <template #tip>
            <div
                class="el-upload__tip">只能上传 jpg/png 文件，且不超过 5000kb</div>
          </template>
        </el-upload>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
                      <el-button type="primary" @click="addImages()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>


  </el-card>





  <!-- 添加弹窗 -->
  <el-dialog v-model="dialogVisible" title="添加相册图片" width="66%">
    <template #footer>

      <el-form-item label="图片名字" prop="imagesInfo.imageName"  >
        <el-input v-model="imagesInfo.imageName" ></el-input>
      </el-form-item>

      <el-upload action=""
                 :http-request="handleRequest"
                 :show-file-list="false"
                 accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP"
      >
        <img
            style="width: 99%;height: auto"
            :src= "`${imageUrl}`"
            class="avatar"  alt=""/>
        <br>
        <div>
          <i class="el-icon-plus avatar-uploader-icon"></i>
          <br>
          <div class="el-upload__text" v-if="!imagesInfo.imageUrl">点击上传图片</div>

        </div>
        <template #tip>
          <div
              class="el-upload__tip">只能上传 jpg/png 文件，且不超过 5000kb</div>
        </template>
      </el-upload>
      <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
                      <el-button type="primary" @click="addImages()"> 确认 </el-button>
        </span>
    </template>
  </el-dialog>


  <el-pagination layout="prev, pager, next"
                 :total="imageList.length"
                 @current-change="handleCurrentChange"
  />




</template>

<script setup>
//控制添加用户弹窗
import {ElMessage, ElMessageBox} from "element-plus";
import {uploadImg} from "@/api/uploadImg.js";
import {getCurrentInstance, ref} from "vue";
import {Delete, Edit} from "@element-plus/icons-vue";
import {deleteImages, selectImagesByImagesType} from '@/api/Images.js'


const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL
const imagesInfo = ref({
  imageType:'图床',
  imageName:'',
  image:'',
})
const imageType=ref("图床")
let dialogVisible = ref(false)//添加图片
let imageList=ref([])


const getImageAllInfo = async () => {
  const res= await selectImagesByImagesType(imageType.value)
  imageList.value=res.data

  //修改imageList里面的imageUrl
  for (let i = 0; i < imageList.value.length; i++) {
    imageList.value[i].imagesUrl=baseURL+imageList.value[i].imagesUrl
  }

  if (imageList.value.length <= 10){
    imageListPage.value=imageList.value
  }else{
    handleCurrentChange(page.value)
  }
  console.log(imageList.value)

}
getImageAllInfo()
const addImages = async () =>{
  if (imagesInfo.value.imageName==='' && imagesInfo.value.image===''){
    ElMessage.error("标题不能为空")
    return
  }
  imagesInfo.value.imageType=imageType.value

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
  // imageAlbumAllList.value=[]
  await getImageAllInfo()
  imageUrl.value=null
  imagesInfo.value.imageName=null

  copy(baseURL+res.data)

}

const imageUrl = ref()
//将图片预览为本地流形式
const handleRequest = async (params) => {
  imageUrl.value = window.URL.createObjectURL(params.file)
  imagesInfo.value.image=params

}

const changeState = () => {
  dialogVisible.value= true
}

const  copy = (row) => {

  //点击后将1234复制到电脑的剪贴板
  //这里正式上线有安全问题所以取消注释掉
  // try {
  //   navigator.clipboard.writeText(row);
  // ElMessage.success("复制成功")
  // } catch (err) {
  //   console.error(err)
  //   ElMessage.error("复制失败")
  // }

}

const deleteCategory = (row) => {

  console.log(row)
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

    await getImageAllInfo()
        handleCurrentChange(page.value)
      })
      .catch(() => {
      })
}
let imageListPage=ref([])
let page=ref(1)
const  handleCurrentChange = (i)=>{
  page.value=i
  imageListPage.value=imageList.value.slice((i-1)*10,i*10)

}

</script>

