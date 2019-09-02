<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
                 text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">
                            {{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';

    export default {
        data() {
            return {
                collapse: false,
                items: [

                    {
                        icon: 'el-icon-setting',
                        index: '1',
                        title: '首页',
                        subs: [
                            {
                                index: 'homeMap',
                                title: '地图视图'

                            },
                            {
                                index: 'homeList',
                                title: '列表视图'
                            }
                        ]
                    },

                    {
                        icon: 'el-icon-tickets',
                        index: '2',
                        title: '会员分析',
                        subs: [

                            {
                                index: 'memberInfo',
                                title: '会员RFM分析'
                            },
                            {
                                index: 'memberRight',
                                title: '会员画像分析'
                            },
                            {
                                index: 'positionManage',
                                title: '会员消费分析'
                            },
                            {
                                index: 'violationManage',
                                title: '会员标签'
                            },
                            {
                                index: 'alarmManage',
                                title: '会员流失率'
                            }

                        ]
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: '3',
                        title: '销售分析',
                        subs: [
                            {
                                index: 'transHeader',
                                title: '交易分析'
                            },

                            {
                                index: 'mapManage',
                                title: '品牌业态分析'
                            },

                            {
                                index: 'alarmSettings',
                                title: '支付类型分析'
                            },
                            {
                                index: 'electricFence',
                                title: '销售数据分析'
                            },
                            {
                                index: 'faultDetect',
                                title: '销售预收分析'
                            }
                        ]
                    },

                    {
                        icon: 'el-icon-rank',
                        index: '4',
                        title: '收益分析',
                        subs: [

                            {
                                icon: 'el-icon-star-on',
                                index: 'attendanceManage',
                                title: '收益分析'
                            },
                            {
                                icon: 'el-icon-rank',
                                index: 'trackManage',
                                title: '店铺分析'
                            },
                            {
                                icon: 'el-icon-warning',
                                index: 'violationStatistics',
                                title: '收益预测'
                            }
                        ]
                    },
                                        {
                        icon: 'el-icon-rank',
                        index: '5',
                        title: '活动效果分析',
                        subs: [

                            {
                                icon: 'el-icon-star-on',
                                index: 'attendanceManage',
                                title: '活动参与分析'
                            },
                            {
                                icon: 'el-icon-rank',
                                index: 'trackManage',
                                title: '代金券分析'
                            },
                            {
                                icon: 'el-icon-warning',
                                index: 'violationStatistics',
                                title: '历史活动分析'
                            }
                        ]
                    },

                    {
                        icon: 'el-icon-message',
                        index: '6',
                        title: '系统管理',
                        subs: [

                            {
                                index: 'userHelp',
                                title: '用户帮助'
                            },

                            {
                                index: 'systemInfo',
                                title: '系统信息'
                            },

                            {
                                index: 'PasswordModify',
                                title: '修改密码'
                            },

                            {
                                index: 'exit',
                                title: '退出系统'
                            }
                        ]
                    },

                    {
                        icon: 'el-icon-date',
                        index: '7',
                        title: '用户管理',
                        subs: [
                            {
                                index: 'userModify',
                                title: '用户编辑'
                            },
                            {
                                index: 'userRight',
                                title: '用户权限'
                            }


                        ]
                    }


                ]
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace('/', '');
            }
        },
        created() {
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar {
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom: 0;
    }

    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }

    .sidebar > ul {
        height: 100%;
    }
</style>
