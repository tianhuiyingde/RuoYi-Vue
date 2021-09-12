<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="研发部门id" prop="departmentId">
        <el-input
          v-model="queryParams.departmentId"
          placeholder="请输入研发部门id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题分类id" prop="serviceId">
        <el-input
          v-model="queryParams.serviceId"
          placeholder="请输入问题分类id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务线id" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入业务线id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题概述" prop="questionDesc">
        <el-input
          v-model="queryParams.questionDesc"
          placeholder="请输入问题概述"
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
          v-hasPermi="['aixuexi:question:add']"
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
          v-hasPermi="['aixuexi:question:edit']"
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
          v-hasPermi="['aixuexi:question:remove']"
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
          v-hasPermi="['aixuexi:question:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="问题分类id" align="center" prop="questionId" />
      <el-table-column label="研发部门id" align="center" prop="departmentId" />
      <el-table-column label="问题分类id" align="center" prop="serviceId" />
      <el-table-column label="业务线id" align="center" prop="categoryId" />
      <el-table-column label="问题概述" align="center" prop="questionDesc" />
      <el-table-column label="问题答案" align="center" prop="questionAnswer" />
      <el-table-column label="问题关键字" align="center" prop="questionKey" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aixuexi:question:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aixuexi:question:remove']"
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

    <!-- 添加或修改问题详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="研发部门id" prop="departmentId">
          <el-input v-model="form.departmentId" placeholder="请输入研发部门id" />
        </el-form-item>
        <el-form-item label="问题分类id" prop="serviceId">
          <el-input v-model="form.serviceId" placeholder="请输入问题分类id" />
        </el-form-item>
        <el-form-item label="业务线id" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入业务线id" />
        </el-form-item>
        <el-form-item label="问题概述" prop="questionDesc">
          <el-input v-model="form.questionDesc" placeholder="请输入问题概述" />
        </el-form-item>
        <el-form-item label="问题答案" prop="questionAnswer">
          <el-input v-model="form.questionAnswer" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="问题关键字" prop="questionKey">
          <el-input v-model="form.questionKey" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listQuestion, getQuestion, delQuestion, addQuestion, updateQuestion, exportQuestion } from "@/api/aixuexi/question";

export default {
  name: "Question",
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
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 问题详情表格数据
      questionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        departmentId: null,
        serviceId: null,
        categoryId: null,
        questionDesc: null,
        questionAnswer: null,
        questionKey: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        departmentId: [
          { required: true, message: "研发部门id不能为空", trigger: "blur" }
        ],
        serviceId: [
          { required: true, message: "问题分类id不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "业务线id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询问题详情列表 */
    getList() {
      this.loading = true;
      listQuestion(this.queryParams).then(response => {
        this.questionList = response.rows;
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
        questionId: null,
        departmentId: null,
        serviceId: null,
        categoryId: null,
        questionDesc: null,
        questionAnswer: null,
        questionKey: null,
        remark: null
      };
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
      this.ids = selection.map(item => item.questionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加问题详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const questionId = row.questionId || this.ids
      getQuestion(questionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改问题详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionId != null) {
            updateQuestion(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuestion(this.form).then(response => {
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
      const questionIds = row.questionId || this.ids;
      this.$confirm('是否确认删除问题详情编号为"' + questionIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQuestion(questionIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有问题详情数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportQuestion(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
