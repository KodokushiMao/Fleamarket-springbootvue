import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login.vue'
import zhuce from '@/components/register.vue'
import admin from '@/components/administrator.vue'
import main from '@/components/main.vue'
import personalInfo from '@/components/userdetails.vue'
import shopmanage from '@/components/shopmanage.vue'
import addgood from '@/components/shelvesgood.vue'
import shopzhuce from '@/components/shopregister.vue'
import shopadmin from '@/components/examineshop.vue'
import goodadmin from '@/components/examinegood.vue'
import goodinfo from '@/components/gooddetails.vue'
import usershop from '@/components/shopdetails.vue'
import car from '@/components/trolley.vue'
import payed from '@/components/paid.vue'
import sendgood from '@/components/delivery.vue'
import tuikuan from '@/components/refund.vue'
import shouhuo from '@/components/receiving.vue'
import horder from '@/components/historicalorders.vue'
import finished from '@/components/completed.vue'
import xiajia from '@/components/ban.vue'
import shoplevel from '@/components/shoplevel.vue'
import chongzhi from '@/components/deposit.vue'

Vue.use(Router)

const routes = [
  { path: '/login', component: login },
  { path: '/zhuce', component: zhuce },
  { path: '/admin', component: admin },
  { path: '/', component: main },
  { path: '/personalInfo', component: personalInfo },
  { path: '/shopmanage', component: shopmanage },
  { path: '/addgood', component: addgood },
  { path: '/shopzhuce', component: shopzhuce },
  { path: '/shopmanage', component: shopmanage },
  { path: '/shopadmin', component: shopadmin },
  { path: '/goodadmin', component: goodadmin },
  { path: '/goodinfo', component: goodinfo },
  { path: '/usershop', component: usershop },
  { path: '/car', component: car },
  { path: '/payed', component: payed },
  { path: '/sendgood', component: sendgood },
  { path: '/tuikuan', component: tuikuan },
  { path: '/shouhuo', component: shouhuo },
  { path: '/horder', component: horder },
  { path: '/finished', component: finished },
  { path: '/xiajia', component: xiajia },
  { path: '/shoplevel', component: shoplevel },
  { path: '/chongzhi', component: chongzhi }
]

const router = new Router({
  routes: routes
})

export default router
