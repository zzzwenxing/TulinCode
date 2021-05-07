<template>
  <div class="order-confirm">
    <order-header title="订单确认">
      <template v-slot:tip>
        <span>请认真填写收货地址</span>
      </template>
    </order-header>
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="position: absolute; width: 0px; height: 0px; overflow: hidden;">
      <defs>
        <symbol id="icon-add" viewBox="0 0 31 32">
          <title>add</title>
          <path d="M30.745 15.152h-14.382v-14.596c0-0.308-0.243-0.557-0.543-0.557s-0.543 0.249-0.543 0.557v14.596h-14.665c-0.3 0-0.543 0.249-0.543 0.557s0.243 0.557 0.543 0.557h14.665v15.177c0 0.307 0.243 0.557 0.543 0.557s0.543-0.249 0.543-0.557v-15.177h14.382c0.3 0 0.543-0.249 0.543-0.557s-0.243-0.557-0.543-0.557z" class="path1"></path>
        </symbol>
        <symbol id="icon-edit" viewBox="0 0 32 32">
          <title>edit</title>
          <path d="M28.287 8.51l-4.805-4.806 0.831-0.831c0.472-0.472 1.086-0.777 1.564-0.777 0.248 0 0.452 0.082 0.622 0.253l3.143 3.144c0.539 0.54 0.133 1.529-0.524 2.186l-0.831 0.831zM26.805 9.992l-1.138 1.138-4.805-4.806 1.138-1.138 4.805 4.806zM24.186 12.612l-14.758 14.762-4.805-4.806 14.758-14.762 4.805 4.806zM7.379 28.288l-4.892 1.224 1.223-4.894 3.669 3.67zM31.123 4.011l-3.143-3.144c-0.567-0.567-1.294-0.867-2.103-0.867-1.036 0-2.174 0.52-3.045 1.391l-20.429 20.436c-0.135 0.134-0.23 0.302-0.276 0.487l-2.095 8.385c-0.089 0.355 0.017 0.736 0.276 0.995 0.198 0.198 0.461 0.307 0.741 0.307 0.085 0 0.171-0.010 0.254-0.031l8.381-2.096c0.185-0.047 0.354-0.142 0.487-0.276l20.43-20.436c1.409-1.41 2.042-3.632 0.524-5.15v0z" class="path1"></path>
        </symbol>
        <symbol id="icon-del" viewBox="0 0 32 32">
          <title>delete</title>
          <path d="M11.355 4.129v-2.065h9.29v2.065h-9.29zM6.194 29.935v-23.742h19.613v23.742h-19.613zM30.968 4.129h-8.258v-3.097c0-0.569-0.463-1.032-1.032-1.032h-11.355c-0.569 0-1.032 0.463-1.032 1.032v3.097h-8.258c-0.569 0-1.032 0.463-1.032 1.032s0.463 1.032 1.032 1.032h3.097v24.774c0 0.569 0.463 1.032 1.032 1.032h21.677c0.569 0 1.032-0.463 1.032-1.032v-24.774h3.097c0.569 0 1.032-0.463 1.032-1.032s-0.463-1.032-1.032-1.032v0z" class="path1"></path>
          <path d="M10.323 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z" class="path2"></path>
          <path d="M16 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z" class="path3"></path>
          <path d="M21.677 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z" class="path4"></path>
        </symbol>
      </defs>
    </svg>
    <div class="wrapper">
      <div class="container">
        <div class="order-box">
          <div class="item-address">
            <h2 class="addr-title">收货地址</h2>
            <div class="addr-list clearfix">
              <div class="addr-info" :class="{'checked':index == checkIndex}" @click="checkIndex=index" v-for="(item,index) in list" :key="index">
                <h2>{{item.name}}</h2>
                <div class="phone">{{item.phoneNumber}}</div>
                <div class="street">{{item.province + ' ' + item.city + ' ' + item.region + ' ' + item.detailAddress}}</div>
                <div class="action">
                  <a href="javascript:;" class="fl" @click="delAddress(item)">
                    <svg class="icon icon-del">
                      <use xlink:href="#icon-del"></use>
                    </svg>
                  </a>
                  <a href="javascript:;" class="fr" @click="editAddressModal(item)">
                    <svg class="icon icon-edit">
                      <use xlink:href="#icon-edit"></use>
                    </svg>
                  </a>
                </div>
              </div>
              <div class="addr-add" @click="openAddressModal">
                <div class="icon-add"></div>
                <div>添加新地址</div>
              </div>
            </div>
          </div>
          <div class="item-good">
            <h2>商品</h2>
            <ul>
              <li v-for="(item,index) in cartList" :key="index">
                <div class="good-name">
                  <img v-lazy="item.productMainImage" alt="">
                  <span>{{item.productName + ' ' + item.productSubTitle}}</span>
                </div>
                <div class="good-price">{{item.price}}元x{{item.quantity}}</div>
                 <div class="good-total">{{item.price * item.quantity}}元</div>
              </li>
            </ul>
          </div>
          <div class="detail">
           <div class="item" v-if="couponList.length!=0">
            <span class="item-name">选择优惠卷：</span>
            <select   class="selc" v-model="curCouponId" @change="getCouponId()" >
              <option  class="selc"  value="0">未选择 </option>
              <option  class="selc"  v-for="(x,index)   in couponList" :key="index"  :value=x.id>{{x.name}} </option>
            </select>
           </div>

            <div class="item">
              <span class="item-name">商品件数：</span>
              <span class="item-val">{{count}}件</span>
            </div>
            <div class="item">
              <span class="item-name">商品总价：</span>
              <span class="item-val">{{totalAmount}}元</span>
            </div>
            <div class="item">
              <span class="item-name">优惠活动：</span>
              <span class="item-val">{{promotionAmount}}元</span>
            </div>
            <div class="item">
              <span class="item-name">优惠券：</span>
              <span class="item-val">{{couponAmount}}元</span>
            </div>
            <div class="item">
              <span class="item-name">运费：</span>
              <span class="item-val">{{freightAmount}}元</span>
            </div>
            <div class="item-total">
              <span class="item-name">应付总额：</span>
              <span class="item-val">{{calcTotalAmount}}元</span>
            </div>
          </div>
          <div class="btn-group">
            <a href="/#/cart" class="btn btn-default btn-large">返回购物车</a>
            <a href="javascript:;" class="btn btn-large" @click="orderSubmit">去结算</a>
          </div>
        </div>
      </div>
    </div>
    <modal
      title="新增确认"
      btnType="1"
      :showModal="showEditModal"
      @cancel="showEditModal=false"
      @submit="submitAddress"
    >
      <template v-slot:body>
        <div class="edit-wrap">
          <div class="item">
            <input type="text" class="input" placeholder="姓名" v-model="checkedItem.name">
            <input type="text" class="input" placeholder="手机号" v-model="checkedItem.phoneNumber">
          </div>
          <div class="item">
            <select name="province" v-model="checkedItem.province">
              <option value="北京">北京</option>
              <option value="天津">天津</option>
              <option value="河北">河北</option>
            </select>
            <select name="city" v-model="checkedItem.city">
              <option value="北京">北京</option>
              <option value="天津">天津</option>
              <option value="河北">石家庄</option>
            </select>
            <select name="region" v-model="checkedItem.region">
              <option value="北京">昌平区</option>
              <option value="天津">海淀区</option>
              <option value="河北">东城区</option>
              <option value="天津">西城区</option>
              <option value="河北">顺义区</option>
              <option value="天津">房山区</option>
            </select>
          </div>
          <div class="item">
            <textarea name="street" v-model="checkedItem.detailAddress"></textarea>
          </div>
          <div class="item">
            <input type="text" class="input" placeholder="邮编" v-model="checkedItem.postCode">
          </div>
        </div>
      </template>
    </modal>
    <modal
      title="删除确认"
      btnType="1"
      :showModal="showDelModal"
      @cancel="showDelModal=false"
      @submit="submitAddress"
    >
      <template v-slot:body>
        <p>您确认要删除此地址吗？</p>
      </template>
    </modal>
  </div>
</template>
<script>

import OrderHeader from './../components/OrderHeader'
import Modal from './../components/Modal'
import constStore from './../store/constStore.js'
import Qs from 'qs'
export default{
  name:'order-confirm',
  data(){
    return {
      list:[],//收货地址列表
      cartList:[],//购物车中需要结算的商品列表
      cartTotalPrice:0,//商品总金额
      count:0,//商品结算数量
      checkedItem:{},//选中的商品对象
      userAction:'',//用户行为 0：新增 1：编辑 2：删除
      showDelModal:false,//是否显示删除弹框
      showEditModal:false,//是否显示新增或者编辑弹框
      checkIndex:0,//当前收货地址选中索引
      couponList:[],
      itemIds:[],
      activeIndex:0,
      totalAmount: 0,
      freightAmount: 0,
      promotionAmount: 50,
      payAmount: 0,
      curCouponId:0,
      couponAmount:0 
 

    }
  },
  computed:{
       calcTotalAmount:function(){
         return this.payAmount-this.couponAmount;
       }

  },   
  components:{
    OrderHeader,
    Modal
  },
  mounted(){
     
    this.getAddressList();
    this.getCartList();
    this.getCouponList();
  },
 
  methods:{
    getAddressList(){

      

      this.axios.get('/member/address/list').then((res)=>{
        this.list = res;
      });

   

    },
    // 打开新增地址弹框
    openAddressModal(){
      this.userAction = 0;
      this.checkedItem = {};
      this.showEditModal = true;
    },
    // 打开新增地址弹框
    editAddressModal(item){
      this.userAction = 1;
      this.checkedItem = item;
      this.showEditModal = true;
    },
    delAddress(item){
      this.checkedItem = item;
      this.userAction = 2;
      this.showDelModal = true;
    },
    // 地址删除、编辑、新增功能
    submitAddress(){
      let {checkedItem,userAction} = this;
      let method,url,params={};
      if(userAction == 0){
        method = 'post',url = '/member/address/add';
      }else if(userAction == 1){
        method = 'post',url = `/member/address/update/${checkedItem.id}`;
      }else {
        method = 'post',url = `/member/address/delete/${checkedItem.id}`;
      }
      if(userAction == 0 || userAction ==1){
        let { name, phoneNumber, province, city, region, detailAddress,postCode} = checkedItem;
        region=checkedItem.region;
        let errMsg='';
        if(!name){
          errMsg = '请输入收货人名称';
        }else if(!phoneNumber || !/\d{11}/.test(phoneNumber)){
          errMsg = '请输入正确格式的手机号';
        }else if(!province){
          errMsg = '请选择省份';
        }else if(!city){
          errMsg = '请选择对应的城市';
        }else if(!detailAddress || !region){
          errMsg = '请输入收货地址';
        }else if(!/\d{6}/.test(postCode)){
          errMsg = '请输入六位邮编';
        }
        
        if(errMsg){
          this.$message.error(errMsg);
          return;
        }
        params = {
          name,
          phoneNumber,
          postCode,
          province,
          city,
          region,
          detailAddress
          
        }
      }
      
      this.axios[method](url,params).then(()=>{
        this.closeModal();
        this.getAddressList();
        this.$message.success('操作成功');
      });
    },
    closeModal(){
      this.checkedItem = {};
      this.userAction = '';
      this.showDelModal = false;
      this.showEditModal = false;
    },
    getCartList(){

        if(constStore.itemids==undefined || constStore.itemids==null || constStore.itemids.length==0){
            this.$message.error('请选择一个产品');
            return;
        }
       
         var itemId="";
         constStore.itemids.map((id)=>{
           itemId+=id+",";
         });
        
        itemId=itemId.substring(0,itemId.length-1)
       
        this.axios.post('/order/generateConfirmOrder',Qs.stringify({itemIds: itemId},{indices: false}),
                  {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then((res)=>{
               this.cartList=res.cartPromotionItemList;
               res.cartPromotionItemList.map((item)=>{
                this.count += item.quantity;
                this.cartTotalPrice += item.price;
                this.itemIds.push(item.id);
               // this.couponAmount=res.
                this.totalAmount=res.calcAmount.totalAmount;
                this.freightAmount=res.calcAmount.freightAmount;
                this.promotionAmount=res.calcAmount.promotionAmount;
                this.payAmount=res.calcAmount.payAmount;

               res.couponHistoryDetailList.map((citem)=>{
                  var o = new Object();
                  o.id=citem.coupon.id;
                  o.name=citem.coupon.name;
                  o.amount=citem.coupon.amount;
                  this.couponList.push(o);
               });

  
               });

         });
     },
    // 订单提交
    orderSubmit(){
      let item = this.list[this.checkIndex];
      
      if(!item){
        this.$message.error('请选择一个收货地址');
        return;
      }

      if(this.curCouponId==0){
              this.axios.post('/order/generateOrder',{
                itemIds:this.itemIds,
                memberReceiveAddressId:item.id,
                payType:1
              
              }).then((res)=>{
              this.$router.push({
                  path:'/order/pay',
                  query:{
                    orderId:res.order.id
                  }
                })
              })
      }else{
            this.axios.post('/order/generateOrder',{
              couponId:this.curCouponId,
              itemIds:this.itemIds,
              memberReceiveAddressId:item.id,
              payType:1
            
            }).then((res)=>{
              this.$router.push({
                path:'/order/pay',
                query:{
                  orderId:res.order.id
                }
              })
            })
      }
    },
    getCouponList(){
    },
    getCouponId(){
         if(this.curCouponId==0){
            this.couponAmount=0;
         }else{
           this.couponList.map((citem)=>{
              if(citem.id==this.curCouponId){
                 this.couponAmount=citem.amount;
              }
           });
         }
        
    } 
  
  }
}
</script>
<style lang="scss">
  .order-confirm{
    .wrapper{
      background-color:#F5F5F5;
      padding-top:30px;
      padding-bottom:84px;
      .order-box{
        background-color:#ffffff;
        padding-left: 40px;
        padding-bottom: 40px;
        .addr-title{
          font-size: 20px;
          color: #333333;
          font-weight: 200;
          margin-bottom:21px;
        }
        .item-address{
          padding-top: 38px;
          .addr-list{
            .addr-info,.addr-add{
              box-sizing:border-box;
              float: left;
              width:271px;
              height:180px;
              border:1px solid #E5E5E5;
              margin-right: 15px;
              padding: 15px 24px;
              font-size: 14px;
              color:#757575;
            }
            .addr-info{
              cursor:pointer;
              h2{
                height:27px;
                font-size:18px;
                font-weight: 300;
                color:#333;
                margin-bottom:10px;
              }
              .street{
                height:50px;
              }
              .action{
                height:50px;
                line-height:50px;
                .icon{
                  width: 20px;
                  height: 20px;
                  fill: #666666;
                  vertical-align: middle;
                  &:hover{
                    fill: #FF6700;
                  }
                }
              }
              &.checked{
                border:1px solid #ff6700;
              }
            }
            .addr-add{
              text-align:center;
              color: #999999;
              cursor:pointer;
              .icon-add{
                width:30px;
                height:30px;
                border-radius:50%;
                background:url('/imgs/icon-add.png') #E0E0E0 no-repeat center;
                background-size:14px;
                margin: 0 auto;
                margin-top: 45px;
                margin-bottom: 10px;
              }
            }
          }
        }
        .item-good{
          margin-top:34px;
          border-bottom: 1px solid #E5E5E5;
          padding-bottom: 12px;
          h2{
            border-bottom:1px solid #E5E5E5;
            padding-bottom: 5px;
          }
          li{
            display:flex;
            align-items: center;
            height:40px;
            line-height:40px;
            margin-top:10px;
            font-size:16px;
            color:#333333;
            .good-name{
              flex:5;
              img{
                width:30px;
                height:30px;
                vertical-align:middle;
              }
            }
            .good-price{
              flex:2;
            }
            .good-total{
              padding-right:44px;
              color:#FF6600;
            }
          }
        }
        .item-shipping,.item-invoice{
          margin-top:31px;
          line-height: 20px;
          h2{
            display: inline-block;
            margin-right: 71px;
            font-size: 20px;
            width: 80px;
          }
          span,a{
            font-size:16px;
            color:#FF6700;
            margin-right:23px;
          }
        }
        .detail{
          padding: 50px 44px 33px 0;
          border-bottom: 1px solid #f5f5f5;
          text-align: right;
          font-size: 16px;
          color: #666666;
          .item-val{
            color:#FF6700;
          }
          .item{
            line-height: 15px;
            margin-bottom: 12px;
          }
          .item-val{
            display:inline-block;
            width:100px;
          }
          .item-total{
            .item-val{
              font-size:28px;
            }
          }
          .selc{
                border: 1px solid #ccc; 
                padding: 7px 7px;
                border-radius: 3px; 
                padding-left:5px;
                padding-bottom:10px;
                font-size:24;
                margin-bottom: 12px;
          }
        }
        .btn-group{
          margin-top: 37px;
          text-align: right;
        }
      }
    }
    .edit-wrap{
      font-size:14px;
      .item{
        margin-bottom:15px;
        .input{
          display:inline-block;
          width:283px;
          height:40px;
          line-height:40px;
          padding-left:15px;
          border:1px solid #E5E5E5;
          &+.input{
            margin-left:14px;
          }
        }
        select{
          height:40px;
          line-height:40px;
          border:1px solid #E5E5E5;
          margin-right:15px;
        }
        textarea{
          height:62px;
          width:100%;
          padding:13px 15px;
          box-sizing:border-box;
          border:1px solid #E5E5E5;
        }
      }
    }
  }
 
</style>