<template>

  <TopNavBar>
  </TopNavBar>
  <div class="questionnaire-container">
    <el-form ref="form" label-width="120px" :model="formData" :rules="formRules" class="questionnaire-form">
      <!-- 学生信息部分 -->
      <el-form-item label="姓名" prop="name">
        <el-input v-model="formData.name" placeholder="请输入你的姓名"></el-input>
      </el-form-item>

      <el-form-item label="学号" prop="studentId">
        <el-input v-model="formData.studentId" placeholder="请输入你的学号"></el-input>
      </el-form-item>

      <el-form-item label="级别" prop="department">
        <el-input v-model="formData.department" placeholder="请输入你的年级信息(如计科2102)"></el-input>
      </el-form-item>

      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input v-model="formData.phoneNumber" placeholder="请输入你的手机号码"></el-input>
      </el-form-item>

      <el-form-item label="邮箱" prop="email">
        <el-input v-model="formData.email" placeholder="请输入你的邮箱"></el-input>
      </el-form-item>

      <!-- 参加社团原因部分 -->
      <el-form-item label="参加社团的原因" prop="reason">
        <el-select v-model="formData.reason" placeholder="请选择或输入原因">
          <el-option
              v-for="reason in reasons"
              :key="reason.value"
              :label="reason.label"
              :value="reason.value">
              </el-option>
        </el-select>
      </el-form-item>


        <!-- 自定义原因 -->
        <el-form-item label="其他原因（选填）" prop="customReason">
          <el-input v-model="formData.customReason" type="textarea" autosize placeholder="如有其他原因，请详细说明"></el-input>
        </el-form-item>

        <!-- 报名信息部分 -->
        <el-form-item label="意向职位" prop="desiredPosition">
          <el-input v-model="formData.desiredPosition" placeholder="请输入意向职位"></el-input>
        </el-form-item>

        <el-form-item label="自我介绍" prop="introduction">
          <el-input v-model="formData.introduction" type="textarea" autosize placeholder="请简要介绍一下自己"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
    </el-form>
  </div>

  <footer1>
  </footer1>

</template>

<script setup>
import { ref, reactive } from 'vue';
import {ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElMessage} from 'element-plus';
import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import footer1 from "@/components/Footer/footer.vue";
import {enrollmentAddService} from "@/api/enrollment.js";

let formData = reactive({
  name: '',
  studentId: '',
  department: '',
  phoneNumber: '',
  reason: '',
  customReason: '',
  desiredPosition: '',
  introduction: '',
  email:''
});

const reasons = [
  { value: '兴趣爱好', label: '兴趣爱好' },
  { value: '提升技能', label: '提升技能' },
  { value: '社交拓展', label: '社交拓展' },
];

const formRules = {
  // 添加表单验证规则，这里仅示例
  name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
  studentId: [{ required: true, message: '学号不能为空', trigger: 'blur' }],
  phoneNumber: [{ required: true, validator: validatePhoneNumber, trigger: 'blur' }],
  department: [{ required: true, message: '年级不能为空', trigger: 'blur' }],
  introduction: [{ required: true, message: '自我介绍不能为空', trigger: 'blur' }],
};

function validatePhoneNumber(rule, value, callback) {
  // 这里可以添加手机号码的验证逻辑
  if (!/^\d{11}$/.test(value)) {
    return callback(new Error('请输入正确的手机号码'));
  }
  callback();
}

const onSubmit = async () => {
  if (formData.name!=='' && formData.phoneNumber!=='' && formData.department!=='' && formData.phoneNumber!=='' && formData.introduction!==''){
    // 提交表单数据逻辑
    let result = await enrollmentAddService(formData)
    ElMessage.success(result.msg? result.msg : '提交成功');

    formData.name='';
    formData.phoneNumber='';
    formData.department='';
    formData.phoneNumber='';
    formData.introduction='';
    formData.customReason='';
    formData.reason='';
    formData.desiredPosition='';
    formData.email='';


    // console.log(formData);
  }else {
    ElMessage.error('请填写完整信息')
  }

};
</script>

<style scoped>
.questionnaire-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f7fa;
}

.questionnaire-form {
  width: 50%;
  max-width: 600px;
  padding: 2rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: white;
  border-radius: 8px;
}

/* 针对不同屏幕大小进行响应式设计 */
@media (max-width: 768px) {
  .questionnaire-form {
    width: 90%;
  }
}
</style>
