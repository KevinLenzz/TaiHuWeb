<template>
    <div id="map" style="width: 100vw;height:100vh"></div>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>{{ title }}</span>
            </div>
        </template>
        <div>更新时间:{{ updateTime }}</div>
        <div>{{ summary }}</div>
        <table>
            <tr>
                <th>预报时间</th>
                <th>降水类型</th>
                <th>5分钟累计降水量(mm)</th>
            </tr>
            <tr v-for="o in sqs" class="text item">
                <td>{{o.fxTime}}</td>
                <td>
                    <div v-if="o.type==='rain'">雨</div>
                    <div v-if="o.type==='snow'">雪</div>
                </td>
                <td>{{o.precip}}</td>
            </tr>
        </table>
    </el-card>
</template>
<script>
import { datum } from "../assets/thlyvg.js"
import * as echarts from 'echarts';
import { JSd } from '../assets/JSdata.js'
import axios from 'axios';
import { onMounted, ref } from 'vue'
// 基于准备好的dom，初始化echarts实例
export default {
    setup() {
        const sqs=ref([]);
        const title = ref("点击地区");
        const updateTime=ref("");
        const summary=ref("");
        onMounted(() => {
            let tmp = JSON.parse(JSON.stringify(JSd));
            axios({
                method: "post",
                url: "http://localhost:8080/a",
                data: "item=jiangshui"
            }).then(result => {
                let obj = eval(result.data);
                for (var j = 0; j < 61; j++) {
                    tmp[j].value = obj[j].minutely[0].precip;
                }
                let mapChart = echarts.init(document.getElementById("map"));
                // 绘制图表
                echarts.registerMap("thlyMap", datum)
                let option1 = {
                    visualMap: {
                        right: 100,
                        bottom: 40,
                        min: 0,
                        max: 1,
                        itemWidth: 30,
                        itemHeight: 210,
                        inRange: {
                            color: [
                                '#ffffff',
                                '#e0f3f8',
                                '#abd9e9',
                                '#74add1',
                                '#4575b4',
                                '#313695'
                            ]
                        },
                        text: ['High', 'Low'],
                        calculable: true
                    },
                    series: [{
                        type: "map",
                        map: "thlyMap",
                        roam: true,
                        center: [121, 31],
                        itemStyle: {
                            areaColor: "#d1edc4",
                            borderWidth: 1,
                            borderColor: "#95d475",
                            shadowColor: "rgba(135,206,235,0.5)",
                            shadowBlur: 5,
                        },
                        emphasis: {
                            itemStyle: {
                                areaColor: "#f89898",
                            }
                        },
                        data: tmp
                    }],
                    graphic: [
                        {
                            type: "image",
                            left: "center",
                            top: "center",
                            style: {
                                image: "https://i.postimg.cc/RhLZyDm4/wave.png",
                                width: 2000,
                                height: 2000
                            }
                        }]
                }
                mapChart.setOption(option1)
                window.addEventListener("resize", () => {
                    mapChart.resize();
                })
                mapChart.on('click', function (params) {
                    if(params.name){
                        updateTime.value=obj[params.dataIndex].updateTime.substring(11,16);
                        summary.value=obj[params.dataIndex].summary;
                        title.value = params.name;
                        for(var k=0;k<24;k++){
                            sqs.value[k]={fxTime:obj[params.dataIndex].minutely[k].fxTime.substring(11,16),type:obj[params.dataIndex].minutely[k].type,precip:obj[params.dataIndex].minutely[k].precip};
                        }
                    }
                })
            })
        })
        return { title,sqs,summary,updateTime};
    }
}
</script>
<style>
#map {
    z-index: -1;
}

.box-card {
    position: absolute;
    right: 30px;
    top: 30px;
    z-index: 1;
    width: 23vw;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>