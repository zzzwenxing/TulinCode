<template>
  <div class="footer">
    <div class="footer-logo">
      <img src="/imgs/logo-footer.png" alt="">
      <p>小米商城</p>
    </div>             
    <div class="footer-link">
      <a href="http://www.tulingxueyuan.cn/" target="_blank">图灵学院</a><span>|</span>
      <a href="https://ke.qq.com/course/231516?tuin=a6505b53" target="_blank">腾讯课堂java架构师培训</a><span>|</span>
      <a href="https://ke.qq.com/course/429988" target="_blank">数据结构与算法</a><span>|</span>
      <a href="https://tuling.ke.qq.com/" target="_blank">腾讯课堂图灵学院</a>
    </div>
    <div class="copyright">Copyright ©2019 <span class="domain">图灵学院</span> All Rights Reserved.</div>
  </div>
</template>
<script>
  export default{
    name:'nav-footer'
  }
</script>
<style lang="scss" scoped>
  .footer{
    height:234px;
    border-top:4px solid #FF6600;
    background-color:#333333;
    color:#999999;
    font-size:16px;
    text-align:center;
    .footer-logo{
      margin-top:46px;
      margin-bottom:31px;
      img{
        width:53px;
        height:36px;
        margin-bottom:13px;
      }
    }
    .footer-link{
      a{
        color:#999999;
        display:inline-block;
      }
      span{
        margin:0 10px;
      }
      margin-bottom:13px;
    }
    .copyright{
      .domain{
        color:#FF6600;
      }
    }
  }
</style>