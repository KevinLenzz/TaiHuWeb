import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import App from '../App.vue'
import TH from '../views/TH.vue'
import MapJS from '../views/MapJS.vue'
import MapFY from '../views/MapFY.vue'
import MapSZ from '../views/MapSZ.vue'
import JD from '../views/JD.vue'
import about from '../views/about.vue'
import RL from '../views/RL.vue'
import Food from '../views/Food.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'rd',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'home',
    component: TH
  },
  {
    path: '/hydrology/water-quality',
    name: 'water-quality',
    component: MapSZ
  },
  {
    path: '/hydrology/rivers-lakes',
    name: 'rivers-lakes',
    component: RL
  },
  {
    path: '/hydrology/precipitation',
    name: 'precipitation',
    component: MapJS
  },
  {
    path: '/humanity/attraction',
    name: 'attraction',
    component: JD
  },
  {
    path: '/humanity/dialect',
    name: 'dialect',
    component: MapFY
  },
  {
    path: '/humanity/food',
    name: 'food',
    component: Food
  },
  {
    path: '/about',
    name: 'abt',
    component: about
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
