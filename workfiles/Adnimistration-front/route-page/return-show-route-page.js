const ReturnShowRoutePage = {
  template: `
   <div id="returnShow">
        <el-divider>退货基本信息</el-divider>
        退货Id：{{returnId}} <br>
        订单Id：{{orderId}} <br>
        订单时间：{{(new Date(orderTimestamp)).toLocaleString()}} <br>
        客户Id：{{customerId}} <br>
        客户姓名：{{customerName}} <br>
        手机：{{mobile}} <br>
        邮箱：{{email}} <br>
        状态：{{statuses[status] ? statuses[status].label : ''}}<br>
        处理方式：{{actions[action] ? actions[action].label : ''}}<br>
        <br>

        <el-divider>退货商品信息</el-divider>
        商品代号：{{productCode}} <br>
        商品名称：{{productName}} <br>
        数量：{{quantity}} <br>
        原因：{{reasons[reason] ? reasons[reason].label : ''}} <br>
        是否开封：<span v-if="opened">是</span><span v-else>否</span> <br>
        备注：{{comment}} <br>
        申请时间：{{(new Date(createTimestamp)).toLocaleString()}}<br>
        更新时间：{{(new Date(updateTimestamp)).toLocaleString()}}<br>
        <br>

        <el-select v-model="selectedAction" placeholder="请选择处理方式">
            <el-option v-for="item in actions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>

        <el-button type="primary" @click="handleUpdateAction">修改处理方式</el-button>
    </div>
    `,
  data() {
    return {
      returnId: '',
      orderId: '',
      orderTimestamp: '',
      customerId: '',
      customerName: '',
      mobile: '',
      email: '',
      status: '',
      action: '',
      productCode: '',
      productName: '',
      quantity: '',
      reason: '',
      opened: '',
      comment: '',
      createTimestamp: '',
      updateTimestamp: '',
      actions: [
        { value: 0, label: '退货' },
        { value: 1, label: '换货' },
        { value: 2, label: '修理' }
      ],
      selectedAction: '',
      statuses: [
        { value: 0, label: '待处理' },
        { value: 1, label: '待取货' },
        { value: 2, label: '正在处理' },
        { value: 3, label: '完成' },
        { value: 4, label: '拒绝' }
      ],
      reasons: [
        { value: 0, label: '发货过期' },
        { value: 1, label: '订单错误' },
        { value: 2, label: '收到错误商品' },
        { value: 3, label: '质量问题' }
      ]
    }
  },
  mounted() {
    this.returnId = this.$route.params.returnId;
    if (!this.returnId) {
      alert('returnId is null');
      return;
    }

    this.getReturnById();
  },
    methods: {
      handleUpdateAction() {
        this.updateReturnAction();
      },
      updateReturnAction() {
        axios.post('/return/update', {
          returnId: this.returnId,
          action: this.selectedAction
        })
          .then( (response) =>{
            console.log(response);
            alert('处理方式更新成功');
            this.getReturnById();
          })
          .catch( (error) =>{
            console.log(error);
            alert('处理方式更新失败');
          });
      },
      getReturnById() {
        axios.get('/return/show', {
          params: {
            returnId: this.returnId
          }
        })
          .then( (response) =>{
            console.log(response);
            var returns = response.data;
            this.orderId = returns.orderId;
            this.orderTimestamp = returns.orderTimestamp;
            this.customerId = returns.customerId;
            this.customerName = returns.customerName;
            this.mobile = returns.mobile;
            this.email = returns.email;
            this.status = returns.status;
            this.action = returns.action;
            this.selectedAction = returns.action;
            this.productCode = returns.productCode;
            this.productName = returns.productName;
            this.quantity = returns.quantity;
            this.reason = returns.reason;
            this.opened = returns.opened;
            this.comment = returns.comment;
            this.createTimestamp = returns.createTimestamp;
            this.updateTimestamp = returns.updateTimestamp;
          })
          .catch( (error) =>{
            console.log(error);
          });
      }
    }
}
