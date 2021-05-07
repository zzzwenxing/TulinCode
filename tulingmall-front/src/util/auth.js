import Cookies from 'js-cookie'

const TokenKey = 'token'
 

export default{
  Cookies,
  methods:{
    getToken () {
       
      return Cookies.get(TokenKey)
     }
  }
}