package com.tuling.version3.support;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by smlz on 2019/8/31.
 */
public class RedisCfgProperties {

	@Value("${redis.host}")
	private String host;

	@Value("${redis.port}")
	private Integer port;

	@Value("${redis.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.maxIdle}")
	private Integer maxIdle;



	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	@Override
	public String toString() {
		return "RedisCfgProperties{" +
				"host='" + host + '\'' +
				", port=" + port +
				", maxTotal=" + maxTotal +
				", maxIdle=" + maxIdle +
				'}';
	}
}
