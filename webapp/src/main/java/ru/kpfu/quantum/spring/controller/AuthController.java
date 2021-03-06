package ru.kpfu.quantum.spring.controller;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.kpfu.quantum.spring.domain.LoginBean;
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.repository.UserRepository;
import ru.kpfu.quantum.spring.utils.UserUtils;

/**
 * Контроллер главной сраницы-логина
 * 
 */
@Controller
public class AuthController {
	/**
	 * С помощью DependencyInjection получает объект для работы с пользователям
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Этот метод будет обрабатывать запросы по адресу / (в нашем случае -
	 * http://localhost:8080/
	 * 
	 * @return строка-название jsp-файла.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
        modelMap.addAttribute("loginBean", new LoginBean());
		return "index";
	}

	/**
	 * Выполнение логина
	 * @param httpServletRequest ревест
	 * @return строку с адресом редиректа
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(HttpServletRequest httpServletRequest,
			 @Valid @ModelAttribute LoginBean loginBean, BindingResult bindingResult) {
        Map<String, Boolean> fieldErrors = new HashMap<>();
        if(bindingResult.hasErrors()) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                fieldErrors.put(fieldError.getField(), true);
            }
            httpServletRequest.setAttribute("fieldErrors", fieldErrors);
            return "index";
        }
		User user = userRepository.findByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword());
		String page;
		if (user != null) {
			// кладем в сессию юзера
			httpServletRequest.getSession().setAttribute(UserUtils.USER_KEY, user);
			page = "/";
		} else {
			page = "/passwordRemind?login=" + loginBean.getLogin();
		}
		return "redirect:" + page;
	}

	/**
	 * Разлогинивание-очистка сессии и переход на главную
	 * @param httpServletRequest реквест
	 * @return редирект на главную
	 */
	@RequestMapping("/logout")
	public String processLogout(HttpServletRequest httpServletRequest) {
		UserUtils.clearSession(httpServletRequest);
		return "redirect:/";
	}
}
