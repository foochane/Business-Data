<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date">会员分析</i></el-breadcrumb-item>
                <el-breadcrumb-item>会员爱好统计</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">

            <div class="schart">
            <div class="content-title">饼状图</div>
            <schart canvasId="pie" width="500" height="400" :data="hobbyData" type="pie" :options="options2"></schart>
            </div>
            <div class="schart">
            <div class="content-title">环形图</div>
            <schart canvasId="ring" width="500" height="400" :data="hobbyData" type="ring" :options="options2"></schart>
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

                hobbyData:[],
                options1: {
                    title: '会员爱好统计',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999'
                },
                options2: {
                    title: '会员爱好统计',
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
                        this.url = 'api/api/hobby/list'; 
                    };
                    this.$axios.get(this.url, {
                        page: this.cur_page
                    }).then((res) => {
                        let data = res.data.rows
                        let num_amt_data = []
                        let num_repo_data = []
                        console.log(data)
                        // for(let i = 0;i<data.length;i++){
                        for(let i = 0;i<8;i++){

                            let tmp = {"name":data[i].hobby,"value":data[i].num}
                            this.hobbyData.push(tmp)
                        }
                    })
                    console.log(this.hobbyData)

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