package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReligionDTO;
import service.SchoolDTO;
import service.SkillDTO;
import service.StaffDAO;
import service.StaffDTO;


@WebServlet("/sign/SignUp")
public class SignUpForm extends HttpServlet {
    private StaffDAO staffdao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StaffDAO doGet 메서드 실행");
		staffdao = new StaffDAO();
		
		
		List<ReligionDTO> relist = staffdao.selectReligion();
		List<SchoolDTO> sclist = staffdao.selectSchool();
		List<SkillDTO> sklist = staffdao.selectSkill();
		request.setAttribute("relist", relist);
		request.setAttribute("sclist", sclist);
		request.setAttribute("sklist", sklist);
		request.getRequestDispatcher("/Check/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SignUpForm doPost 메서드 실행");
		request.setCharacterEncoding("euc-kr");
		int st_no = Integer.parseInt(request.getParameter("st_no"));
		System.out.println("st_no : "+ st_no);
		String st_name = request.getParameter("st_name");
		System.out.println("st_name : "+ st_name);
		int st_sn = Integer.parseInt(request.getParameter("st_sn"));
		System.out.println("st_sn : "+ st_sn);
		int st_graduateday = Integer.parseInt(request.getParameter("st_graduateday"));
		System.out.println("st_graduateday : "+ st_graduateday);
		int schoolno = Integer.parseInt(request.getParameter("schoolno"));
		System.out.println("schoolno : "+ schoolno);
		int religionno = Integer.parseInt(request.getParameter("religionno"));
		System.out.println("religionno : "+ religionno);
		
		StaffDTO staffdto = new StaffDTO();
		staffdto.setSt_no(st_no);
		staffdto.setSt_name(st_name);
		staffdto.setSt_sn(st_sn);
		staffdto.setSt_graduateday(st_graduateday);
		staffdto.setSt_schoolno(schoolno);
		staffdto.setSt_religionno(religionno);
				
		staffdao = new StaffDAO();
		int rowCount = staffdao.insertStaff(staffdto);
		 
		response.sendRedirect(request.getContextPath()+"/Check/index.jsp");
	
	}

}
