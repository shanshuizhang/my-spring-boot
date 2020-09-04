package com.zss.test.event;

/**
 * 事件监听器抽象
 */
public interface EventListener<E extends AbstractEvent> {

	/**
	 * 监听到关系的事件，执行自己的逻辑
	 * @param event
	 */
	void onEvent(E event);
}
