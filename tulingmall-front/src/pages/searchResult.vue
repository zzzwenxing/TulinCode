<template>

  <div class="index">
  <nav-header></nav-header>
    <div class="product-box">
      <div class="container">
        <h2>查询结果</h2>
        <div class="wrapper">
          <div class="banner-left">
            <a href="/#/product/35"><img v-lazy="'/imgs/mix-alpha.jpg'" alt=""></a>
          </div>
          <div class="list-box">
            <div class="list" v-for="(item,i) in productList" v-bind:key="i">
               <div class="item"  >
                <div class="item-img">
                  <img v-lazy="item.pic" alt="">
                </div>
                <div class="item-info">
                  <h3>{{item.name}}</h3>
                  <p>{{item.subtitle}}</p>
                  <p class="price" @click="buyIt(item.id)">{{item.price}}元</p>
                </div>
               </div>
            </div>
          </div>
        </div>
      </div>
    </div>
      <nav-footer></nav-footer>
  </div>
 
</template>
<script>
  import NavHeader from './../components/NavHeader'
  import NavFooter from './../components/NavFooter'
  import Qs from 'qs'
  export default{
    name:'searchResult',
    components:{
      NavHeader,
      NavFooter
     },
    data(){
      return {
        keywords :this.$route.params.keywords,
        productList:[]
      }
    },
    mounted(){
      this.init();
    },
    methods:{
      init(){
        
         this.doSearch( );

      },
      doSearch( ){
           this.axios.post('/esProduct/search/simple',Qs.stringify({keyword : this.keywords},{indices: false}),
                  {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then((res)=>{
                     this.productList=res.list;   
          });
      },
      buyIt(id){
            this.$router.push(`/product/${id}`);
      } 
    },
    
  }
</script>
<style lang="scss">
  @import './../assets/scss/config.scss';
  @import './../assets/scss/mixin.scss';
  .index{
     
  
    .product-box{
      background-color:$colorJ;
      padding:30px 0 50px;
      h2{
        font-size:$fontF;
        height:21px;
        line-height:21px;
        color:$colorB;
        margin-bottom:20px;
      }
      .wrapper{
        display:flex;
        .banner-left{
          margin-right:16px;
          img{
            width:224px;
            height:619px;
          }
        }
        .list-box{
          .list{
            @include flex();
            width:986px;
            margin-bottom:14px;
            &:last-child{
              margin-bottom:0;
            }
            .item{
              width:236px;
              height:302px;
              background-color:$colorG;
              text-align:center;
              span{
                display:inline-block;
                width:67px;
                height:24px;
                font-size:14px;
                line-height:24px;
                color:$colorG;
                &.new-pro{
                  background-color:#7ECF68;
                }
                &.kill-pro{
                  background-color:#E82626;
                }
              }
              .item-img{
                img{
                  width:100%;
                  height:195px;
                }
              }
              .item-info{
                h3{
                  font-size:$fontJ;
                  color:$colorB;
                  line-height:$fontJ;
                  font-weight:bold;
                }
                p{
                  color:$colorD;
                  line-height:13px;
                  margin:6px auto 13px;
                }
                .price{
                  color:#F20A0A;
                  font-size:$fontJ;
                  font-weight:bold;
                  cursor:pointer;
                  &:after{
                    @include bgImg(22px,22px,'/imgs/icon-cart-hover.png');
                    content:' ';
                    margin-left:5px;
                    vertical-align: middle;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
</style>