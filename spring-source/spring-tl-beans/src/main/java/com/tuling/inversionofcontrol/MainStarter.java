package com.tuling.inversionofcontrol;

/**
 * Created by smlz on 2019/8/1.
 */
public class MainStarter {

	public static void main(String[] args) {
		HandsomeBoy zhangsan = new HandsomeBoy();

		BeautifulGirl beautifulGirl = new BeautifulGirl("你找女朋友的各种条件");
		//女朋友各种投你所好
		beautifulGirl.setXXXX("女朋友各种投你所好");

		zhangsan.setBeautifulGirl(beautifulGirl);

		//做羞羞的事情
		zhangsan.doXiuXiuSomeThing();
	}
}
