<template>
    <el-container class="layout-container-demo" style="height: 100vh;overflow-x:hidden;overflow-y:hidden">
    <Menu></Menu>
    <el-main style="caret-color: rgba(0,0,0,0);overflow-x:hidden;overflow-y:hidden;padding:0px">
        <router-view></router-view>
    </el-main>
    </el-container>
</template>
<script lang="ts" setup>
    import Menu from './views/Menu.vue'
    import MapJS from './views/MapJS.vue'
    import TH from './views/TH.vue'
</script>
<style lang="less">
</style>
