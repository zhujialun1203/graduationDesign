<template>
  <el-container class="home_container">
    <el-aside width="auto" @mouseenter.native="collapseOpen" @mouseleave.native="collapseClose">
      <el-menu
        default-active="1-4-1"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
      >
        <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
          <el-submenu :index="index+''" v-if="item.children.length>1" :key="index" width="auto">
            <template slot="title">
              <i :class="item.iconCls"></i>
              <span>{{item.name}}</span>
            </template>
            <el-menu-item
              v-for="child in item.children"
              v-if="!child.hidden"
              :index="child.path"
              :key="child.path"
            >{{child.name}}</el-menu-item>
          </el-submenu>
          <template v-else>
            <el-menu-item :index="item.children[0].path">
              <i :class="item.children[0].iconCls"></i>
              <span slot="title">{{item.children[0].name}}</span>
            </el-menu-item>
          </template>
        </template>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-button @click="collapseStatus">
          <i class="el-icon-d-arrow-right"></i>
        </el-button>
        <div class="home_title">V部落博客管理平台</div>
        <div class="home_userinfoContainer">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link home_userinfo">
              {{currentUserName}}
              <i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
              <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
              <el-dropdown-item command="myMessage">我的信息</el-dropdown-item>
              <el-dropdown-item command="Visit">浏览页</el-dropdown-item>
              <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
        </el-breadcrumb>
        <keep-alive>
          <router-view v-if="this.$route.meta.keepAlive"></router-view>
        </keep-alive>
        <router-view v-if="!this.$route.meta.keepAlive"></router-view>
      </el-main>
    </el-container>
  </el-container>

  <!-- <el-container class="home_container">

    <el-container>
     
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>-->
</template>
<script>
import { getRequest } from "../utils/api";
export default {
  methods: {
    //  handleCloseDraw(done) {
    //     this.$confirm('确认关闭？')
    //       .then(_ => {
    //         done();
    //       })
    //       .catch(_ => {});
    //   },
    // MyArticle(){
    //   var _this=this;
    //   _this.$router.replace({path: '/articleList'});
    // },

    itemClick(aid) {
      var _this = this;
      _this.display = true;
      // this.$router.push({ path: "/showDtail", query: { aid: row.id } });
    },
    collapseStatus() {
      this.collapseBtnClick = this.isCollapse;
      this.isCollapse = !this.isCollapse;
    },
    collapseOpen() {
      if (this.collapseBtnClick) return;
      this.isCollapse = false;
    },

    collapseClose() {
      if (this.collapseBtnClick) return;
      this.isCollapse = true;
    },
    handleCommand(command) {
      var _this = this;
      if (command == "logout") {
        this.$confirm("注销登录吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(
          function() {
            getRequest("/logout");
            _this.currentUserName = "游客";
            _this.$router.replace({ path: "/" });
          },
          function() {
            //取消
          }
        );
      } else if (command == "sysMsg") {
        getRequest("/api-user/notice/").then(resp => {
          if (resp.status == 200) {
            _this.notice = resp.data;
            _this.$alert("公告：" + _this.notice.notice, {
              confirmButtonText: "确定",
              callback: action => {}
            });
          }
        });
      }else if (command == "MyArticle")
      {
         _this.$router.replace({path: '/articleList'});
      }else if (command == "myMessage")
      {
         _this.$router.replace({path: '/userInfo'});
      }
      else if (command == "Visit")
      {
         window.open('http://localhost:8080/#/');
      }
      
    }
  },
  mounted: function() {
    // this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
    //   confirmButtonText: '确定',
    //   callback: action => {
    //   }
    // });
    var _this = this;
    getRequest("/currentUserNickName").then(
      function(msg) {
        _this.currentUserName = msg.data;
      },
      function(msg) {
        _this.currentUserName = "游客";
      }
    );
  },
  data() {
    return {
      direction: "rtl",
      drawer: false,
      currentUserName: "",
      collapseBtnClick: false,
      isCollapse: true,
      display: false,
      drawerWidth: "700px"
    };
  }
};
</script>
<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.el-header {
  background-color: #20a0ff;
  color: #333;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.home_title {
  color: #fff;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
