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
      <el-form-item label="业务线名称" prop="servicelineName">
        <el-input
          v-model="queryParams.servicelineName"
          placeholder="请输入业务线名称"
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
          v-hasPermi="['aixuexi:serviceline:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="servicelineList"
      row-key="servicelineId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="研发部门id" prop="departmentId" />
      <el-table-column label="业务线名称" align="center" prop="servicelineName" />
      <el-table-column label="业务线描述" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aixuexi:serviceline:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['aixuexi:serviceline:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aixuexi:serviceline:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改业务线对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="研发部门id" prop="departmentId">
          <treeselect v-model="form.departmentId" :options="servicelineOptions" :normalizer="normalizer" placeholder="请选择研发部门id" />
        </el-form-item>
        <el-form-item label="业务线名称" prop="servicelineName">
          <el-input v-model="form.servicelineName" placeholder="请输入业务线名称" />
        </el-form-item>
        <el-form-item label="业务线描述" prop="remark">
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
import { listServiceline, getServiceline, delServiceline, addServiceline, updateServiceline, exportServiceline } from "@/api/aixuexi/serviceline";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Serviceline",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 业务线表格数据
      servicelineList: [],
      // 业务线树选项
      servicelineOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        departmentId: null,
        servicelineName: null,
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
    /** 查询业务线列表 */
    getList() {
      this.loading = true;
      listServiceline(this.queryParams).then(response => {
        this.servicelineList = this.handleTree(response.data, "servicelineId", "departmentId");
        this.loading = false;
      });
    },
    /** 转换业务线数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.servicelineId,
        label: node.servicelineName,
        children: node.children
      };
    },
	/** 查询业务线下拉树结构 */
    getTreeselect() {
      listServiceline().then(response => {
        this.servicelineOptions = [];
        const data = { servicelineId: 0, servicelineName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "servicelineId", "departmentId");
        this.servicelineOptions.push(data);
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
        servicelineId: null,
        departmentId: null,
        servicelineName: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.servicelineId) {
        this.form.departmentId = row.servicelineId;
      } else {
        this.form.departmentId = 0;
      }
      this.open = true;
      this.title = "添加业务线";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.departmentId = row.servicelineId;
      }
      getServiceline(row.servicelineId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改业务线";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.servicelineId != null) {
            updateServiceline(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addServiceline(this.form).then(response => {
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
      this.$confirm('是否确认删除业务线编号为"' + row.servicelineId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delServiceline(row.servicelineId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    }
  }
};
</script>
