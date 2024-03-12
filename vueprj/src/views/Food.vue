<template>
    <div id="bg"></div>
    <img id="pgng" @click="load(1)" src="../../public/排骨年糕.jpg" />
    <img id="mzpg" @click="load(2)" src="../../public/梅子排骨.jpg" />
    <img id="sj" @click="load(3)" src="../../public/生煎.jpg" />
    <img id="qt" @click="load(4)" src="../../public/青团.png" />
    <img id="azm" @click="load(5)" src="../../public/奥灶面.jpg" />
    <img id="xlb" @click="load(6)" src="../../public/小笼包.jpg" />
    <el-card v-if="ifshow" class="box-card" draggable>
        <template #header>
            <div class="card-header">
                <span>{{ title }}</span>
            </div>
        </template>
        <el-scrollbar height="300px">
            <div v-for="review in reviews">
                <el-text class="rvname" size="large">{{ review.name }}</el-text><br />
                <el-rate
                    v-model="review.rate"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                /><br />
                <el-text class="rvcontent" line-clamp="100" size="large" style="width: 20vw">{{ review.content }}</el-text><br />
                <el-text class="rvtime" size="large">{{ review.time }}</el-text><br />
                <hr/>
            </div>
        </el-scrollbar>
        <el-input v-model="input" style="caret-color: #11be59;" placeholder="留名" />
        <el-input v-model="textarea" maxlength="50" style="caret-color: #11be59;" placeholder="评论内容" show-word-limit
            type="textarea" />
        <el-rate v-model="ratearea" allow-half />
        <br />
        <el-button @click="send()" id="enter">提交</el-button>
        <br />
    </el-card>
</template>

<script>
import { ElMessage } from 'element-plus'
import { ref } from 'vue'
import axios from 'axios';
export default {
    setup() {
        const ifshow=ref(false)
        const reviews= ref()
        const textarea = ref('')
        const input = ref('')
        const ratearea = ref()
        const title = ref('')
        const idx=ref()
        function load(vidx){
            if(ifshow.value==false){
                ifshow.value=true;
            }
            title.value=vidx;
            textarea.value='';
            input.value=''
            ratearea.value=5
            idx.value=vidx;
            axios({
            method: "get",
            url: "http://localhost:8080/c",
            params:{
                idx:idx.value
            }
            }).then(result => {
                reviews.value=eval(result.data);
            })
        }
        function send() {
            if(textarea.value==''&&input.value==''){
                ElMessage("请留名并输入评论内容");
            }else if(textarea.value==''){
                ElMessage("请输入评论内容");
            }else if(input.value==''){
                ElMessage("请留名");
            }else{
                let pack={
                        name: input.value,
                        content: textarea.value,
                        rate: ratearea.value,
                        idx:idx.value
                    };
                axios({
                    method: "post",
                    url: "http://localhost:8080/b",
                    data:"data="+JSON.stringify(pack)
                }).then(result =>{load(idx.value)});
            }
        }
        return { ifshow,title,load,reviews,textarea, input, ratearea, send }
    }
}
</script>

<style>
#bg{
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-image: url("../../public/bg.png");
    background-repeat: repeat;
    opacity: 0.5;
}
.rvname{
    float: left;
}
.rvcontent{
    float: left;
}
.rvtime{
    float: right;
}
#enter {
    position: relative;
    float: right;
}

.box-card {
    position: absolute;
    right: 30px;
    top: 30px;
    z-index: 1;
    width: 23vw;
}

#pgng {
    position: absolute;
    top: 20vh;
    left: 10vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}

#mzpg {
    position: absolute;
    top: 20vh;
    left: 30vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}

#sj {
    position: absolute;
    top: 20vh;
    left: 50vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}

#qt {
    position: absolute;
    top: 60vh;
    left: 10vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}

#azm {
    position: absolute;
    top: 60vh;
    left: 30vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}

#xlb {
    position: absolute;
    top: 60vh;
    left: 50vw;
    height: 20vh;
    width: 20vw;
    object-fit: cover;
}
</style>