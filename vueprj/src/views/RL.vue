<template>
    <img id="rp" src="../../public/river_lake.png" />
    <div id="chart" style="width: 100vw;height:70vh"></div>
</template>
<script>
import * as echarts from 'echarts';
import axios from 'axios';
import { onMounted, ref } from 'vue'
// 基于准备好的dom，初始化echarts实例
export default {
    setup() {
        onMounted(() => {
            axios({
                method: "post",
                url: "http://localhost:8080/a",
                data: "item=riverlake"
            }).then(result => {
                let obj = eval(result.data);
                let Chart = echarts.init(document.getElementById("chart"));
                let option = {
                    title: {
                        left:'82%',
                        top:'85%',
                        text: '部分河流、湖泊水位',
                        subtext: '更新时间: '+obj.time
                    },
                    grid: {
                        left: "60%",
                        top: "5%",
                        right: "5%",
                        bottom: "20%",
                        containLabel: true
                    },
                    yAxis: {
                        position: 'right',
                        type: 'category',
                        data: obj.names,
                        axisLabel:{
                            fontSize:15,
                            color:"#000000"
                        }
                    },
                    xAxis: {
                        inverse: true,
                        type: 'value'
                    },
                    series: [
                        {
                            label: {
                                formatter: '{c}米',
                                show: true,
                                position: 'outside'
                            },
                            showBackground: true,
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                                    { offset: 0, color: '#83bff6' },
                                    { offset: 0.5, color: '#188df0' },
                                    { offset: 1, color: '#188df0' }
                                ])
                            },
                            emphasis: {
                                itemStyle: {
                                    color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                                        { offset: 0, color: '#2378f7' },
                                        { offset: 0.7, color: '#2378f7' },
                                        { offset: 1, color: '#83bff6' }
                                    ])
                                }
                            },
                            data: obj.values,
                            type: 'bar'
                        }
                    ]
                };
                Chart.setOption(option);
            })
        })
    }
}
</script>
<style>
#chart{
    position: absolute;
}
#rp {
    width: 150vh;
    height: 100vh;
    top: 0;
    left: 0px;
    user-select: none;
    z-index: -2;
    position: absolute;
    opacity: 1;
    -webkit-user-drag: none;
    pointer-events: none;
}
</style>