<style type="text/css">
.el-container {
  background-color: white;
  height: 100%;
}
.blog_table_footer {
  display: flex;
  box-sizing: content-box;
  padding-top: 10px;
  padding-bottom: 0px;
  margin-bottom: 0px;
  justify-content: space-between;
  float: right;
}
.el-dropdown-link {
  cursor: pointer;
  color: #000000;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
.el-main{
  line-height:0%;
}



.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
<template>
<el-container>
<el-row :gutter="10">
  <el-row>
    <div style="display: flex;justify-content: flex-start">
        <el-input
          placeholder="通过标题搜索该分类下的博客..."
          prefix-icon="el-icon-search"
          v-model="keywords"
          style="width: 400px;"
          size="middle"
        ></el-input>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          style="margin-left: 3px"
          @click="searchClick"
        >搜索</el-button>
      </div>
      </el-row>
  <el-row><el-image :src="imgsrc"></el-image></el-row>
  <el-row >
          <el-col :span="18">
      <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
          <div v-for="item in articles" :key="item" style="height:150px;margin-top: 10px;">
          <el-card style="height:100%;width:90%;margin:0 auto"></el-card></div>
          </el-col >
              
</el-row >
<el-row>
  <el-col :span="12"><div class="grid-content bg-purple-light">
     <div class="blog_table_footer">
        <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalCount"
          @current-change="currentChange"
          v-show="this.articles.length>0"
        ></el-pagination>
      </div>
      </div></el-col>
</el-row>
</el-row>




</el-container>
<!-- 
       <div style="display: flex;justify-content: flex-start">
        <el-input
          placeholder="通过标题搜索该分类下的博客..."
          prefix-icon="el-icon-search"
          v-model="keywords"
          style="width: 400px;"
          size="middle"
        ></el-input>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          style="margin-left: 3px"
          @click="searchClick"
        >搜索</el-button>
      </div>
      <div>blog</div>
      <div style="float:right">
        <el-dropdown>
          <span class="el-dropdown-link">
            {{currentUserName}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>黄金糕</el-dropdown-item>
            <el-dropdown-item>狮子头</el-dropdown-item>
            <el-dropdown-item>螺蛳粉</el-dropdown-item>
            <el-dropdown-item disabled>双皮奶</el-dropdown-item>
            <el-dropdown-item divided>蚵仔煎</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div> -->

  

      <!-- <el-table
        ref="multipleTable"
        :data="articles"
        tooltip-effect="dark"
        style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
        max-height="390"
        @selection-change="handleSelectionChange"
        v-loading="loading"
      >
        <el-table-column type="selection" width="35" align="left" v-if="showEdit || showDelete"></el-table-column>
        <el-table-column label="标题" width="400" align="left">
          <template slot-scope="scope">
            <span
              style="color: #409eff;cursor: pointer"
              @click="itemClick(scope.row)"
            >{{ scope.row.title}}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column
        label="最近编辑时间" width="140" align="left">
        <template slot-scope="scope">{{ scope.row.editTime | formatDateTime}}</template>
        </el-table-column>-->
        <!-- <el-table-column prop="nickname" label="作者" width="120" align="left"></el-table-column>
        <el-table-column prop="cateName" label="所属分类" width="120" align="left"></el-table-column>
      </el-table> --> 
     


  <!--   
  <div><el-button @click="login" :disabled="loginable" style="float:right">登录</el-button></div>-->
</template>

<script>
import { putRequest, getRequestByGuest } from "../utils/api";
import { getRequest } from "../utils/api";
import articleitem from "./ArticleItem";
//  import Vue from 'vue'
//  var bus = new Vue()

export default {
  data() {
    return {
      imgsrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      loginable: false,
      articles: [],
      selItems: [],
      loading: false,
      currentPage: 1,
      totalCount: -1,
      pageSize: 20,
      keywords: "",
      dustbinData: [],
      currentUserName: ""
    };
  },
  mounted: function() {
    var _this = this;
    this.loading = true;
    this.loadBlogs(1, this.pageSize);
    var _this = this;
    window.bus.$on("blogTableReload", function() {
      _this.loading = true;
      _this.loadBlogs(_this.currentPage, _this.pageSize);
    });
    getRequest("/currentUserNickName").then(
      function(msg) {
        _this.currentUserName = msg.data;
        _this.loginable = !_this.loginable;
      },
      function(msg) {
        _this.currentUserName = "游客";
      }
    );
  },
  methods: {
    login() {
      this.$router.push({ path: "/" });
    },
    searchClick() {
      this.loadBlogs(1, this.pageSize);
    },
    itemClick(row) {
      this.$router.push({ path: "/showDtail", query: { aid: row.id } });
    },
    deleteMany() {
      var selItems = this.selItems;
      for (var i = 0; i < selItems.length; i++) {
        this.dustbinData.push(selItems[i].id);
      }
      this.deleteToDustBin(selItems[0].state);
    },
    //翻页
    currentChange(currentPage) {
      this.currentPage = currentPage;
      this.loading = true;
      this.loadBlogs(currentPage, this.pageSize);
    },
    loadBlogs(page, count) {
      var _this = this;
      var url =
        "/api-article/article/show/guest" +
        "?page=" +
        page +
        "&count=" +
        count +
        "&keywords=" +
        this.keywords;
      getRequestByGuest(url)
        .then(
          resp => {
            _this.loading = false;
            if (resp.status == 200) {
              _this.articles = resp.data.articles;
              _this.totalCount = resp.data.totalCount;
            } else {
              _this.$message({ type: "error", message: "数据加载失败!" });
            }
          },
          resp => {
            _this.loading = false;
            if (resp.response.status == 403) {
              _this.$message({ type: "error", message: resp.response.data });
            } else {
              _this.$message({ type: "error", message: "数据加载失败!" });
            }
          }
        )
        .catch(resp => {
          //压根没见到服务器
          _this.loading = false;
          _this.$message({ type: "error", message: "数据加载失败!" });
        });
    },
    handleSelectionChange(val) {
      this.selItems = val;
    },
    handleEdit(index, row) {
      this.$router.push({
        path: "/editBlog",
        query: { from: this.activeName, id: row.id }
      });
    },
    handleDelete(index, row) {
      this.dustbinData.push(row.id);
      this.deleteToDustBin(row.state);
    },
    handleRestore(index, row) {
      let _this = this;
      this.$confirm("将该文件还原到原处，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          _this.loading = true;
          putRequest("/api-article/article/restore", {
            articleId: row.id
          }).then(resp => {
            if (resp.status == 200) {
              var data = resp.data;
              _this.$message({ type: data.status, message: data.msg });
              if (data.status == "success") {
                window.bus.$emit("blogTableReload"); //通过选项卡都重新加载数据
              }
            } else {
              _this.$message({ type: "error", message: "还原失败!" });
            }
            _this.loading = false;
          });
        })
        .catch(() => {
          _this.$message({
            type: "info",
            message: "已取消还原"
          });
        });
    },
    deleteToDustBin(state) {
      var _this = this;
      this.$confirm(
        state != 2
          ? "将该文件放入回收站，是否继续?"
          : "永久删除该文件, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          _this.loading = true;
          var url = "";
          if (_this.state == -2) {
            url = "/api-article/admin/article/dustbin";
          } else {
            url = "/api-article/article/dustbin";
          }
          putRequest(url, { aids: _this.dustbinData, state: state }).then(
            resp => {
              if (resp.status == 200) {
                var data = resp.data;
                _this.$message({ type: data.status, message: data.msg });
                if (data.status == "success") {
                  window.bus.$emit("blogTableReload"); //通过选项卡都重新加载数据
                }
              } else {
                _this.$message({ type: "error", message: "删除失败!" });
              }
              _this.loading = false;
              _this.dustbinData = [];
            },
            resp => {
              _this.loading = false;
              _this.$message({ type: "error", message: "删除失败!" });
              _this.dustbinData = [];
            }
          );
        })
        .catch(() => {
          _this.$message({
            type: "info",
            message: "已取消删除"
          });
          _this.dustbinData = [];
        });
    }
  },
  props: ["state", "showEdit", "showDelete", "activeName", "showRestore"]
};
</script>
