<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <el-form-item label="头像">
      <div class="upload-info">
        <div>
          <el-upload
            class="upload-pic"
            :action="domain"
            :data="QiniuData"
            :on-remove="handleRemove"
            :on-error="uploadError"
            :on-success="uploadSuccess"
            :before-remove="beforeRemove"
            :before-upload="beforeAvatarUpload"
            :limit="3"
            multiple
            :on-exceed="handleExceed"
            :file-list="fileList"
            :show-file-list="false"
          >
            <img v-if="this.user.avatar" :src="this.user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </div>
    </el-form-item>
    <el-form-item label="用户昵称" prop="nickName">
      <el-input v-model="user.nickName" />
    </el-form-item>
    <el-form-item label="qq" prop="qq">
      <el-input v-model="user.qq" maxlength="11" />
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="user.email" maxlength="50" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
// import { updateUserProfile } from "@/api/system/user";

import { getRequest, postRequest } from "../utils/api";
import { uploadFileRequest } from "../utils/api";

export default {
  props: {},
  data() {
    return {
      loading: false,
      QiniuData: {
        key: "", //图片名字处理
        token: "", //七牛云token
        data: {}
      },
      domain: "http://upload.qiniup.com", // 七牛云的上传地址（华东区）
      qiniuaddr: "http://q9fqfs6zo.bkt.clouddn.com/", // 七牛云的图片外链地址 七牛云空间的外链地址
      uploadPicUrl: "", //提交到后台图片地址
      fileList: [],

      user: {
        userId: "",
        nickName: "",
        email: "",
        qq: "",
        avatar: ""
      },
      // 表单校验
      rules: {
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        qq: [
          {
            type: "number",
            required: true,
            message: "请输入QQ",
            trigger: "blur"
          }
        ],
        email: [
          { required: true, message: "邮箱地址不能为空", trigger: "blur" },
          {
            type: "email",
            message: "'请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ]
      }
    };
  },
  methods: {
    handleRemove(file, fileList) {
      this.user.avatar = "";
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 张图片，如需更换，请删除上一张图片在重新选择！`
      );
    },
    beforeAvatarUpload(file) {
      //图片上传之前的方法
      // const isPNG = file.type === "image/png";
      // const isJPEG = file.type === "image/jpeg";
      // const isJPG = file.type === "image/jpg";
      // const isLt2M = file.size / 1024 / 1024 < 2;

      // if (!isPNG && !isJPEG && !isJPG) {
      //   this.$message.error("上传头像图片只能是 jpg、png、jpeg 格式!");
      //   return false;
      // }
      // if (!isLt2M) {
      //   this.$message.error("上传头像图片大小不能超过 2MB!");
      //   return false;
      // }
      this.QiniuData.data = file;
      this.QiniuData.key = `${file.name}`;
      console.log(this.QiniuData.key);
    },
    uploadSuccess(response, file, fileList) {
      //图片上传成功的方法
      console.log(fileList);
      console.log(response);
      console.log(file);
      this.user.avatar = `${this.qiniuaddr}/${response.key}`;
    },
    uploadError(err, file, fileList) {
      //图片上传失败时调用
      this.$message({
        message: "上传出错，请重试！",
        type: "error",
        center: true
      });
    },
    beforeRemove(file, fileList) {
      // return this.$confirm(`确定移除 ${ file.name }？`);
    },
    submit() {
      var _this = this;
      postRequest("/api-user/userInfo/updateUserInfo", {
        userId: _this.user.userId,
        nickName: _this.user.nickName,
        email: _this.user.email,
        qq: _this.user.qq,
        avatar: _this.user.avatar
      }).then(resp => {
        _this.loading = false;
        console.log(resp.data);
        if (resp.data.status == "success") {
          _this.$alert(resp.data.msg);
        } else if (resp.data.status == "error") {
          this.$alert(resp.data.msg);
        }
      });
    }
  },
  mounted() {
    var _this = this;
    getRequest("/getMyUserinfo").then(resp => {
      if (resp.status == 200) {
        _this.user = resp.data;
        // console.log(resp.data);
        // _this.$alert("公告："+_this.user.userId, {
        // confirmButtonText: '确定',
        // callback: action => {
        // }
        // });
      } else {
        _this.$message({ type: "error", message: "获取用户信息失败!" });
      }
    });
    getRequest("/api-user/getToken").then(resp => {
      if (resp.status == 200) {
        _this.QiniuData.token = resp.data;
      } else {
        _this.$message({ type: "error", message: "获取七牛云Token失败!" });
      }
    });
  }
};
</script>
