<template>
  <div class="header">
    <div class="extra">
      <el-button type="success" @click="dialogVisible1 = true"> 添加相册</el-button>
    </div>
  </div>
  <el-table :data="imageAlbumAllList" :border="parentBorder" style="width: 100%" :default-expand-all="true" >


    <el-table-column type="expand">
      <template #default="props">
          <div class="header">
            <div class="extra">
              <el-button type="primary" @click="changeState(props.row)"> 添加相册图片</el-button>
            </div>
          </div>

          <el-table :data="props.row.List" :border="childBorder" style="margin: 10px" >
            <el-table-column label="Name" prop="imageName" />
<!--            <el-table-column label="State" prop="image_url" />-->
            <el-table-column label="图片" width="150px">
              <template #default="scope">

                <el-image
                    :src= "`${baseURL + scope.row.imagesUrl}`"
                ></el-image>
              </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime" />

            <el-table-column label="操作" width="100" prop="id">
              <template #default="{ row }">
<!--                <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>-->
                <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
              </template>
            </el-table-column>


          </el-table>




      </template>
    </el-table-column>
<!--    <el-table-column label="时间" prop="date" />-->
    <el-table-column label="相册名" prop="phoneAlbumName" />




  </el-table>

  <!-- 添加弹窗 -->
  <el-dialog v-model="dialogVisible" title="添加相册图片" width="66%">
    <template #footer>

      <el-form-item label="图片名字" prop="imagesInfo.imageName"  >
        <el-input v-model="imagesInfo.imageName" ></el-input>
      </el-form-item>

      <el-upload action=""
                 :http-request="handleRequest"
                 :show-file-list="false">
<!--        http://localhost:8080/images2024/02/07/d7d3df54-c9e5-42b6-9486-f6add75a1d40.jpg-->
<!--        http://localhost:8080/images/2024/02/07/82694166-4217-466f-b8cb-1855e8c2b2e6.jpg-->
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

</template>

<script  setup>
import {getCurrentInstance, ref, toRaw} from 'vue'
import {phoneAlbumAddService, phoneAlbumAllInfoService} from "@/api/phoneAlbum.js"
import {Delete} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {deleteImages, selectImagesByImagesType} from '@/api/Images.js'
import {uploadImg} from "@/api/uploadImg.js";
//控制添加用户弹窗
let dialogVisible = ref(false)
let dialogVisible1 = ref(false)
const parentBorder = ref(false)
const childBorder = ref(false)
const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    state: 'California',
    city: 'San Francisco',
    address: '3650 21st St, San Francisco',
    zip: 'CA 94114',
    family: [
      {
        name: 'Jerry',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Spike',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
      {
        name: 'Tyke',
        state: 'California',
        city: 'San Francisco',
        address: '3650 21st St, San Francisco',
        zip: 'CA 94114',
      },
    ],
  },
]

const PhoneAlbumNameList=ref([
  {
    id:1,
    phoneAlbumName:'第一次活动',
    List:[
      {
        id:2,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
        image_name:'相册123',
        create_time:'2022年11月11日'
      },
      {
        id:3,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
        image_name:'相册123',
        create_time:'2022年11月11日'
      },
      {
        id:4,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
        image_name:'相册123',
        create_time:'2022年11月11日'
      },
      {
        id:5,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
        image_name:'相册123',
        create_time:'2022年11月11日'
      },
    ]

  },
  {
    id:1,
    phoneAlbumName:'第2次活动',
    List:[
      {
        id:2,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
      },
      {
        id:3,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
      },
      {
        id:4,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
      },
      {
        id:5,
        image_url:'https://img.yzcdn.cn/vant/apple-1.jpg',
      },
    ]

  }

])


const deleteCategory = (row) => {
  //提示用户  确认框
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
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        //刷新列表
        // await articleAllInfo()
        // await getImageAlbumAllInfo()
        await updatePhoneAlbumImage()

      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '用户取消了删除',
        })
      })
}


const changeState = (row) => {
  phoneAlbumId.value=row.id
  dialogVisible.value= true
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
  console.log(res.data)
  dialogVisible.value=false
  console.log(res.data)


  imagesInfo.value.image=''
  imagesInfo.value.imageName=''
  imagesInfo.value.imageType=''




  // const res= await

}
const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL
const imagesInfo = ref({
  imageType:'',
  imageName:'',
  image:'',
})
const imageUrl = ref()


const handleRequest = async (params) => {
  // if (imagesInfo.value.imageName===''){
  //   ElMessage.error("标题不能为空")
  //   return
  // }

  // console.log(params)
  // const _file = params.raw;
  // let blob = new Blob([_file]);
  //
  //
  // // let blob = new Blob([JSON.stringify(params)], { type: 'application/json' });
  // imageUrl.value = params.raw
  // console.log(imageUrl.value)

  imageUrl.value = window.URL.createObjectURL(params.file)
  imagesInfo.value.image=params
  imagesInfo.value.imageType=phoneAlbumId.value
  console.log(imageUrl)
  // var { file } = params;
  // var formData = new FormData();
  // // formData.append("token", data.params.token);
  // formData.append("file", file);
  // formData.append('imageType', imagesInfo.value.imageType)
  // formData.append('imageName', imagesInfo.value.imageName)
  //
  //
  // const res =await uploadImg(formData)
  // console.log(res.data)
  // imagesInfo.value.imageUrl = (res.data)
  // console.log(  imagesInfo.value.imageUrl)

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
  console.log(res.data)
}
let imageAlbumAllList = ref([])
const getImageAlbumAllInfo = async () => {
  const res = await phoneAlbumAllInfoService()
  console.log(res)
  imageAlbumAllList.value=res.data
  // console.log(imageAlbumAllList.value)
  for (let i = 0; i < imageAlbumAllList.value.length; i++) {
    // console.log(imageAlbumAllList.value[i].id)
    imageAlbumAllList.value[i].List=await getImageAlbumList(imageAlbumAllList.value[i].id)
  }
  // PhoneAlbumNameList.value=res.data
  console.log(toRaw(imageAlbumAllList.value))

}
getImageAlbumAllInfo()
const getImageAlbumList = async (type) => {
  const res = await selectImagesByImagesType(type)
  // console.log(res)
  return res.data
}
getImageAlbumList()

const updatePhoneAlbumImage = async () => {
  console.log(phoneAlbumId.value)
  imageAlbumAllList.value[phoneAlbumId].List = await getImageAlbumList(phoneAlbumId)
}



</script>
