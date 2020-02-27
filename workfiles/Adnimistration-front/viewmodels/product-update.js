var update = new Vue({
    el: '#update',
    data: {
        productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        stockQuantity: '',
        rewordPoints: '',
        sortOrder: '',
        description: '',
        selectedStatus: 1,
        selectedMainPic: '',
        mainPicUrl: '',
        selectedOtherPics: [],
        otherPicUrls: [],
        statuses: [
            { value: 0, label: '下架' },
            { value: 1, label: '上架' },
            { value: 2, label: '待审核' }
        ],
        mainFileList: [],
        otherFileList: []
    },
    mounted() {
        var url = new URL(location.href);
        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            return;
        }
        this.getProductById();
    },
    methods: {
        handleUpdateClick() {
            this.updateProduct();
        },
        handleOnMainChange(val) {
            this.selectedMainPic = val.raw;
        },
        handleUploadMainClick() {
            this.uploadMainImage();
        },
        uploadMainImage() {
            var formData = new FormData();
            formData.append("image", this.selectedMainPic);

            axios.post('/image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (response) {
                    console.log(response);
                  update.mainPicUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        handleOnOtherChange(file, fileList) {
            this.selectedOtherPics = fileList;
        },
        handleOnOtherRemove(file, fileList) {
            this.selectedOtherPics = fileList;
        },
        handleUploadOtherClick() {
            this.uploadOtherImage();
        },
        uploadOtherImage() {
            this.selectedOtherPics.forEach(pic => {
                var formData = new FormData();
                formData.append("image", pic.raw);

                axios.post('/image/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                    .then(function (response) {
                        console.log(response);
                        var url = response.data;
                      update.otherPicUrls.push(url);
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert('上传失败');
                    });
            });


        },
        updateProduct() {
            axios.post('/product/update', {
                productId: this.productId,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                status: this.selectedStatus,
                mainPicUrl: this.mainPicUrl,
                rewordPoints: this.rewordPoints,
                sortOrder: this.sortOrder,
                description: this.description,
                otherPicUrls: this.otherPicUrls
            })
                .then(function (response) {
                    console.log(response);
                    alert('修改成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getProductById() {
            axios.get('/product/show', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var product = response.data;
                  update.productId = product.productId;
                  update.productCode = product.productCode;
                  update.productName = product.productName;
                  update.price = product.price;
                  update.discount = product.discount;
                  update.stockQuantity = product.stockQuantity;
                  update.selectedStatus = product.status;
                  update.rewordPoints = product.rewordPoints;
                  update.sortOrder = product.sortOrder;
                  update.mainPicUrl = product.mainPicUrl;
                  update.description = product.description;
                  update.otherPicUrls = product.otherPicUrls;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
