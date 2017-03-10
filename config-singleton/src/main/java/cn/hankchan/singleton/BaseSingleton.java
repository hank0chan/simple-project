package cn.hankchan.singleton;

/**
 * 基础的单例接口
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 10:25:16 - 10 Mar 2017
 * @detail 只有一个reload方法，由子类实现，用于重新载入配置信息
 */
public interface BaseSingleton {

	public void reload();
}
