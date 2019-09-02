<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 轨迹统计</el-breadcrumb-item>
                <el-breadcrumb-item>基础图表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!-- <div class="plugins-tips">
                vue-schart：vue.js封装sChart.js的图表组件。
                访问地址：<a href="https://github.com/lin-xin/vue-schart" target="_blank">vue-schart</a>
            </div> -->
            <div class="schart">
                <div class="content-title">柱状图</div>
                <schart canvasId="bar" width="500" height="400" :data="data1" type="bar" :options="options1"></schart>
            </div>
            <div class="schart">
            <div class="content-title">折线图</div>
            <schart canvasId="line" width="500" height="400" :data="data1" type="line" :options="options1"></schart>
            </div>
            <div class="schart">
            <div class="content-title">饼状图</div>
            <schart canvasId="pie" width="500" height="400" :data="data2" type="pie" :options="options2"></schart>
            </div>
            <div class="schart">
            <div class="content-title">环形图</div>
            <schart canvasId="ring" width="500" height="400" :data="data2" type="ring" :options="options2"></schart>
            </div>
        </div>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    export default {
        components: {
            Schart
        },
        data:()=>({

                data1:[],
                data2:[],
                options1: {
                    title: '某商店近年营业总额',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999'
                },
                options2: {
                    title: '某商店各商品年度销量',
                    bgColor: '#607d8b',
                    titleColor: '#ffffff',
                    legendColor: '#ffffff'
                }
            

        }),
        created() {
            console.log("create")
            this.getData();
        },
        methods: {
            
            getData() {
                    if (process.env.NODE_ENV === 'development') {
                        this.url = 'api/api/custrepo/list'; 
                    };
                    this.$axios.get(this.url, {
                        page: this.cur_page
                    }).then((res) => {
                        let data = res.data.rows
                        let num_amt_data = []
                        let num_repo_data = []
                        console.log(data)
                        for(let i = 0;i<data.length;i++){

                            let tmp1 = {"name":data[i].billmouth,"value":data[i].num_amt}
                            this.data1.push(tmp1)
                            let tmp2 = {"name":data[i].billmouth,"value":data[i].num_repo}
                            this.data2.push(tmp2)
                        }
                    })
                    console.log(this.data1)

                }
        }
    }

</script>
<style scoped>
    .schart{
        width: 600px;
        display: inline-block;
    }
    .content-title{
        clear: both;
        font-weight: 400;
        line-height: 50px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }
    
</style>