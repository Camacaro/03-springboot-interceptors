package com.jesus.courses.springboot.interceptor.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("interceptorSchedule")
public class InterceptorSchedule implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InterceptorSchedule.class);

    @Value("${config.opening.hour}")
    private Integer opening;

    @Value("${config.closing.hour}")
    private Integer closing;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Hora actual, es un singleton por eso el getInstance
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        log.info("time : " + time);
        log.info("opening : " + opening);
        log.info("closing : " + closing);
        if (time >= opening && time < closing) {
            /*
              Esto es para crear un nuevo string crear mensajes, concatenar
              teniendo siempre una sola instancia
             */
            StringBuilder msg = new StringBuilder("Bienvenidos al horario de atención a clientes");
            msg.append(", atendemos desde las ");
            msg.append(opening);
            msg.append(" hrs. ");
            msg.append("hasta las ");
            msg.append(closing);
            msg.append(" hrs. Gracias por su visita");
            request.setAttribute("msg", msg.toString());
            return true;
        }

        response.sendRedirect(request.getContextPath().concat("/closed"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String msg = (String) request.getAttribute("msg");
        if (modelAndView != null && handler instanceof HandlerMethod) {
            modelAndView.addObject("attentionScheduleMsg", msg);
        }
    }
}
