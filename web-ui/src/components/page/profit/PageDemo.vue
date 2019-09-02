<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 网页模板</el-breadcrumb-item>
                <el-breadcrumb-item>网页模板</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="plugins-tips">
                vue-schart：vue.js封装sChart.js的图表组件。
                访问地址：<a href="https://github.com/lin-xin/vue-schart" target="_blank">vue-schart</a>
            </div>
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
