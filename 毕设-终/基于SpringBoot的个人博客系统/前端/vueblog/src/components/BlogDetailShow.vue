<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
    </el-col>
    <el-col :span="24">
      <div>
        <div>
          <h3 style="margin-top: 0px;margin-bottom: 0px">{{article.title}}</h3>
        </div>
        <div
          style="width: 100%;margin-top: 5px;display: flex;justify-content: flex-end;align-items: center"
        >
          <div
            style="display: inline; color: #20a0ff;margin-left: 50px;margin-right:20px;font-size: 12px;"
          >{{article.nickname}}</div>
          <span
            style="color: #20a0ff;margin-right:20px;font-size: 12px;"
          >浏览 {{article.pageView==null?0:article.pageView}}</span>
          <span
            style="color: #20a0ff;margin-right:20px;font-size: 12px;"
          >{{article.editTime | formatDateTime}}</span>
          <el-tag
            type="success"
            v-for="(item,index) in article.tags"
            :key="index"
            size="small"
            style="margin-left: 8px"
          >{{item.tagName}}</el-tag>
          <span style="margin:0px 50px 0px 0px"></span>
        </div>
      </div>
    </el-col>
    <el-col>
      <div style="text-align: left" v-html="article.htmlContent"></div>
      <el-divider></el-divider>
      <el-row>
      <template v-for="(comment,index) in comment">
        <el-row :key="index">
          <div style="float:left">
            {{comment.nickName}}:
          </div>
          <div style="float:left">
            {{comment.comments}}
          </div>
          <el-divider></el-divider>
        </el-row>
   
    </template>

      </el-row>
      <el-divider></el-divider>
      <el-row>
        <el-col><div style="float:left;font-size: 20px;">{{this.currentUserName}}:</div></el-col>
        <el-col><el-input id="comment"></el-input></el-col>
        <el-col><el-button style="float: right; margin-top:5px;" :disabled="dis">评论</el-button></el-col>
      </el-row>
    </el-col>
  </el-row>
</template>
<script>

import { getRequest, getRequestByGuest } from "../utils/api";
export default {
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  },
  mounted: function() {
    var aid = this.$route.query.aid;
    this.activeName = this.$route.query.an;
    var _this = this;
    this.loading = true;
    getRequest("/currentUserNickName").then(
      function(msg) {
        _this.currentUserName = msg.data;
        _this.dis=false;
      },
      function(msg) {
        _this.currentUserName = "游客";
      }
    );
    getRequestByGuest("/api-article/article/show/" + aid).then(
      resp => {
        if (resp.status == 200) {
          _this.article = resp.data;
        }
        _this.loading = false;
      },
      resp => {
        _this.loading = false;
        _this.$message({ type: "error", message: "页面加载失败!" });
      }
    );
  },
  data() {
    return {
      currentUserName:"",
      article: {},
      loading: false,
      activeName: "",
      dis: true,
      comment:[{
        nickName:"zhansan",
        comments:"asdasdsad"
      },
      {
        nickName:"zhansan",
        comments:"asdasdsad"
      },
      {
        nickName:"zhansan",
        comments:"asdasdsad"
      }]
       
    };
  }
};
</script>
