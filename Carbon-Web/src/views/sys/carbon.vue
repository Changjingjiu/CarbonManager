<template>
    <div>
        <!-- 搜索栏 -->
        <el-card id="search">
            <el-input
             placeholder="检测点名称"
             v-model="searchModel.monitorName"
             clearable>
        </el-input>
        <el-input
             placeholder="检测点地址"
             v-model="searchModel.monitorAddr"
             clearable>
        </el-input>

        <el-button @click="clear" type="primary" round>重置查询</el-button>

        <el-button @click="getCarbonList" type="primary" icon="el-icon-search" round>查询</el-button>
        
        <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" round >新建</el-button>
        </el-card>

        <!-- 结果列表 -->
        <el-card>

            <el-table
      :data="carbonList"
      style="width: 100%">
      <el-table-column
        prop="warningId"
        label="用户ID">
      </el-table-column>

      <el-table-column
        prop="monitorName"
        label="监测点名称"
        >
      </el-table-column>
      <el-table-column
        prop="monitorAddr"
        label="监测点地址">
      </el-table-column>
      
      <el-table-column
        prop="monitorPower"
        label="实时功率">
      </el-table-column>
      
      <el-table-column
        prop="currentVoltage"
        label="实时电压">
      </el-table-column>

      <el-table-column
        prop="warningStatus"
        label="状态">
      </el-table-column>
      
      <el-table-column
        prop="reportTime"
        label="上报时间">
      </el-table-column>

      <el-table-column
        prop="description"
        label="备注">
      </el-table-column>
      
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click="openEditUI(scope.row.warningId)" type="primary" icon="el-icon-edit" circle></el-button>
          <el-button @click="deleteCarbon(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>

    </el-table>

        </el-card>

        <!-- 分页组件 -->
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="searchModel.pageNo"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="searchModel.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>


      <!-- 用户信息编辑对话框 -->
      <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
        <el-form :model="carbonForm" ref="carbonFormRef" :rules="rules">

          <el-form-item label="检测点名称" prop="monitorName" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.monitorName" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="检测点地址" prop="monitorAddr" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.monitorAddr" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="实时功率" prop="monitorPower" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.monitorPower" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="实时电压" prop="currentVoltage" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.currentVoltage" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="状态" prop="warningStatus" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.warningStatus" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="上报时间" :label-width="formLabelWidth">
            <el-date-picker
              v-model="carbonForm.reportTime"
              type="datetime"
              placeholder="选择日期时间"
              value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="备注" :label-width="formLabelWidth">
            <el-input v-model="carbonForm.description" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveCarbon">确 定</el-button>
        </div>
      </el-dialog>

    </div>
</template>

<script>
import carbonApi from '../../api/carbonManage'
export default {
    data(){
        return{
            formLabelWidth: '130px',
            carbonForm: {},
            dialogFormVisible: false,
            title: "",
            total: 0,
            searchModel: {
                pageNo: 1,
                pageSize: 10
            },
            carbonList:[],
            rules: {
              monitorName: [
            { required: true, message: '请填写检测点名称', trigger: 'blur' }
                    ],
                    monitorAddr: [
            { required: true, message: '请填写检测点地址', trigger: 'blur' }
                    ],
                    monitorPower: [
            { required: true, message: '请填写实时功率', trigger: 'blur' }
                    ],
                    currentVoltage: [
            { required: true, message: '请填写实时电压', trigger: 'blur' }
                    ],
                    warningStatus: [
            { required: true, message: '请填写状态', trigger: 'blur' }
                    ]
            }
        }
    },
    methods: {
        clear(){
          location.reload();
        },
        deleteCarbon(carbon){
          this.$confirm(`确认删除用户 ${carbon.monitorName}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          carbonApi.deleteCarbonById(carbon.warningId).then( response => {
              this.$message({
              type: 'success',
              message: '删除成功'
            });
            this.getCarbonList();
          });
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
        },
        saveCarbon(){
          //触发表单验证
          this.$refs.carbonFormRef.validate((valid) => {
          if (valid) {
            //提交请求给后台
            carbonApi.saveCarbon(this.carbonForm).then(response =>{
                // 成功提示
                this.$message({
                showClose: true,
                message: '新建成功',
                type: 'success'
              });

                // 关闭对话框
              this.dialogFormVisible = false;


                // 刷新表格
                this.getCarbonList();
            });


          } else {
            console.log('error submit!!');
            return false;
          }
        });

          
        },
        clearForm(){
          this.carbonForm = {},
          this.$refs.carbonFormRef.clearValidate();
        },
        openEditUI(warningId){
          if( warningId == null){
            this.title = "添加能效检测点";
          }else{
            this.title = "修改能效检测点";
            //根据id查询用户
            carbonApi.getCarbonById(warningId).then(response => {
              this.carbonForm = response.data;
            })
          }
          this.dialogFormVisible = true;
        },
        handleSizeChange(pageSize){
          this.searchModel.pageSize = pageSize;
          this.getCarbonList();
        },
        handleCurrentChange(pageNo){
          this.searchModel.pageNo = pageNo;
          this.getCarbonList();
        },
        getCarbonList(){
            carbonApi.getCarbonList(this.searchModel).then(response => {
                this.carbonList = response.data.rows;
                this.total = response.data.total;
            });

        }
    },
    created(){
        this.getCarbonList();
    }
};
</script>

<style lang="scss" scoped>

#search .el-input{
    width: 200px;
    margin-right: 8px;
}

.el-dialog .el-input{
  width: 50%;
}
</style>