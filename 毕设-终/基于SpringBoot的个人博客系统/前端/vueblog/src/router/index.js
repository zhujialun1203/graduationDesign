import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import reg from '@/components/register'
import Home from '@/components/Home'
import ArticleList from '@/components/ArticleList'
import CateMana from '@/components/CateMana'
import DataCharts from '@/components/DataCharts'
import PostArticle from '@/components/PostArticle'
import UserMana from '@/components/UserMana'
import BlogDetail from '@/components/BlogDetail'
import index from '@/components/index'
import BlogTableShow from '@/components/BlogTableShow'
import BlogDetailShow from '@/components/BlogDetailShow'
import userInfo from '@/components/userInfo'
import userAvatar from '@/components/userAvatar'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/show',
      name: 'test',
      hidden: true,
      component: BlogTableShow
    },
    {
      path: '/showDtail',
      name: 'test',
      hidden: true,
      component: BlogDetailShow
    },
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, 
    {
      path: '/reg',
      name: '注册',
      hidden: true,
      component: reg
    }, 
    {
      path: '/home',
      name: '',
      component: Home,
      hidden: true
    },
    {
      path: '/home',
      component: Home,
      name: '主页',
      children: [
        {
          path: '/index',
          iconCls: 'fa fa-user-o',
          name: '主页',
          component: index
        },
        
      ]
    },
    {
      path: '/home',
      component: Home,
      name: '文章管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        
        {
          path: '/articleList',
          name: '文章列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发表文章',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '博客详情',
          component: BlogDetail,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑博客',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '用户管理',
      iconCls: 'el-icon-user-solid',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        },
      
        {
          path: '/userInfo',
          name: '个人信息',
          component: userInfo,
          meta: {
            keepAlive: true
          }
        },
        {
          path: '/userAvatar',
          name: '头像更改',
          component: userAvatar,
          meta: {
            keepAlive: true
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '栏目管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '栏目管理',
          component: CateMana
        }
      ]
    },
  ]
})
