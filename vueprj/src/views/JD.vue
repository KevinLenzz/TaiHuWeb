<template>
    <div id="wave2"></div>
    <div class="block" m="t-4">
        <el-row>
            <el-col :span="12" class="fade1">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/西山.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E8%A5%BF%E5%B1%B1/4747623')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
            <el-col :span="12" class="fade2">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/拈花湾.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E6%8B%88%E8%8A%B1%E6%B9%BE/17725495')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12" class="fade3">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/湖州太湖.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E6%B9%96%E5%B7%9E%E5%A4%AA%E6%B9%96%E6%97%85%E6%B8%B8%E5%BA%A6%E5%81%87%E5%8C%BA/8528582')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
            <el-col :span="12" class="fade4">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/太湖湾旅游度假区.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E6%AD%A6%E8%BF%9B%E5%A4%AA%E6%B9%96%E6%B9%BE%E6%97%85%E6%B8%B8%E5%BA%A6%E5%81%87%E5%8C%BA/3679711')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12" class="fade5">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/南湖.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E5%8D%97%E6%B9%96/2916548')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
            <el-col :span="12" class="fade6">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/九里.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E4%B9%9D%E9%87%8C%E9%A3%8E%E6%99%AF%E5%8C%BA/19258191')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12" class="fade7">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/吴越国王陵考古遗址公园.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E5%90%B4%E8%B6%8A%E5%9B%BD%E7%8E%8B%E9%99%B5/4707195')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
            <el-col :span="12" class="fade8">
                <el-carousel trigger="click" height="25vh" :autoplay="false">
                    <el-carousel-item>
                        <img class="image" src="../../public/朱家角.jpg" style="cursor:pointer" @click="goToLink('https://baike.baidu.com/item/%E6%9C%B1%E5%AE%B6%E8%A7%92%E5%8F%A4%E9%95%87%E6%97%85%E6%B8%B8%E5%8C%BA/5167631')"/>
                    </el-carousel-item>
                </el-carousel>
            </el-col>
        </el-row>
</div>
</template>
<script>
import 'animate.css';
export default{
    methods: {
        goToLink(url){
            window.open(url,'_blank');
        }
    }
}
</script>
<style scoped>
#wave2{
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-image: url("../../public/wave2.jpg");
    background-repeat: repeat;
    opacity: 0.2;
}
.fade1{
    animation: fadeInLeft;
    animation-duration: 0.2s;
    animation-delay:0.2s;
    animation-fill-mode:both
}
.fade2{
    animation: fadeInRight;
    animation-duration: 0.2s;
    animation-delay:0.3s;
    animation-fill-mode:both
}
.fade3{
    animation: fadeInLeft;
    animation-duration: 0.2s;
    animation-delay:0.4s;
    animation-fill-mode:both
}
.fade4{
    animation: fadeInRight;
    animation-duration: 0.2s;
    animation-delay:0.5s;
    animation-fill-mode:both
}
.fade5{
    animation: fadeInLeft;
    animation-duration: 0.2s;
    animation-delay:0.6s;
    animation-fill-mode:both
}
.fade6{
    animation: fadeInRight;
    animation-duration: 0.2s;
    animation-delay:0.7s;
    animation-fill-mode:both
}
.fade7{
    animation: fadeInLeft;
    animation-duration: 0.2s;
    animation-delay:0.8s;
    animation-fill-mode:both
}
.fade8{
    animation: fadeInRight;
    animation-duration: 0.2s;
    animation-delay:0.9s;
    animation-fill-mode:both
}
.image{
    position:absolute;
    width:100%;
    top:-50%;
}
.demonstration {
    color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>