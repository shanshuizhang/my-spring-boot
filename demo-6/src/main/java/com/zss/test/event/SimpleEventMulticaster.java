package com.zss.test.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件广播器简单实现
 */
public class SimpleEventMulticaster implements EventMulticaster {

	private Map<Class<?>, List<EventListener>> listenerMap = new ConcurrentHashMap<>();

	@Override
	public void addEventListener(EventListener<?> eventListener) {
		Class<?> clazz = getEventType(eventListener);
		List<EventListener> listeners = listenerMap.get(clazz);
		if(listeners == null){
			listeners = new ArrayList<>();
			listenerMap.put(clazz,listeners);
		}
		listeners.add(eventListener);
	}

	@Override
	public void removeEventListener(EventListener<?> eventListener) {
		Class<?> clazz = getEventType(eventListener);
		List<EventListener> listeners = listenerMap.get(clazz);
		if(listeners != null){
			listeners.remove(eventListener);
		}
	}

	@Override
	public void multicaster(AbstractEvent event) {
		List<EventListener> listeners = listenerMap.get(event.getClass());
		if(listeners != null){
			for(EventListener listener: listeners){
				listener.onEvent(event);
			}
		}
	}

	private Class<?> getEventType(EventListener eventListener){
		ParameterizedType parameterizedType = (ParameterizedType)eventListener.getClass().getGenericInterfaces()[0];
		Type eventType = parameterizedType.getActualTypeArguments()[0];
		return (Class<?>)eventType;
	}
}
