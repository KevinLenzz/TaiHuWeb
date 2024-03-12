<template>
    <h1 id="text">太湖流域数据可视化</h1>
    <div id="map" style="width: 100vw;height:100vh"></div>
</template>
<script>
import {datum} from "../assets/thvg.js"
import 'animate.css';
import * as echarts from 'echarts';
// 基于准备好的dom，初始化echarts实例
export default {
    mounted() {
        let mapChart = echarts.init(document.getElementById("map"));
        // 绘制图表
        echarts.registerMap("thMap",datum)
        mapChart.setOption({
            geo:{
                type:"map",
                map:"thMap",
                itemStyle:{
                    areaColor:"#d1edc4",
                    borderWidth: 3,
                    borderColor:"#95d475",
                    shadowColor:"rgba(135,206,235,0.5)",
                    shadowBlur:5,
                },
                emphasis: {
                    itemStyle:{
                        areaColor:"#d1edc4"
                    }
                }
            },
            graphic: [
            {
                type: "image",
                left: "center",
                top: "center",
                style: {
                    image: "https://img.zcool.cn/community/0148cb600111f011013f7928a9b7ae.jpg@3000w_1l_0o_100sh.jpg",
                    width: 2000,
                    height: 2000
                }
            },]
        })
        window.addEventListener("resize",()=>{
            mapChart.resize();
        })
    }
}
</script>

<style>
@font-face {
  font-family: 'UserFont';
  src: url('../assets/QTQianMo.ttf') format('truetype');
  font-weight: normal;
  font-style: normal;
}
#text{
    user-select:none;
    position: absolute;
    z-index:1;
    left:30%;
    top:35%;
    font-size: 5vw;
    font-family: 'UserFont';
    color: lightseagreen;
    display: inline-block;
    animation: fadeInUp;
    animation-duration: 2s;
    text-shadow: 2px 2px 2px #000000;
}
#map{
    z-index:-1;
}
</style>