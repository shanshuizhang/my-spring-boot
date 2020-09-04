package com.zss.test.event;

/**
 * 事件广播器抽象
 */
public interface EventMulticaster {

	/**
	 * 注册事件监听器
	 * @param eventListener
	 */
	void addEventListener(EventListener<?> eventListener);

	/**
	 * 移除事件监听器
	 * @param eventListener
	 */
	void removeEventListener(EventListener<?> eventListener);

	/**
	 * 广播事件
	 * @param event
	 */
	void multicaster(AbstractEvent event);
}
