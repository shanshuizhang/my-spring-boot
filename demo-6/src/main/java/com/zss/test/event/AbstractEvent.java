package com.zss.test.event;

/**
 * 事件抽象
 */
public abstract class AbstractEvent {

	protected Object source;

	public AbstractEvent(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
}
