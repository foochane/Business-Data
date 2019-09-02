<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 用户编辑</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="add" @click="add">添加</el-button>

                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>


            <el-table :data="data" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="userName" label="姓名" sortable width="150">
                </el-table-column>
                <el-table-column prop="account" label="账号" sortable width="150">
                </el-table-column>
                <el-table-column prop="userPhone" label="电话" sortable width="150">
                </el-table-column>
                <el-table-column prop="position" label="岗位" width="200">
                </el-table-column>
                <el-table-column prop="userRight" label="用户权限" :formatter="formatter">
                </el-table-column>
                <el-table-column label="操作" width="300">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="handleSelect(scope.$index, scope.row)">查看</el-button>
                        <el-button size="small" type="selection" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <el-form-item label="姓名">
                    <el-input v-model="form.userName"></el-input>
                </el-form-item>
                <el-form-item label="账号">
                    <el-input v-model="form.account"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="form.userPhone"></el-input>
                </el-form-item>
                <el-form-item label="岗位">
                    <el-input v-model="form.position"></el-input>
                </el-form-item>
                <el-form-item label="权限">
                    <el-input v-model="form.userRight"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
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
                url: 'api/user/list',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
                form: {
                    userName: '',
                    account: '',
                    userPhone:'',
                    position:'',
                    userRight:''
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
                // if (process.env.NODE_ENV === 'development') {
                //     // this.url = '/ms/table/list';
                //     this.url = 'api/api/user/list';  //这个可以请求到后端的
                // };
                // this.$axios.get(this.url, {
                //     page: this.cur_page
                // }).then((res) => {
                //     console.log("数据：")
                //     console.log(res.data.rows);
                //     this.tableData = res.data.rows;
                // })

                this.tableData = [{"userName":"老王","account":"admin","userPhone":"13644444666","position":"管理员","userRight":1}];
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
            handleSelect(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                console.log("row：")
                console.log(row);
                this.form.title = "查看"
                this.form = {
                    userName: item.userName,
                    account: item.account,
                    userPhone:item.userPhone,
                    position: item.position,
                    userRight:item.userRight==1 ?"root":"其他"
                }
                this.editVisible = true;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                this.form = {
                    userName: item.userName,
                    account: item.account,
                    userPhone:item.userPhone,
                    position: item.position,
                    userRight:item.userRight==1 ?"root":"其他"
                }
                this.editVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            add() {

                this.$message.error('添加' );

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存编辑
            saveEdit() {
                this.$set(this.tableData, this.idx, this.form);
                this.editVisible = false;
                this.$message.success(`修改第 ${this.idx+1} 行成功`);
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
