package com.tuling.inversionofcontrol;

/**
 * 帅气的年轻小伙
 * Created by smlz on 2019/8/1.
 */
public class HandsomeBoy {

	private BeautifulGirl beautifulGirl;

	//你找女朋友的各种要求条件
	private String findGirlRequire;

	public HandsomeBoy() {
		//你可能比较牛掰,指腹为婚
		//beautifulGirl = new BeautifulGirl();
	}

	public void setBeautifulGirl(BeautifulGirl beautifulGirl) {
		this.beautifulGirl = beautifulGirl;
	}

	public void doXiuXiuSomeThing() {
		beautifulGirl.providerXiuXiuSomeThing();
	}

	public void setFindGirlRequire(String findGirlRequire) {
		this.findGirlRequire = findGirlRequire;
	}
}
