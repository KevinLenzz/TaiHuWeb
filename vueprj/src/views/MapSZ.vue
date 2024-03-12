<template>
  <div id="container"></div>
  <div v-for="o in sqs">
    <div v-if="o.name === duanmian">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ o.city }}&nbsp&nbsp{{ o.name }}</span>
          </div>
        </template>
        <table>
          <tr>
            <td>浊度</td>
            <td>{{o.turbidity}}</td>
            <td>NTU</td>
          </tr>
          <tr>
            <td>氨氮</td>
            <td>{{o.nh3n}}</td>
            <td>mg/L</td>
          </tr>
          <tr>
            <td>总氮</td>
            <td>{{o.total_nitrogen}}</td>
            <td>mg/L</td>
          </tr>
          <tr>
            <td>总磷</td>
            <td>{{ o.total_phosphorus }}</td>
            <td>mg/L</td>
          </tr>
          <tr>
            <td>水质</td>
            <td>{{ o.quality }}</td>
            <td>类</td>
          </tr>
          <tr>
            <td>河流</td>
            <td>{{ o.river }}</td>
            <td></td>
          </tr>
          <tr>
            <td>水温</td>
            <td>{{ o.temperature }}</td>
            <td>℃</td>
          </tr>
          <tr>
            <td>pH</td>
            <td>{{o.ph}}</td>
            <td></td>
          </tr>
          <tr>
            <td>高锰酸盐指数</td>
            <td>{{ o.codmn }}</td>
            <td>mg/L</td>
          </tr>
          <tr>
            <td>溶解氧</td>
            <td>{{ o.do }}</td>
            <td>mg/L</td>
          </tr>
          <tr>
            <td>电导率</td>
            <td>{{ o.conductivity }}</td>
            <td>μS/cm</td>
          </tr>
        </table>
        <template #footer>
          时间：{{o.time_point}}
        </template>
      </el-card>
    </div>
  </div>
</template>
<script>
import { onMounted, onUnmounted, ref } from "vue";
import AMapLoader from "@amap/amap-jsapi-loader";
import axios from 'axios';
export default {
  setup() {
    let map = null;
    const sqs = ref([]);
    const duanmian=ref("");

    onMounted(() => {
      let obj;
      axios({
        method: "post",
        url: "http://localhost:8080/a",
        data: "item=shuizhi"
      }).then(result => {
        obj = eval(result.data);
        sqs.value = obj.data;
        AMapLoader.load({
        key: "08ffbd0ca8947ecab25bc46920b684cb", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.ToolBar', 'AMap.Scale', 'AMap.ControlBar', 'AMap.MapType'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
        .then((AMap) => {
          const map = new AMap.Map("container", {
            version: "2.0",
            // 设置地图容器id
            viewMode: "2D", // 是否为3D地图模式
            zoom: 9, // 初始化地图级别
            center: [120.62, 31.31], // 初始化地图中心点位置
            mapStyle: "amap://styles/fresh",
          });
          let toolbar = new AMap.ToolBar({
            visible: true,
            position: {
              bottom: '15px',
              right: '100px'
            }
          });
          var scale = new AMap.Scale({
            visible: true,
            position: {
              left: '20px',
              bottom: '20px'
            }
          });
          var controlbar = new AMap.ControlBar({
            visible: true,
            position: {
              bottom: '15px',
              right: '150px'
            }
          });
          var type = new AMap.MapType({
            defaultType: 0,
            position: {
              left: '100px',
              top: '15px'
            }
          });
          map.addControl(type);
          map.addControl(toolbar);
          map.addControl(scale);
          map.addControl(controlbar);
          for (let i = 0; i < 125; i++) {
            if(sqs.value[i].longitude&&sqs.value[i].latitude){
              let marker = new AMap.Marker({
                position: [parseFloat(sqs.value[i].longitude),parseFloat(sqs.value[i].latitude)],
                title: sqs.value[i].name,
              });
              map.add(marker);
              marker.on("click", function (e) {
                duanmian.value=sqs.value[i].name;
              });
            }
          }
        })
        .catch((e) => {
          console.log(e);
        });
      });
    });

    onUnmounted(() => {
      map?.destroy();
    });
    return { sqs,duanmian};
  }
}
</script>

<style scoped>
#container {
  width: 100vw;
  height: 100vh;
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