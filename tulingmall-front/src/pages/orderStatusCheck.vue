<template>
  <div class="order-list">
    <order-header title="订单下单中">
      <template v-slot:tip>
        <span>请谨防钓鱼链接或诈骗电话，了解更多></span>
      </template>
    </order-header>
    <div class="wrapper">
      <div class="container">
        <div class="order-box">
          <loading v-if="loading"></loading>
          <div class="order" >
            <div class="order-title">
            <img src="data:image/gif;base64,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" style="width:32px;height:32px;" 
            
            />
            <span> {{resultText}}</span>
            </div>
            <div class="order-content clearfix">
        
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import OrderHeader from './../components/OrderHeader'
 
  export default{
    name:'order-list',
    components:{
      OrderHeader     
    },
    
    data(){
      return {
         otimer:function(){},
         id:this.$route.params.id,
         resultText:"状态查询中..."
      }
    },
    created(){
         this.timer();
    },
    mounted(){
      this.getOrderStatus();
    },
    methods:{
      getOrderStatus(){
          
         this.axios.get('/order/miaosha/result?productId='+this.id).then((res)=>{
            
            if(res==null){
                this.resultText="无进行中的订单";
                this.destroyed();
              return ;
            }
            if(res==-1){
                this.destroyed();
                this.resultText="秒杀失败";
               // this.$router.push({path:'/product/'+this.id});
            }else if(res>1){
               this.destroyed();
                  this.$router.push({
                  path:'/order/pay',
                  query:{
                    orderId:res
                  }
                }) 
            } 
        })
       },
      timer(){
        this.otimer=setInterval(()=>{
          this.getOrderStatus();
        },3000);

      },
      destroyed() {
        clearInterval(this.otimer)
       }
  
    }
  }
</script>
<style lang="scss">
  @import './../assets/scss/config.scss';
  @import './../assets/scss/mixin.scss';
  .order-list{
    .wrapper{
      background-color:$colorJ;
      padding-top:33px;
      padding-bottom:110px;
      .order-box{
        .order{
          @include border();
          background-color:$colorG;
          margin-bottom:31px;
          &:last-child{
            margin-bottom:0;
          }
          .order-title{
            @include height(74px);
            background-color:$colorK;
            padding:0 43px;
            font-size:16px;
            color:$colorC;
            .item-info{
              span{
                margin:0 9px;
              }
            }
            .money{
              font-size:26px;
              color:$colorB;
            }
          }
          .order-content{
            padding:0 43px;
            .good-box{
              width:88%;
              .good-list{
                display: flex;
                align-items: center;
                height:145px;
                .good-img{
                  width:112px;
                  img{
                    width:100%;
                  }
                }
                .good-name{
                  font-size:20px;
                  color:$colorB;
                }
              }
            }
            .good-state{
              @include height(145px);
              font-size: 20px;
              color:$colorA;
              a{
                color:$colorA;
              }
            }
          }
        }
        .pagination{
          text-align:right;
        }
        .el-pagination.is-background .el-pager li:not(.disabled).active{
          background-color: #FF6600;
        }
        .el-button--primary{
          background-color: #FF6600;
          border-color: #FF6600;
        }
        .load-more,.scroll-more{
          text-align:center;
        }
      }
    }
  }
</style>