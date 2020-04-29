<template>
  <div>
    <div v-for="(item,index) in picList" :key="index">
      <img :src="item.url" style="float:left">
    </div>
    <div>
      <el-upload
        ref="upload"
        :data="QiniuData"
        :on-error="uploadError"
        :on-success="uploadSuccess"
        :on-exceed="handleExceed"
        :before-upload="beforeUpload"
        :action="domain"
        list-type="picture-card"
        :auto-upload="false"
      >
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{file}">
          <img class="el-upload-list__item-thumbnail" :src="file.url" alt />
          <span class="el-upload-list__item-actions">
            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
              <i class="el-icon-zoom-in"></i>
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleDownload(file)"
            >
              <i class="el-icon-download"></i>
            </span>
            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
              <i class="el-icon-delete"></i>
            </span>
          </span>
        </div>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt />
      </el-dialog>
      <div>
        <el-button type="primary" :loading="loading" @click="handleSubmit">提交</el-button>
        <el-button type="info" plain>取消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { postRequest, getRequest } from "../utils/api";
export default {
  data() {
    return {
      picList: [],
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
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
      picUrl: "",
      userId: ""
    };
  },
  mounted() {
    var _this = this;
    getRequest("/api-user/getToken").then(resp => {
      _this.QiniuData.token = resp.data;
    });
    getRequest("/currentUserId").then(resp => {
      _this.userId = resp.data;

      getRequest("/api-user/pic/getPicByUserId/"+resp.data).then(
        resp => {
          this.picList=resp.data;
        }
      );
    });
  },
  methods: {
    handleSubmit() {
      var _this = this;
      this.$refs.upload.submit();
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 张图片，如需更换，请删除上一张图片在重新选择！`
      );
    },
    beforeUpload(file) {
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
    },
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    uploadSuccess(response, file, fileList) {
      //图片上传成功的方法
      var _this = this;
      console.log(fileList);
      console.log(response);
      console.log(file);
      this.uploadPicUrl = `${this.qiniuaddr}/${response.key}`;

      postRequest("/api-user/pic/", {
        picUrl: _this.uploadPicUrl,
        uid: _this.userId
      }).then(resp => {
        if (resp.status == 200) {
          this.$refs.upload.clearFiles();
          this.$message({
            message: "已经存储",
            type: "success",
            center: true
          });
        } else {
          _this.$message({ type: "error", message: "错误!" });
        }
      });
    },
    uploadError(err, file, fileList) {
      //图片上传失败时调用
      this.$message({
        message: "上传出错，请重试！",
        type: "error",
        center: true
      });
    },
    handleDownload(file) {
      console.log(file);
    }
  }
};
</script>