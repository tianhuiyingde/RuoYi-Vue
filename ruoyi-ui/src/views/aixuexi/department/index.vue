<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="研发部门名称" prop="departmentName">
        <el-input
          v-model="queryParams.departmentName"
          placeholder="请输入研发部门名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['aixuexi:department:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['aixuexi:department:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['aixuexi:department:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['aixuexi:department:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="departmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="研发部门id" align="center" prop="departmentId" />
      <el-table-column label="研发部门名称" align="center" prop="departmentName" />
      <el-table-column label="部门描述" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aixuexi:department:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aixuexi:department:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改研发部门对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="研发部门名称" prop="departmentName">
          <el-input v-model="form.departmentName" placeholder="请输入研发部门名称" />
        </el-form-item>
        <el-form-item label="部门描述" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">业务线信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAxxServiceline">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAxxServiceline">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="axxServicelineList" :row-class-name="rowAxxServicelineIndex" @selection-change="handleAxxServicelineSelectionChange" ref="axxServiceline">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="业务线名称" prop="servicelineName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.servicelineName" placeholder="请输入业务线名称" />
            </template>
          </el-table-column>
          <el-table-column label="业务线描述" prop="remark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入业务线描述" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDepartment, getDepartment, delDepartment, addDepartment, updateDepartment, exportDepartment } from "@/api/aixuexi/department";

export default {
  name: "Department",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedAxxServiceline: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 研发部门表格数据
      departmentList: [],
      // 业务线表格数据
      axxServicelineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        departmentName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询研发部门列表 */
    getList() {
      this.loading = true;
      listDepartment(this.queryParams).then(response => {
        this.departmentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        departmentId: null,
        departmentName: null,
        remark: null
      };
      this.axxServicelineList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.departmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加研发部门";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const departmentId = row.departmentId || this.ids
      getDepartment(departmentId).then(response => {
        this.form = response.data;
        this.axxServicelineList = response.data.axxServicelineList;
        this.open = true;
        this.title = "修改研发部门";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.axxServicelineList = this.axxServicelineList;
          if (this.form.departmentId != null) {
            updateDepartment(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDepartment(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const departmentIds = row.departmentId || this.ids;
      this.$confirm('是否确认删除研发部门编号为"' + departmentIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDepartment(departmentIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
	/** 业务线序号 */
    rowAxxServicelineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 业务线添加按钮操作 */
    handleAddAxxServiceline() {
      let obj = {};
      obj.servicelineName = "";
      obj.remark = "";
      this.axxServicelineList.push(obj);
    },
    /** 业务线删除按钮操作 */
    handleDeleteAxxServiceline() {
      if (this.checkedAxxServiceline.length == 0) {
        this.$alert("请先选择要删除的业务线数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.axxServicelineList.splice(this.checkedAxxServiceline[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleAxxServicelineSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.axxServiceline.clearSelection();
        this.$refs.axxServiceline.toggleRowSelection(selection.pop());
      } else {
        this.checkedAxxServiceline = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有研发部门数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportDepartment(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
