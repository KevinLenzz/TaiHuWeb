<template>
        <el-menu router default-active="1" class="el-menu-vertical-demo" background-color="#B0E0E6" :collapse="isCollapse">
            <el-menu-item>
            <el-button @click="open" circle="true">{{arrow}}</el-button>
            </el-menu-item>
            <el-menu-item index="/home">
                <el-icon>
                    <house />
                </el-icon>
                <span>首页</span>
            </el-menu-item>
            <el-sub-menu index="2">
                <template #title>
                    <el-icon>
                        <cloudy />
                    </el-icon>
                    <span>水文</span>
                </template>
                <el-menu-item index="/hydrology/water-quality">水质</el-menu-item>
                <el-menu-item index="/hydrology/rivers-lakes">河汊湖泊</el-menu-item>
                <el-menu-item index="/hydrology/precipitation">降水</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
                <template #title>
                    <el-icon>
                        <reading />
                    </el-icon>
                    <span>人文</span>
                </template>
                <el-menu-item index="/humanity/attraction">景点</el-menu-item>
                <el-menu-item index="/humanity/dialect">方言</el-menu-item>
                <el-menu-item index="/humanity/food">美食</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/about">
                <el-icon>
                    <document />
                </el-icon>
                <span>关于</span>
            </el-menu-item>
        </el-menu>
</template>
  
<script lang="ts" setup>
import { ref } from 'vue'
import {
    House,
    Reading,
    Cloudy,
    Document
} from '@element-plus/icons-vue'
const arrow=ref("一>")
const isCollapse = ref(true)
const open=()=>{
    if(isCollapse.value){
        isCollapse.value=false;
        arrow.value="<一";
    }else{
        isCollapse.value=true;
        arrow.value="一>";
    }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
}
.el-menu-vertical-demo{
    height: 100vh;
    user-select:none;
}
.el-menu{
 border:0!important;
}
.pitchless{
    user-select:none;
}
</style>