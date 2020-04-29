<template>
  <div id="app" style="background-color: rgba(235, 235, 235, 0.08)">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
  height: 100%;
   /* background: url("https://cdn.jsdelivr.net/gh/1154562398/cdn-jsDelivr@1.1.0/images/bg.jpg") no-repeat; */
  background-size:100% 100%;
  padding-top: 1%;
  box-sizing: border-box;
}

</style>
