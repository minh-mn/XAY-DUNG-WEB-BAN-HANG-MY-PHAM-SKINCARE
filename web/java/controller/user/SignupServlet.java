package web.java.controller.user;

import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import web.java.dao.UserDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ================= GET ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("Page/web/signup.jsp")
               .forward(request, response);
    }

    /* ================= POST ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        String fullname = request.getParameter("fullname");

        /* ===== 1. VALIDATE ===== */
        if (username == null || password == null || email == null || fullname == null ||
            username.isBlank() || password.isBlank() ||
            email.isBlank() || fullname.isBlank()) {

            request.setAttribute("mess", "Please fill in all required fields");
            request.getRequestDispatcher("Page/web/signup.jsp")
                   .forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();

        /* ===== 2. CHECK USERNAME EXISTS ===== */
        if (userDAO.getUserByUsername(username) != null) {
            request.setAttribute("mess", "Username already exists");
            request.getRequestDispatcher("Page/web/signup.jsp")
                   .forward(request, response);
            return;
        }

        /* ===== 3. SIGN UP ===== */
        boolean success = userDAO.signUp(username, password, email, fullname);

        if (!success) {
            request.setAttribute("mess", "Sign up failed. Please try again.");
            request.getRequestDispatcher("Page/web/signup.jsp")
                   .forward(request, response);
            return;
        }

        /* ===== 4. SEND WELCOME EMAIL ===== */
        sendWelcomeEmail(email, fullname, username);

        /* ===== 5. DONE ===== */
        request.setAttribute("mess", "Sign up successful! Please login.");
        request.getRequestDispatcher("Page/web/login.jsp")
               .forward(request, response);
    }

    /* ================= SEND EMAIL ================= */
    private void sendWelcomeEmail(String toEmail, String fullname, String username) {

        final String fromEmail = "dangushopjava@gmail.com";
        final String appPassword = "dangushop123"; // ⚠ demo – KHÔNG dùng mật khẩu thật

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(
            props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            }
        );

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
            );

            message.setSubject("Welcome to Beauty Shop");
            message.setContent(
                """
                <div>
                    <p>Dear <b>%s</b>,</p>
                    <p>Thank you for registering at our shop.</p>
                    <p>Your username: <b>%s</b></p>
                    <p>We hope you enjoy your shopping experience!</p>
                </div>
                """.formatted(fullname, username),
                "text/html;charset=UTF-8"
            );

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // Không làm fail signup
        }
    }
}
