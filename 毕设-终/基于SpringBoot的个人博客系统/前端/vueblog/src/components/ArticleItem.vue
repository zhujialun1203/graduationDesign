<template>
  <div class="itembox">
    <div class="titlebox">
      <span class="colorbox"></span>
      <span class="title" v-text="article.title"></span>
    </div>
    <div class="content">
      <div class="img" v-html="article.background"></div>
      <div class="intro">
        <div class="introcontent" v-text="article.Intro"></div>
        <div class="other">
          <span class="time">
            <i class="el-icon-date"></i>
            发布时间：{{ article.date }}
          </span>
          <span class="category">
            <i class="el-icon-s-grid"></i>
            分类：{{ article.category[0] }}
          </span>
          <span class="view">
            <i class="el-icon-view"></i>
            浏览量：{{ article.views }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {}
  },
  props: ["article"]
}
</script>
<style lang="scss" scoped>
.itembox {
  height: 235px !important;
  max-width: 950px !important;
  min-width: 800px;
  background: white;
  padding: 10px 20px;
  text-align: left;
}

.titlebox {
  margin: 10px;
  text-align: left;
  margin-bottom: 20px;
  position: relative;
  .colorbox {
    position: absolute;
    left: -30px;
    top: -20px;
    display: inline-block;
    height: 45px;
    width: 10px;
    background: rgb(10, 105, 168);
  }
  .title {
    font-size: 25px;
    font-weight: bold;
  }
}

.content {
  height: 170px;
  display: flex;
  .img {
    margin: 0px 10px;
    border: 1px solid rgb(231, 230, 229);
    background: white;
    height: 170px;
    width: 280px !important;
    overflow: hidden;
    vertical-align: middle;
  }

  .intro {
    text-align: left;
    width: calc(100% - 300px);
    .introcontent {
      font-size: 15px;
      line-height: 25px;
      color: rgb(119, 118, 116);
      margin: 0px 10px;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 5;
      overflow: hidden;
    }
  }
}

.other {
  margin: 0px 10px;
  margin-top: 12px;
  padding-top: 5px;
  border-top: 1px solid gainsboro;
  height: 35px;
  line-height: 35px;
  font-size: 14px;

  span {
    margin-right: 20px;
    color: rgb(145, 152, 155);
  }
  // .category {
  //   color: #ba8f6c;
  // }
  // .time {
  //   color: #725334;
  // }
  // .view {
  //   color: rgb(69, 137, 148);
  // }
}
</style>
<style lang="scss">
.img {
  img {
    width: 280px;
    height: 170px;
    object-fit: cover;
  }
}
</style>
