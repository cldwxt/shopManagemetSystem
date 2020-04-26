package com.dlnu.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dlnu.pojo.User;
import com.dlnu.service.UserService;


@WebFilter("/*")
public class AutoLoginFilter implements Filter {
	 //private UserDao userDao = null;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        //��session�л�ȡ�û���Ϣ
        String username = (String)session.getAttribute("username");
        Cookie cookie = null;
        //�����session�л�ȡ�û���ϢΪ�գ�����Ҫ��ȡcookie���е�¼�Լ���ŵ�session�У�������С�
        if(username == null){
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length > 0){
                for (Cookie c : cookies) {
                    if(c.getName().equals("AUTO_LOGIN")){
                        cookie = c;
                        break;
                    }
                }
            }
        }
        //cookie��Ϊ�գ�˵�������Զ���¼��cookie
        if(cookie != null){
            //��ȡcookie��ֵ��ŵ�session��
            String[] userInfo = cookie.getValue().split("-");
            User u = new User();
            u.setUserName(userInfo[0]);
            u.setPassword(userInfo[1]);
            //����service��¼
            UserService service = new UserService();
            int userBack = service.checkLogin(u.getUserName(),u.getPassword());
            //��½�ɹ�
            if(userBack == 4){
                session.setAttribute("username",u.getUserName());
            }
        }
        //��������ڣ�ֱ�ӷ���
        chain.doFilter(request, resp);
    }

  

}
