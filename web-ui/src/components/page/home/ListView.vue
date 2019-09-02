<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 列表视图</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="delAll">快速查看</el-button>
                <el-select v-model="select_cate" placeholder="筛选" class="handle-select mr10">
                    <el-option key="1" label="条件1" value="条件1"></el-option>
                    <el-option key="2" label="条件2" value="条件2"></el-option>
                </el-select>
                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>

            <el-table :data="data" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="name" label="姓名" sortable width="150">
                </el-table-column>
                <el-table-column prop="position" label="岗位" width="200">
                </el-table-column>
                <el-table-column prop="violation" label="消费情况" width="200">
                </el-table-column>
                <el-table-column prop="location" label="消费地点" :formatter="formatter">
                </el-table-column>
                <el-table-column label="操作" width="300">
                    <template slot-scope="scope">
                        <el-button size="small" @click="handleDetail(scope.$index, scope.row)">详细信息</el-button>
                        <el-button size="small" type="danger" @click="handleLocate(scope.$index, scope.row)">会员信息</el-button>
                        <el-button size="small" type="primary" @click="handleTrack(scope.$index, scope.row)">消费轨迹</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
                </el-pagination>
            </div>
        </div>


        <!-- 详细信息弹出框 -->
        <el-dialog title="详细信息" :visible.sync="detailVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <el-form-item label="日期    "label-width="70px">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="   姓名"label-width="70px">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="   职位"label-width="70px">
                    <el-input v-model="form.position"></el-input>
                </el-form-item>
                <el-form-item label="位置"label-width="70px">
                    <el-input v-model="form.location"></el-input>
                </el-form-item>
                <el-form-item label= "消费情况" label-width="70px">
                    <el-input v-model="form.violation"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="detailVisible = false">关闭</el-button>
            </span>
        </el-dialog>

        <!-- 人员定位弹出框 -->
        <el-dialog title="会员信息" :visible.sync="locateVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
               <div>  显示会员信息</div>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="locateVisible = false">关闭</el-button>
            </span>
        </el-dialog>

        <!-- 轨迹查询弹出框 -->
        <el-dialog title="消费轨迹" :visible.sync="trackVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <div>  显示消费轨迹情况</div>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="trackVisible = false">关闭</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: 'api/member/list',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                detailVisible: false,
                locateVisible:false,
                trackVisible:false,
                delVisible: false,
                form: {
                    name: '',
                    position: '',
                    violation:'',
                    location:''
                },
                idx: -1
            }
        },
        created() {
            this.getData();
        },
        computed: {
            data() {
                return this.tableData.filter((d) => {
                    return d;
                })
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData() {
                // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
                if (process.env.NODE_ENV === 'development') {
                    // this.url = '/ms/table/list';
                    this.url = 'api/api/member/list';  //这个可以请求到后端的
                };
                this.$axios.get(this.url, {
                    page: this.cur_page
                }).then((res) => {
                    console.log("数据：")
                    console.log(res.data.rows);
                    this.tableData = res.data.rows;
                })

                // 修改为本地的假数据
                //this.tableData = [{"name":"xiaoming","position":"工人","violation":"无违规情况","location":"上面"},{"name":"xiaohou","position":"工人","violation":"无违规情况","location":"厂区"},{"name":"xiaohong","position":"工人","violation":"无违规情况","location":"上面"},{"name":"liming","position":"工人","violation":"无违规情况","location":"大厅"},{"name":"bob","position":"工人","violation":"无违规情况","location":"上面"}];
            },
            search() {
                this.is_search = true;
            },
            formatter(row, column) {
                // return row.address;
                 return row.location;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleDetail(index,row){
                this.idx = index;
                const item = this.tableData[index];
                this.form = {
                    name: item.name,
                    position: item.position,
                    violation: item.violation,
                    location: item.location
                }
                this.detailVisible = true;
            },
            handleLocate(index,row){
                this.idx = index;
                const item = this.tableData[index];
                this.locateVisible = true;
            },
            handleTrack(index,row){
                this.idx = index;
                const item = this.tableData[index];
                this.trackVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            delAll() {
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error('删除了' + str);
                this.multipleSelection = [];
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 确定删除
            deleteRow(){
                this.tableData.splice(this.idx, 1);
                this.$message.success('删除成功');
                this.delVisible = false;
            }
        }
    }

</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
</style>
