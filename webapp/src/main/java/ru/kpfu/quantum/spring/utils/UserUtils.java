package ru.kpfu.quantum.spring.utils;

import javax.servlet.http.HttpServletRequest;

import ru.kpfu.quantum.spring.entities.User;

/**
 * Класс-утиллита проверяет залогиненность какого-либо юзера, содержит ключ
 * карты сессии
 * 
 */
public class UserUtils {
	/** Ключ для карты сессии, по которому будет получаться юзер */
	public static final String USER_KEY = "user";

	/**
	 * Проверка залогиненности юзера
	 * 
	 * @param request реквест
	 * @return <code>true</code> если залогинен, <code>false</code> если никакой
	 *         юзер не залогинен в сессии
	 */
	public static boolean isLogined(HttpServletRequest request) {
		boolean result = request.getSession().getAttribute(USER_KEY) != null;
		return result;
	}

	/**
	 * Получение юзера. 
	 * @param request ревест
	 * @return юзера или <code>null</code>,если нет залогиненного пользователя
	 */
	public static User getUserFromSession(HttpServletRequest request) {
		User result = (User) request.getSession().getAttribute(USER_KEY);
		return result;
	}
	
	/**
	 * Очищает сессию(все значения в карте атрибутов будут стерты)
	 * @param request ревест
	 */
	public static void clearSession(HttpServletRequest request){
		request.getSession().invalidate();
	}

}
