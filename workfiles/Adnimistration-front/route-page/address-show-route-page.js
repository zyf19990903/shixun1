const AddressShowRoutePage = {
  template: `
    <div id="addressShow">
        标签：{{tag}} <br>
        内容：{{content}} <br>
        收货人姓名：{{receiverName}} <br>
        收货人手机：{{receiverMobile}} <br>
        <br>
    </div>
    `,
  data() {
    return {
      addressId: '',
      tag: '',
      content: '',
      receiverName: '',
      receiverMobile: ''
    }
  },
    mounted() {
      this.addressId = this.$route.query.addressId;
      if (!this.addressId) {
        alert('addressId is null');
        return;
      }

      this.getAddressById();
    },
    methods: {
      getAddressById() {
        axios.get('/address/show', {
          params: {
            addressId: this.addressId
          }
        })
          .then( (response) =>{
            console.log(response);
            var address = response.data;
            this.tag = address.tag;
            this.content = address.content;
            this.receiverName = address.receiverName;
            this.receiverMobile = address.receiverMobile;
          })
          .catch( (error) =>{
            console.log(error);
          });
      }
    }
}
