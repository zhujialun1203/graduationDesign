<template >
  <el-container>
    <el-form
      :rules="rules"
      :model="regForm"
      class="reg-container"
      label-position="left"
      label-width="0px"
      v-loading="loading"
    >
      <h3 class="reg_title">注册</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="regForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="regForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          type="password"
          v-model="regForm.confirmPassword"
          auto-complete="off"
          placeholder="重复密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">注册</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>
<script>
import { postRequest } from "../utils/api";
export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.regForm.password !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      regForm: {
        username: "",
        password: "",
        confirmPassword: ""
      },
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        password: [
          { requirsed: true, message: "密码不能为空", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ],
        cosnfirmPassword: [
          { required: true, message: "确认密码不能为空", trigger: "blur" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      },
      checked: true,

      loading: false
    };
  },
  methods: {
    submitClick: function() {
      var _this = this;
      this.loading = true;
      if (this.regForm.password != this.regForm.confirmPassword) {
        this.$alert("两次密码不一致");
        this.loading = false;
      } else {
        postRequest("/reg", {
          userName: this.regForm.username,
          passWord: this.regForm.password
        }).then(
          resp => {
            _this.loading = false;
            console.log(resp.data);
            if (resp.data.status == "success") {
              _this.$alert(resp.data.msg);
              _this.$router.replace({ path: "/" });
            } else if (resp.data.status == "error") {
              this.$alert(resp.data.msg);
            }
          },
          resp => {
            _this.loading = false;
            _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
          }
        );
      }
    }
  }
};
</script>
<style>
.reg-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;

  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.reg_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.reg_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
