import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/homeMap'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                 //首页
                {
                    path: '/homeMap',
                    component: resolve => require(['../components/page/home/MapView.vue'], resolve),
                    meta: { title: '地图视图' }
                },
                {
                    path: '/homeList',
                    component: resolve => require(['../components/page/home/ListView.vue'], resolve),
                    meta: { title: '列表视图' }
                },

                // 会员分析
                {
                    path: '/memberInfo',
                    component: resolve => require(['../components/page/member/PageDemo1.vue'], resolve),
                    meta: { title: '会员RFM分析' }
                },
                {
                    path: '/memberRight',
                    component: resolve => require(['../components/page/member/PageDemo.vue'], resolve),
                    meta: { title: '会员画像分析' }
                },
                {
                    path: '/positionManage',
                    component: resolve => require(['../components/page/member/PageDemo.vue'], resolve),
                    meta: { title: '会员消费分析' }
                },
                {
                    path: '/violationManage',
                    component: resolve => require(['../components/page/member/PageDemo.vue'], resolve),
                    meta: { title: '会员标签' }
                },
                {
                    path: '/AlarmManage',
                    component: resolve => require(['../components/page/member/PageDemo1.vue'], resolve),
                    meta: { title: '会员流失率' }
                },

                 //销售分析
                 {
                    path: '/transHeader',
                    component: resolve => require(['../components/page/sell/TransHeader.vue'], resolve),
                    meta: { title: '交易分析' }
                },
                {
                    path: '/mapManage',
                    component: resolve => require(['../components/page/sell/custrepo.vue'], resolve),
                    meta: { title: '品牌业态分析' }
                },
                {
                    path: '/alarmSettings',
                    component: resolve => require(['../components/page/sell/PageDemo1.vue'], resolve),
                    meta: { title: '支付类型分析' }
                },
                {
                    path: '/electricFence',
                    component: resolve => require(['../components/page/sell/PageDemo1.vue'], resolve),
                    meta: { title: '销售数据分析' }
                },
                {
                    path: '/faultDetect',
                    component: resolve => require(['../components/page/sell/PageDemo1.vue'], resolve),
                    meta: { title: '销售预收分析' }
                },

                // 收益分析
                {
                    path: '/attendanceManage',
                    component: resolve => require(['../components/page/profit/PageDemo1.vue'], resolve),
                    meta: { title: '收益分析' }
                },
                {
                    path: '/trackManage',
                    component: resolve => require(['../components/page/profit/PageDemo1.vue'], resolve),
                    meta: { title: '店铺分析' }
                },
                {
                    path: '/violationStatistics',
                    component: resolve => require(['../components/page/profit/PageDemo1.vue'], resolve),
                    meta: { title: '收益预测' }
                },
                // 活动效果分析
                {
                    path: '/attendanceManage',
                    component: resolve => require(['../components/page/promotion/PageDemo1.vue'], resolve),
                    meta: { title: '活动参与分析' }
                },
                {
                    path: '/trackManage',
                    component: resolve => require(['../components/page/promotion/PageDemo1.vue'], resolve),
                    meta: { title: '代金券分析' }
                },
                {
                    path: '/violationStatistics',
                    component: resolve => require(['../components/page/promotion/PageDemo1.vue'], resolve),
                    meta: { title: '历史活动分析' }
                },

                // 系统管理
                {
                    path: '/userHelp',
                    component: resolve => require(['../components/page/system/UserHelp.vue'], resolve),
                    meta: { title: '用户帮助' }
                },
                {
                    path: '/systemInfo',
                    component: resolve => require(['../components/page/system/SystemInfo.vue'], resolve),
                    meta: { title: '系统信息' }
                },
                {
                    path: '/PasswordModify',
                    component: resolve => require(['../components/page/system/PasswordModify.vue'], resolve),
                    meta: { title: '密码修改' }
                },
                {
                    path: '/exit',
                    component: resolve => require(['../components/page/system/Exit.vue'], resolve),
                    meta: { title: '退出系统' }
                },

                 // 用户管理
                {
                    path: '/userModify',
                    component: resolve => require(['../components/page/user/UserModify.vue'], resolve),
                    meta: { title: '用户编辑' }
                },
                {
                    path: '/userRight',
                    component: resolve => require(['../components/page/user/UserRight.vue'], resolve),
                    meta: { title: '用户权限' }
                }



            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
